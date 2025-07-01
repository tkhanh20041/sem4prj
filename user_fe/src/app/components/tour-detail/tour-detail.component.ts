import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Cart } from 'src/app/common/Cart';
import { CartDetail } from 'src/app/common/CartDetail';
import { Customer } from 'src/app/common/Customer';
import { Favorites } from 'src/app/common/Favorites';
import { Tour } from 'src/app/common/Tour';
import { Rate } from 'src/app/common/Rate';
import { CartService } from 'src/app/services/cart.service';
import { CustomerService } from 'src/app/services/customer.service';
import { FavoritesService } from 'src/app/services/favorites.service';
import { TourService } from 'src/app/services/tour.service';
import { RateService } from 'src/app/services/rate.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-tour-detail',
  templateUrl: './tour-detail.component.html',
  styleUrls: ['./tour-detail.component.css']
})
export class TourDetailComponent implements OnInit {

  tour!: Tour;
  tours!: Tour[];
  id!: number;

  isLoading = true;

  customer!: Customer;
  favorite!: Favorites;
  favorites!: Favorites[];
  totalLike!: number;

  cart!: Cart;
  cartDetail!: CartDetail;
  cartDetails!: CartDetail[];

  rates: Rate[] = [];
  rateAll:Rate[] = [];
  countRate!:number;

  itemsComment:number = 3;

  tourImageList: string[] = [];
  isGalleryVisible: boolean = false;
   currentImageIndex: number = 0;
  
  constructor(
    private tourService: TourService,
    private modalService: NgbModal,
    private cartService: CartService, 
    private toastr: ToastrService,
    private router: Router,
    private route: ActivatedRoute,
    private customerService: CustomerService, 
    private favoriteService: FavoritesService,
    private sessionService: SessionService,
    private rateService: RateService) {
    route.params.subscribe(val => {
      this.ngOnInit();
    })
  }

  slideConfig = {"slidesToShow": 7, "slidesToScroll": 2, "autoplay": true};

  ngOnInit(): void {
    this.modalService.dismissAll();
    this.router.events.subscribe((evt) => {
      if (!(evt instanceof NavigationEnd)) {
        return;
      }
      window.scrollTo(0, 0)
    });
    this.id = this.route.snapshot.params['id'];
    this.getTour();
    this.getRates();
    this.getTotalLike();
    this.getAllRate();
  }

  setItemsComment(size: number) {
    this.getTour();
    this.getRates();
    this.getTotalLike();
    this.getAllRate();
    this.itemsComment = size;
    console.log(this.itemsComment);
    
  }

  getTour() {
    this.tourService.getOne(this.id).subscribe(data => {
      this.isLoading = false;
      this.tour = data as Tour;
      this.tourService.getSuggest(this.tour.category.categoryId, this.tour.tourId).subscribe(data => {
        this.tours = data as Tour[];
      })
    }, error => {
      this.toastr.error('Tour du lịch không tồn tại!', 'Hệ thống');
      this.router.navigate(['/home'])
    })
  }

  getRates() {
    this.rateService.getByTour(this.id).subscribe(data=>{
      if(this.rates == null){
        this.rates = [];
      }
      this.rates = data as Rate[];
    }, error=>{
      this.toastr.error('Lỗi hệ thống!', 'Hệ thống');
    })
  }

  getAllRate() {
    this.rateService.getAll().subscribe(data => {
      if (this.rateAll == null) {
        this.rateAll = [];
      }
      this.rateAll = data as Rate[];
    })
  }

  getAvgRate(id: number): number {
    let avgRating: number = 0;
    this.countRate = 0;
    if (this.rateAll == null) {
      return 0;
    }
    for (const item of this.rateAll) {
      if (item.tour.tourId === id) {
        avgRating += item.rating;
        this.countRate++;
      }
    }
    return this.countRate==0 ? 0 : Math.round(avgRating/this.countRate * 10) / 10;
  }

