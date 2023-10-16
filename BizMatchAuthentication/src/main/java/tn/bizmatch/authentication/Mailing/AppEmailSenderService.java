package tn.bizmatch.authentication.Mailing;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.bizmatch.authentication.entities.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;


@Service
public class AppEmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMailWithAttachement(User user) {


        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("fatma.maazoun21@gmail.com");
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setSubject("Reset Password");
            mimeMessageHelper.setText(MailContent(user.getId(),user.getEmail(),LocalDate.now()), true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


        mailSender.send(mimeMessage);
        System.out.println("Mail with attachment sent successfully..");


    }


    public String MailContent(int id,String eemail,LocalDate date) {
       return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
               "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
               "\n" +
               "<head>\n" +
               "    <meta charset=\"UTF-8\">\n" +
               "    <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
               "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
               "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
               "    <meta content=\"telephone=no\" name=\"format-detection\">\n" +
               "    <title></title>\n" +
               "    <!--[if (mso 16)]>\n" +
               "    <style type=\"text/css\">\n" +
               "    a {text-decoration: none;}\n" +
               "    </style>\n" +
               "    <![endif]-->\n" +
               "    <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]-->\n" +
               "    <!--[if gte mso 9]>\n" +
               "<xml>\n" +
               "    <o:OfficeDocumentSettings>\n" +
               "    <o:AllowPNG></o:AllowPNG>\n" +
               "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
               "    </o:OfficeDocumentSettings>\n" +
               "</xml>\n" +
               "<![endif]-->\n" +
               "    <!--[if !mso]><!-- -->\n" +
               "    <link href=\"https://fonts.googleapis.com/css?family=Merriweather:400,400i,700,700i\" rel=\"stylesheet\">\n" +
               "    <!--<![endif]-->\n" +
               "</head>\n" +
               "\n" +
               "<body>\n" +
               "    <div class=\"es-wrapper-color\">\n" +
               "        <!--[if gte mso 9]>\n" +
               "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
               "\t\t\t\t<v:fill type=\"tile\" color=\"#fafafa\"></v:fill>\n" +
               "\t\t\t</v:background>\n" +
               "\t\t<![endif]-->\n" +
               "        <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
               "            <tbody>\n" +
               "                <tr>\n" +
               "                    <td class=\"esd-email-paddings\" valign=\"top\">\n" +
               "                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header esd-header-popover\" align=\"center\">\n" +
               "                            <tbody>\n" +
               "                                <tr>\n" +
               "                                    <td class=\"esd-stripe\" align=\"center\" esd-custom-block-id=\"388981\" bgcolor=\"#efefef\" style=\"background-color: #efefef;\">\n" +
               "                                        <table class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"background-color: #ffffff;\" bgcolor=\"#ffffff\">\n" +
               "                                            <tbody>\n" +
               "                                                <tr>\n" +
               "                                                    <td class=\"esd-structure es-p10t es-p20r es-p20l\" align=\"left\" bgcolor=\"#ffffff\" style=\"background-color: #ffffff;\">\n" +
               "                                                        <!--[if mso]><table width=\"560\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"510\" valign=\"top\"><![endif]-->\n" +
               "                                                        <table cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"es-left\">\n" +
               "                                                            <tbody>\n" +
               "                                                                <tr>\n" +
               "                                                                    <td width=\"510\" class=\"es-m-p0r esd-container-frame es-m-p20b\" valign=\"top\" align=\"center\">\n" +
               "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#ffffff\" style=\"background-color: #ffffff;\">\n" +
               "                                                                            <tbody>\n" +
               "                                                                                <tr>\n" +
               "                                                                                    <td align=\"left\" class=\"esd-block-image es-p40r\" style=\"font-size: 0px;\"><a target=\"_blank\"><img class=\"adapt-img\" src=\"https://mhttoy.stripocdn.email/content/guids/CABINET_f4a00054d828fdf3bfa3e8ed9d0fb646189a69c9695247b6330198543211d0f2/images/logo_bizmatchremovebgpreview.png\" alt style=\"display: block;\" width=\"263\"></a></td>\n" +
               "                                                                                </tr>\n" +
               "                                                                            </tbody>\n" +
               "                                                                        </table>\n" +
               "                                                                    </td>\n" +
               "                                                                </tr>\n" +
               "                                                            </tbody>\n" +
               "                                                        </table>\n" +
               "                                                        <!--[if mso]></td><td width=\"20\"></td><td width=\"30\" valign=\"top\"><![endif]-->\n" +
               "                                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\">\n" +
               "                                                            <tbody>\n" +
               "                                                                <tr>\n" +
               "                                                                    <td width=\"30\" align=\"left\" class=\"esd-container-frame\">\n" +
               "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
               "                                                                            <tbody>\n" +
               "                                                                                <tr>\n" +
               "                                                                                    <td align=\"left\" class=\"esd-block-text es-p40t\">\n" +
               "                                                                                        <p><strong>"+date+"</strong></p>\n" +
               "                                                                                    </td>\n" +
               "                                                                                </tr>\n" +
               "                                                                            </tbody>\n" +
               "                                                                        </table>\n" +
               "                                                                    </td>\n" +
               "                                                                </tr>\n" +
               "                                                            </tbody>\n" +
               "                                                        </table>\n" +
               "                                                        <!--[if mso]></td></tr></table><![endif]-->\n" +
               "                                                    </td>\n" +
               "                                                </tr>\n" +
               "                                            </tbody>\n" +
               "                                        </table>\n" +
               "                                    </td>\n" +
               "                                </tr>\n" +
               "                            </tbody>\n" +
               "                        </table>\n" +
               "                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\">\n" +
               "                            <tbody>\n" +
               "                                <tr>\n" +
               "                                    <td class=\"esd-stripe\" align=\"center\" bgcolor=\"#efefef\" style=\"background-color: #efefef;\">\n" +
               "                                        <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
               "                                            <tbody>\n" +
               "                                                <tr>\n" +
               "                                                    <td class=\"esd-structure es-p15t es-p20r es-p20l\" align=\"left\">\n" +
               "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
               "                                                            <tbody>\n" +
               "                                                                <tr>\n" +
               "                                                                    <td width=\"560\" class=\"esd-container-frame\" align=\"center\" valign=\"top\">\n" +
               "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
               "                                                                            <tbody>\n" +
               "                                                                                <tr>\n" +
               "                                                                                    <td align=\"center\" class=\"esd-block-image es-p10t es-p10b\" style=\"font-size: 0px;\"><a target=\"_blank\"><img src=\"https://mhttoy.stripocdn.email/content/guids/CABINET_f4a00054d828fdf3bfa3e8ed9d0fb646189a69c9695247b6330198543211d0f2/images/forgot_passwordpana.png\" alt style=\"display: block;\" width=\"450\"></a></td>\n" +
               "                                                                                </tr>\n" +
               "                                                                                <tr>\n" +
               "                                                                                    <td align=\"center\" class=\"esd-block-text\">\n" +
               "                                                                                        <p style=\"font-size: 26px; color: #333333; font-family: merriweather, georgia, 'times new roman', serif;\"><strong>Forgot&nbsp;</strong></p>\n" +
               "                                                                                        <p style=\"font-size: 26px; color: #333333; font-family: merriweather, georgia, 'times new roman', serif;\"><strong>&nbsp;password ?</strong><br></p>\n" +
               "                                                                                    </td>\n" +
               "                                                                                </tr>\n" +
               "                                                                            </tbody>\n" +
               "                                                                        </table>\n" +
               "                                                                    </td>\n" +
               "                                                                </tr>\n" +
               "                                                            </tbody>\n" +
               "                                                        </table>\n" +
               "                                                    </td>\n" +
               "                                                </tr>\n" +
               "                                                <tr>\n" +
               "                                                    <td class=\"esd-structure es-p20r es-p15l\" align=\"left\">\n" +
               "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
               "                                                            <tbody>\n" +
               "                                                                <tr>\n" +
               "                                                                    <td width=\"565\" class=\"esd-container-frame\" align=\"center\" valign=\"top\">\n" +
               "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
               "                                                                            <tbody>\n" +
               "                                                                                <tr>\n" +
               "                                                                                    <td align=\"left\" class=\"esd-block-text\">\n" +
               "                                                                                        <p style=\"font-size: 19px;\">Hello,</p>\n" +
               "                                                                                        <p style=\"font-size: 19px;\"><br>We’ve received a request to reset the password for the BizMatch account associated with <strong>"+eemail+"</strong>. No changes have been made to your account yet.<br><br>You can reset your password by clicking the link below:</p>\n" +
               "                                                                                        <p style=\"font-size: 19px;\"><br></p>\n" +
               "                                                                                    </td>\n" +
               "                                                                                </tr>\n" +
               "                                                                            </tbody>\n" +
               "                                                                        </table>\n" +
               "                                                                    </td>\n" +
               "                                                                </tr>\n" +
               "                                                            </tbody>\n" +
               "                                                        </table>\n" +
               "                                                    </td>\n" +
               "                                                </tr>\n" +
               "                                                <tr>\n" +
               "                                                    <td class=\"esd-structure es-p30t es-p20b es-p40r es-p40l\" align=\"left\" esd-custom-block-id=\"388973\" bgcolor=\"#ffffff\" style=\"background-color: #ffffff;\">\n" +
               "                                                        <!--[if mso]><table width=\"520\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"138\" valign=\"top\"><![endif]-->\n" +
               "                                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\">\n" +
               "                                                            <tbody>\n" +
               "                                                                <tr>\n" +
               "                                                                    <td width=\"138\" class=\"es-m-p0r esd-container-frame es-m-p20b\" align=\"center\">\n" +
               "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
               "                                                                            <tbody>\n" +
               "                                                                                <tr>\n" +
               "                                                                                    <td align=\"center\" class=\"esd-block-image\" style=\"font-size: 0px;\"><a target=\"_blank\" href=\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTeQV-7pW2PgCx4bVJovm0f0Ry-o4xwd2uggA&usqp=CAU\"><img class=\"adapt-img\" src=\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTeQV-7pW2PgCx4bVJovm0f0Ry-o4xwd2uggA&usqp=CAU\" alt style=\"display: block;\" width=\"138\"></a></td>\n" +
               "                                                                                </tr>\n" +
               "                                                                            </tbody>\n" +
               "                                                                        </table>\n" +
               "                                                                    </td>\n" +
               "                                                                </tr>\n" +
               "                                                            </tbody>\n" +
               "                                                        </table>\n" +
               "                                                        <!--[if mso]></td><td width=\"5\"></td><td width=\"377\" valign=\"top\"><![endif]-->\n" +
               "                                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\">\n" +
               "                                                            <tbody>\n" +
               "                                                                <tr>\n" +
               "                                                                    <td width=\"377\" class=\"esd-container-frame\" align=\"center\">\n" +
               "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#f9cb9c\" style=\"background-color: #f9cb9c; border-radius: 17px; border-collapse: separate;\">\n" +
               "                                                                            <tbody>\n" +
               "                                                                                <tr>\n" +
               "                                                                                    <td align=\"center\" class=\"esd-block-button\" bgcolor=\"#ffffff\">\n" +
               "                                                                                        <!--[if mso]><a href=\"http://localhost:4200/reset/"+id+" target=\"_blank\" hidden>\n" +
               "\t<v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" esdevVmlButton href=\"http://localhost:4200/login/reset"+id+" \n" +
               "                style=\"height:86px; v-text-anchor:middle; width:271px\" arcsize=\"20%\" stroke=\"f\"  fillcolor=\"#cccccc\">\n" +
               "\t\t<w:anchorlock></w:anchorlock>\n" +
               "\t\t<center style='color:#ffffff; font-family:arial, \"helvetica neue\", helvetica, sans-serif; font-size:22px; font-weight:400; line-height:22px;  mso-text-raise:1px'>Reset your password</center>\n" +
               "\t</v:roundrect></a>\n" +
               "<![endif]-->\n" +
               "                                                                                        <!--[if !mso]><!-- --><span class=\"msohide es-button-border\" style=\"background: #cccccc; border-radius: 17px;\"><a href=\"http://localhost:4200/reset/"+id+"\" class=\"es-button es-button-1696230315901\" target=\"_blank\" style=\"font-size: 22px; background: #cccccc; border-radius: 17px; padding: 30px 20px 30px 25px; mso-border-alt: 10px solid #cccccc;\">Reset your password</a></span>\n" +
               "                                                                                        <!--<![endif]-->\n" +
               "                                                                                    </td>\n" +
               "                                                                                </tr>\n" +
               "                                                                            </tbody>\n" +
               "                                                                        </table>\n" +
               "                                                                    </td>\n" +
               "                                                                </tr>\n" +
               "                                                            </tbody>\n" +
               "                                                        </table>\n" +
               "                                                        <!--[if mso]></td></tr></table><![endif]-->\n" +
               "                                                    </td>\n" +
               "                                                </tr>\n" +
               "                                                <tr>\n" +
               "                                                    <td class=\"esd-structure es-p5t es-p5b es-p20r es-p35l\" align=\"left\" esd-custom-block-id=\"388973\">\n" +
               "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
               "                                                            <tbody>\n" +
               "                                                                <tr>\n" +
               "                                                                    <td width=\"545\" class=\"esd-container-frame\" align=\"center\">\n" +
               "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
               "                                                                            <tbody>\n" +
               "                                                                                <tr>\n" +
               "                                                                                    <td align=\"left\" class=\"esd-block-text\">\n" +
               "                                                                                        <p style=\"font-size: 19px;\">We recommend changing your password upon your first login for security reasons. Please ensure that you choose a strong password and keep it secure.</p>\n" +
               "                                                                                    </td>\n" +
               "                                                                                </tr>\n" +
               "                                                                            </tbody>\n" +
               "                                                                        </table>\n" +
               "                                                                    </td>\n" +
               "                                                                </tr>\n" +
               "                                                            </tbody>\n" +
               "                                                        </table>\n" +
               "                                                    </td>\n" +
               "                                                </tr>\n" +
               "                                                <tr>\n" +
               "                                                    <td class=\"esd-structure es-p20t es-p20b es-p20r es-p40l\" align=\"left\" esd-custom-block-id=\"388973\">\n" +
               "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
               "                                                            <tbody>\n" +
               "                                                                <tr>\n" +
               "                                                                    <td width=\"540\" class=\"es-m-p0r esd-container-frame\" align=\"center\">\n" +
               "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
               "                                                                            <tbody>\n" +
               "                                                                                <tr>\n" +
               "                                                                                    <td align=\"left\" class=\"esd-block-text\">\n" +
               "                                                                                        <p style=\"text-align: center;\">If you have any questions or need assistance getting started, don't hesitate to contact our support team. We're here to help you make the most of our application.</p>\n" +
               "                                                                                    </td>\n" +
               "                                                                                </tr>\n" +
               "                                                                                <tr>\n" +
               "                                                                                    <td align=\"left\" class=\"esd-block-text es-p10b es-m-txt-l\">\n" +
               "                                                                                        <h3><br></h3>\n" +
               "                                                                                    </td>\n" +
               "                                                                                </tr>\n" +
               "                                                                            </tbody>\n" +
               "                                                                        </table>\n" +
               "                                                                    </td>\n" +
               "                                                                </tr>\n" +
               "                                                            </tbody>\n" +
               "                                                        </table>\n" +
               "                                                    </td>\n" +
               "                                                </tr>\n" +
               "                                            </tbody>\n" +
               "                                        </table>\n" +
               "                                    </td>\n" +
               "                                </tr>\n" +
               "                            </tbody>\n" +
               "                        </table>\n" +
               "                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content esd-footer-popover\" align=\"center\">\n" +
               "                            <tbody>\n" +
               "                                <tr>\n" +
               "                                    <td class=\"esd-stripe\" align=\"center\" esd-custom-block-id=\"388983\">\n" +
               "                                        <table class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"background-color: transparent;\" bgcolor=\"rgba(0, 0, 0, 0)\">\n" +
               "                                            <tbody>\n" +
               "                                                <tr>\n" +
               "                                                    <td class=\"esd-structure es-p20\" align=\"left\">\n" +
               "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
               "                                                            <tbody>\n" +
               "                                                                <tr>\n" +
               "                                                                    <td width=\"560\" class=\"esd-container-frame\" align=\"center\" valign=\"top\">\n" +
               "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
               "                                                                            <tbody>\n" +
               "                                                                                <tr>\n" +
               "                                                                                    <td align=\"center\" class=\"esd-block-text es-infoblock\">\n" +
               "                                                                                        <p><a target=\"_blank\"></a>No longer want to receive these emails?&nbsp;<a href target=\"_blank\">Unsubscribe</a>.<a target=\"_blank\"></a></p>\n" +
               "                                                                                    </td>\n" +
               "                                                                                </tr>\n" +
               "                                                                            </tbody>\n" +
               "                                                                        </table>\n" +
               "                                                                    </td>\n" +
               "                                                                </tr>\n" +
               "                                                            </tbody>\n" +
               "                                                        </table>\n" +
               "                                                    </td>\n" +
               "                                                </tr>\n" +
               "                                            </tbody>\n" +
               "                                        </table>\n" +
               "                                    </td>\n" +
               "                                </tr>\n" +
               "                            </tbody>\n" +
               "                        </table>\n" +
               "                    </td>\n" +
               "                </tr>\n" +
               "            </tbody>\n" +
               "        </table>\n" +
               "    </div>\n" +
               "</body>\n" +
               "\n" +
               "</html>";
    }

}
