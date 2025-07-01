package Sem4.TravelTour.utils;

import Sem4.TravelTour.entity.Book;
import Sem4.TravelTour.entity.BookDetail;
import Sem4.TravelTour.service.BookService.BookDetailService;
import Sem4.TravelTour.service.BookService.BookService;
import Sem4.TravelTour.service.MailService.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class SendMailUtil {
    @Autowired
    SendMailService sendMailService;
    @Autowired
    BookDetailService bookDetailService;
    @Autowired
    BookService bookService;
    public void sendMailBook(Book book){
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        List<BookDetail> listBookDetail = bookDetailService.findByBook(book);
        StringBuilder content = new StringBuilder();
        content.append(HEADER);
        for (BookDetail bookDetail : listBookDetail){
            content.append("<tr>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;text-align: center;\">\r\n"
                    + "                                                        <img style=\"width: 85%;\" src="
                    + bookDetail.getTour().getImage() + ">\r\n"
                    + "                                                    </td>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\"> "
                    + bookDetail.getTour().getName() + " </td>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\"> "
                    + bookDetail.getQuantity() + " </td>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\"> "
                    + format(String.valueOf(bookDetail.getPrice())) + " </td>\r\n"
                    + "                                                </tr>");
        }
        content.append(BODY2);
        content.append(
                "<td width=\"55%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;\"> Tổng tiền: </td>\r\n"
                        + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee; color: red;\"> "
                        + format(String.valueOf(book.getAmount())) + " </td>");
        content.append(BODY3);
        content.append(
                "<td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                        + "                                                            <p style=\"font-weight: 800;\">Địa chỉ nhận vé</p>\r\n"
                        + "                                                            <p>" + book.getAddress()
                        + "</p>\r\n" + "                                                        </td>\r\n"
                        + "                                                    </tr>\r\n"
                        + "                                                </table>\r\n"
                        + "                                            </div>\r\n"
                        + "                                            <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\r\n"
                        + "                                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\r\n"
                        + "                                                    <tr>\r\n"
                        + "                                                        <td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                        + "                                                            <p style=\"font-weight: 800;\">Ngày đặt</p>\r\n"
                        + "                                                            <p>"
                        + dt.format(book.getBookDate()) + "</p>\r\n"
                        + "                                                        </td>\r\n"
                        + "                                                    </tr>\r\n"
                        + "                                                </table>\r\n"
                        + "                                            </div>\r\n"
                        + "                                            <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\r\n"
                        + "                                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\r\n"
                        + "                                                    <tr>\r\n"
                        + "                                                        <td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                        + "                                                            <p style=\"font-weight: 800;\">Tên người đặt</p>\r\n"
                        + "                                                            <p>" + book.getUser().getName()
                        + "</p>\r\n" + "                                                        </td>\r\n"
                        + "                                                    </tr>\r\n"
                        + "                                                </table>\r\n"
                        + "                                            </div>\r\n"
                        + "                                            <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\r\n"
                        + "                                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\r\n"
                        + "                                                    <tr>\r\n"
                        + "                                                        <td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                        + "                                                            <p style=\"font-weight: 800;\">Số điện thoại</p>\r\n"
                        + "                                                            <p>" + book.getPhone()
                        + "</p>\r\n" + "                                                        </td>");
        content.append(FOOTER);
        sendMailService.queue(book.getUser().getEmail(),"Đặt chỗ thành công",content.toString());
    }

    public void sendMailBookCancel (Book book){
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        List<BookDetail> listBookDetail = bookDetailService.findByBook(book);
        StringBuilder content = new StringBuilder();
        content.append(HEADERCANCEL);
        for (BookDetail bookDetail : listBookDetail){
            content.append("<tr>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;text-align: center;\">\r\n"
                    + "                                                        <img style=\"width: 85%;\" src="
                    + bookDetail.getTour().getImage() + ">\r\n"
                    + "                                                    </td>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\"> "
                    + bookDetail.getTour().getName() + " </td>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\"> "
                    + bookDetail.getQuantity() + " </td>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\"> "
                    + format(String.valueOf(bookDetail.getPrice())) + " </td>\r\n"
                    + "                                                </tr>");
            content.append(BODY2);
            content.append(
                    "<td width=\"55%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;\"> Tổng tiền: </td>\r\n"
                            + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee; color: red;\"> "
                            + format(String.valueOf(book.getAmount())) + " </td>");
            content.append(BODY3);
            content.append(
                    "<td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                            + "                                                            <p style=\"font-weight: 800;\">Địa chỉ nhận vé</p>\r\n"
                            + "                                                            <p>" + book.getAddress()
                            + "</p>\r\n" + "                                                        </td>\r\n"
                            + "                                                    </tr>\r\n"
                            + "                                                </table>\r\n"
                            + "                                            </div>\r\n"
                            + "                                            <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\r\n"
                            + "                                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\r\n"
                            + "                                                    <tr>\r\n"
                            + "                                                        <td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                            + "                                                            <p style=\"font-weight: 800;\">Ngày đặt</p>\r\n"
                            + "                                                            <p>"
                            + dt.format(book.getBookDate()) + "</p>\r\n"
                            + "                                                        </td>\r\n"
                            + "                                                    </tr>\r\n"
                            + "                                                </table>\r\n"
                            + "                                            </div>\r\n"
                            + "                                            <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\r\n"
                            + "                                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\r\n"
                            + "                                                    <tr>\r\n"
                            + "                                                        <td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                            + "                                                            <p style=\"font-weight: 800;\">Tên người đặt</p>\r\n"
                            + "                                                            <p>" + book.getUser().getName()
                            + "</p>\r\n" + "                                                        </td>\r\n"
                            + "                                                    </tr>\r\n"
                            + "                                                </table>\r\n"
                            + "                                            </div>\r\n"
                            + "                                            <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\r\n"
                            + "                                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\r\n"
                            + "                                                    <tr>\r\n"
                            + "                                                        <td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                            + "                                                            <p style=\"font-weight: 800;\">Số điện thoại</p>\r\n"
                            + "                                                            <p>" + book.getPhone()
                            + "</p>\r\n" + "                                                        </td>");
            content.append(FOOTER);
            sendMailService.queue(book.getUser().getEmail(),"Hủy đặt chỗ thành công",content.toString());
        }
    }

    public void sendMailBookConfirm(Book book){
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        List<BookDetail> listBookDetail = bookDetailService.findByBook(book);
        StringBuilder content = new StringBuilder();
        content.append(HEADERCONFIRM);
        for (BookDetail bookDetail : listBookDetail){
            content.append("<tr>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;text-align: center;\">\r\n"
                    + "                                                        <img style=\"width: 85%;\" src="
                    + bookDetail.getTour().getImage() + ">\r\n"
                    + "                                                    </td>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\"> "
                    + bookDetail.getTour().getName() + " </td>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\"> "
                    + bookDetail.getQuantity() + " </td>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\"> "
                    + format(String.valueOf(bookDetail.getPrice())) + " </td>\r\n"
                    + "                                                </tr>");
        }
        content.append(BODY2);
        content.append(
                "<td width=\"55%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;\"> Tổng tiền: </td>\r\n"
                        + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee; color: red;\"> "
                        + format(String.valueOf(book.getAmount())) + " </td>");
        content.append(BODY3);
        content.append(
                "<td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                        + "                                                            <p style=\"font-weight: 800;\">Địa chỉ nhận vé</p>\r\n"
                        + "                                                            <p>" + book.getAddress()
                        + "</p>\r\n" + "                                                        </td>\r\n"
                        + "                                                    </tr>\r\n"
                        + "                                                </table>\r\n"
                        + "                                            </div>\r\n"
                        + "                                            <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\r\n"
                        + "                                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\r\n"
                        + "                                                    <tr>\r\n"
                        + "                                                        <td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                        + "                                                            <p style=\"font-weight: 800;\">Ngày đặt</p>\r\n"
                        + "                                                            <p>"
                        + dt.format(book.getBookDate()) + "</p>\r\n"
                        + "                                                        </td>\r\n"
                        + "                                                    </tr>\r\n"
                        + "                                                </table>\r\n"
                        + "                                            </div>\r\n"
                        + "                                            <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\r\n"
                        + "                                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\r\n"
                        + "                                                    <tr>\r\n"
                        + "                                                        <td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                        + "                                                            <p style=\"font-weight: 800;\">Tên người đặt</p>\r\n"
                        + "                                                            <p>" + book.getUser().getName()
                        + "</p>\r\n" + "                                                        </td>\r\n"
                        + "                                                    </tr>\r\n"
                        + "                                                </table>\r\n"
                        + "                                            </div>\r\n"
                        + "                                            <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\r\n"
                        + "                                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\r\n"
                        + "                                                    <tr>\r\n"
                        + "                                                        <td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                        + "                                                            <p style=\"font-weight: 800;\">Số điện thoại</p>\r\n"
                        + "                                                            <p>" + book.getPhone()
                        + "</p>\r\n" + "                                                        </td>");
        content.append(FOOTER);
        sendMailService.queue(book.getUser().getEmail(),"Xác nhận đặt chỗ thành công",content.toString());
    }

    public void sendMailBookThanks (Book book){
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        List<BookDetail> listBookDetail = bookDetailService.findByBook(book);
        StringBuilder content = new StringBuilder();
        content.append(HEADERTHANKS);
        for (BookDetail bookDetail: listBookDetail){
            content.append("<tr>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;text-align: center;\">\r\n"
                    + "                                                        <img style=\"width: 85%;\" src="
                    + bookDetail.getTour().getImage() + ">\r\n"
                    + "                                                    </td>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\"> "
                    + bookDetail.getTour().getName() + " </td>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\"> "
                    + bookDetail.getQuantity() + " </td>\r\n"
                    + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 18px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\"> "
                    + format(String.valueOf(bookDetail.getPrice())) + " </td>\r\n"
                    + "                                                </tr>");
        }
        content.append(BODY2);
        content.append(
                "<td width=\"55%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;\"> Tổng tiền: </td>\r\n"
                        + "                                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee; color: red;\"> "
                        + format(String.valueOf(book.getAmount())) + " </td>");
        content.append(BODY3);
        content.append(
                "<td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                        + "                                                            <p style=\"font-weight: 800;\">Địa chỉ nhận vé</p>\r\n"
                        + "                                                            <p>" + book.getAddress()
                        + "</p>\r\n" + "                                                        </td>\r\n"
                        + "                                                    </tr>\r\n"
                        + "                                                </table>\r\n"
                        + "                                            </div>\r\n"
                        + "                                            <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\r\n"
                        + "                                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\r\n"
                        + "                                                    <tr>\r\n"
                        + "                                                        <td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                        + "                                                            <p style=\"font-weight: 800;\">Ngày đặt</p>\r\n"
                        + "                                                            <p>"
                        + dt.format(book.getBookDate()) + "</p>\r\n"
                        + "                                                        </td>\r\n"
                        + "                                                    </tr>\r\n"
                        + "                                                </table>\r\n"
                        + "                                            </div>\r\n"
                        + "                                            <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\r\n"
                        + "                                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\r\n"
                        + "                                                    <tr>\r\n"
                        + "                                                        <td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                        + "                                                            <p style=\"font-weight: 800;\">Tên người đặt</p>\r\n"
                        + "                                                            <p>" + book.getUser().getName()
                        + "</p>\r\n" + "                                                        </td>\r\n"
                        + "                                                    </tr>\r\n"
                        + "                                                </table>\r\n"
                        + "                                            </div>\r\n"
                        + "                                            <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\r\n"
                        + "                                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\r\n"
                        + "                                                    <tr>\r\n"
                        + "                                                        <td align=\"center\" valign=\"top\" style=\"font-family: Open sans-serif; font-size: 20px; font-weight: 400; line-height: 24px;\">\r\n"
                        + "                                                            <p style=\"font-weight: 800;\">Số điện thoại</p>\r\n"
                        + "                                                            <p>" + book.getPhone()
                        + "</p>\r\n" + "                                                        </td>");
        content.append(FOOTER);
        sendMailService.queue(book.getUser().getEmail(),"Cảm ơn đã đặt chỗ",content.toString());
    }
    static String HEADER = "<h1>Thông tin đặt phòng</h1>";
    static String HEADERCANCEL = "<h1>Thông tin hủy đặt phòng</h1>";
    static String HEADERCONFIRM = "<h1>Thông tin xác nhận đặt phòng</h1>";
    static String HEADERTHANKS = "<h1>Thông tin cảm ơn</h1>";
    static String BODY2 = "<h2>Chúc quý khách có chuyến đi vui vẻ</h2>";
    static String BODY3 = "<h3>Trân trọng cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi</h3>";
    static String FOOTER = "<p>Đây là email tự động, vui lòng không trả lời lại email này</p>";
    public String format(String number) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");

        return formatter.format(Double.valueOf(number)) + " VNĐ";
    }
}