  toggleLike(id: number) {
    let email = this.sessionService.getUser();
    if (email == null) {
      this.router.navigate(['/sign-form']);
      this.toastr.info('Hãy đăng nhập để sử dụng dịch vụ của chúng tôi', 'Hệ thống');
      return;
    }
    this.favoriteService.getByTourIdAndEmail(id, email).subscribe(data => {
      if (data == null) {
        this.customerService.getByEmail(email).subscribe(data => {
          this.customer = data as Customer;
          this.favoriteService.post(new Favorites(0, new Customer(this.customer.userId), new Tour(id))).subscribe(data => {
            this.toastr.success('Thêm thành công!', 'Hệ thống');
            this.favoriteService.getByEmail(email).subscribe(data => {
              this.favorites = data as Favorites[];
              this.favoriteService.setLength(this.favorites.length);
              this.getTotalLike();
            }, error => {
              this.toastr.error('Lỗi truy xuất dữ liệu!', 'Hệ thống');
            })
          }, error => {
            this.toastr.error('Thêm thất bại!', 'Hệ thống');
          })
        })
      } else {
        this.favorite = data as Favorites;
        this.favoriteService.delete(this.favorite.favoriteId).subscribe(data => {
          this.toastr.info('Đã xoá ra khỏi danh sách yêu thích!', 'Hệ thống');
          this.favoriteService.getByEmail(email).subscribe(data => {
            this.favorites = data as Favorites[];
            this.favoriteService.setLength(this.favorites.length);
            this.getTotalLike();
          }, error => {
            this.toastr.error('Lỗi truy xuất dữ liệu!', 'Hệ thống');
          })
        }, error => {
          this.toastr.error('Lỗi!', 'Hệ thống');
        })
      }
    })
  }

  getTotalLike() {
    this.favoriteService.getByTour(this.id).subscribe(data => {
      this.totalLike = data as number;
    })
  }
  getTourImageList(id: number) {
    this.tourService.getTourImageList(id).subscribe((data: string[]) => {
      this.tourImageList = data;
      this.currentImageIndex = 0;  // Reset to the first image
      this.isGalleryVisible = true;  // Show the gallery
    });
  }

  showGallery() {
    console.log("show gallery");
    this.isGalleryVisible = true;  
  }

  closeGallery() {
    console.log("close gallery");
    this.isGalleryVisible = false;
  }

  // Show the next image in the list
  nextImage() {
    if (this.currentImageIndex < this.tourImageList.length - 1) {
      this.currentImageIndex++; // Move to the next image
    } else {
      this.currentImageIndex = 0; // Loop back to the first image if at the end
    }
  }

  // Show the previous image in the list
  previousImage() {
    if (this.currentImageIndex > 0) {
      this.currentImageIndex--; // Move to the previous image
    } else {
      this.currentImageIndex = this.tourImageList.length - 1; // Loop back to the last image if at the start
    }
  }

  addCart(tourId: number, price: number) {
    let email = this.sessionService.getUser();
    console.log("email: ",email);
    if (email == null) {
      this.router.navigate(['/sign-form']);
      this.toastr.info('Hãy đăng nhập để sử dụng dịch vụ của chúng tôi', 'Hệ thống');
      return;
    }
    this.cartService.getCart(email).subscribe(data => {
      this.cart = data as Cart;

          var today = new Date();
          var dd = today.getDate();
          var mm = today.getMonth() + 1;
          var yyyy = today.getFullYear();
          var tempStartDate = { year: yyyy, month: mm, day: dd };
          const start_date = new Date(tempStartDate.year, tempStartDate.month - 1, tempStartDate.day);

          var today = new Date();
          var dd = today.getDate() + 1;
          var mm = today.getMonth() + 1;
          var yyyy = today.getFullYear();
          var tempEndDate = { year: yyyy, month: mm, day: dd };
          const end_date = new Date(tempEndDate.year, tempEndDate.month - 1, tempEndDate.day); 

      this.cartDetail = new CartDetail(0, 1, price, new Tour(tourId), new Cart(this.cart.cartId), start_date, end_date);
      this.cartService.postDetail(this.cartDetail).subscribe(data => {
        this.toastr.success('Thêm vào giỏ hàng thành công!', 'Hệ thống!');
        this.cartService.getAllDetail(this.cart.cartId).subscribe(data => {
          this.cartDetails = data as CartDetail[];
          this.cartService.setLength(this.cartDetails.length);
        })
      }, error => {
        this.toastr.error('Tour du lịch này có thể đã hết chỗ!', 'Hệ thống');
        this.router.navigate(['/all-tour']);
        window.location.href = "/all-tour";
      })
    })
  }

}
