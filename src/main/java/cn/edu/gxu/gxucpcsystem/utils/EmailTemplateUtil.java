package cn.edu.gxu.gxucpcsystem.utils;

public class EmailTemplateUtil {
    // 东信杯报名成功通知模板
    public static String DongXinSignUpSuccessTem = "<html>\n" +
            "\n" +
            "  <head>\n" +
            "\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
            "\n" +
            "    <title>广西大学程序设计竞赛</title>\n" +
            "\n" +
            "    <style type=\"text/css\">\n" +
            "\n" +
            "      #template_body a {\n" +
            "\n" +
            "        color: #4ca6cf;\n" +
            "\n" +
            "      }\n" +
            "\n" +
            "    </style>\n" +
            "\n" +
            "    <style type=\"text/css\" id=\"custom-css\"></style>\n" +
            "\n" +
            "  </head>\n" +
            "\n" +
            "  <body\n" +
            "\n" +
            "    leftmargin=\"0\"\n" +
            "\n" +
            "    marginwidth=\"0\"\n" +
            "\n" +
            "    topmargin=\"0\"\n" +
            "\n" +
            "    marginheight=\"0\"\n" +
            "\n" +
            "    offset=\"0\"\n" +
            "\n" +
            "  >\n" +
            "\n" +
            "    <div\n" +
            "\n" +
            "      id=\"body\"\n" +
            "\n" +
            "      style=\"\n" +
            "\n" +
            "        background-color: #e3e3e3;\n" +
            "\n" +
            "        width: 100%;\n" +
            "\n" +
            "        -webkit-text-size-adjust: none !important;\n" +
            "\n" +
            "        margin: 0;\n" +
            "\n" +
            "        padding: 70px 0 70px 0;\n" +
            "\n" +
            "      \"\n" +
            "\n" +
            "    >\n" +
            "\n" +
            "      <table\n" +
            "\n" +
            "        border=\"0\"\n" +
            "\n" +
            "        cellpadding=\"0\"\n" +
            "\n" +
            "        cellspacing=\"0\"\n" +
            "\n" +
            "        height=\"100%\"\n" +
            "\n" +
            "        width=\"100%\"\n" +
            "\n" +
            "      >\n" +
            "\n" +
            "        <tr>\n" +
            "\n" +
            "          <td align=\"center\" valign=\"top\">\n" +
            "\n" +
            "            <table\n" +
            "\n" +
            "              border=\"0\"\n" +
            "\n" +
            "              cellpadding=\"0\"\n" +
            "\n" +
            "              cellspacing=\"0\"\n" +
            "\n" +
            "              id=\"template_container\"\n" +
            "\n" +
            "              style=\"\n" +
            "\n" +
            "                -webkit-box-shadow: 0 0 0 3px rgba(0, 0, 0, 0.025) !important;\n" +
            "\n" +
            "                box-shadow: 0 0 0 3px rgba(0, 0, 0, 0.025) !important;\n" +
            "\n" +
            "                -webkit-border-radius: 6px !important;\n" +
            "\n" +
            "                border-radius: 6px !important;\n" +
            "\n" +
            "                background-color: #fafafa;\n" +
            "\n" +
            "                border-radius: 6px !important;\n" +
            "\n" +
            "                width: 100%;\n" +
            "\n" +
            "                max-width: 680px;\n" +
            "\n" +
            "              \"\n" +
            "\n" +
            "            >\n" +
            "\n" +
            "              <tr>\n" +
            "\n" +
            "                <td align=\"center\" valign=\"top\">\n" +
            "\n" +
            "                  <!-- Header -->\n" +
            "\n" +
            "                  <table\n" +
            "\n" +
            "                    border=\"0\"\n" +
            "\n" +
            "                    cellpadding=\"0\"\n" +
            "\n" +
            "                    cellspacing=\"0\"\n" +
            "\n" +
            "                    width=\"100%\"\n" +
            "\n" +
            "                    id=\"template_header\"\n" +
            "\n" +
            "                    style=\"\n" +
            "\n" +
            "                      background-color: #454545;\n" +
            "\n" +
            "                      color: #f1f1f1;\n" +
            "\n" +
            "                      -webkit-border-top-left-radius: 6px !important;\n" +
            "\n" +
            "                      -webkit-border-top-right-radius: 6px !important;\n" +
            "\n" +
            "                      border-top-left-radius: 6px !important;\n" +
            "\n" +
            "                      border-top-right-radius: 6px !important;\n" +
            "\n" +
            "                      border-bottom: 0;\n" +
            "\n" +
            "                      font-family: Arial;\n" +
            "\n" +
            "                      font-weight: bold;\n" +
            "\n" +
            "                      line-height: 100%;\n" +
            "\n" +
            "                      vertical-align: middle;\n" +
            "\n" +
            "                    \"\n" +
            "\n" +
            "                  >\n" +
            "\n" +
            "                    <tr>\n" +
            "\n" +
            "                      <td>\n" +
            "\n" +
            "                        <h1\n" +
            "\n" +
            "                          style=\"\n" +
            "\n" +
            "                            color: #f1f1f1;\n" +
            "\n" +
            "                            margin: 0;\n" +
            "\n" +
            "                            padding: 28px 24px;\n" +
            "\n" +
            "                            display: block;\n" +
            "\n" +
            "                            font-family: Arial;\n" +
            "\n" +
            "                            font-size: 30px;\n" +
            "\n" +
            "                            font-weight: bold;\n" +
            "\n" +
            "                            text-align: center;\n" +
            "\n" +
            "                            line-height: 150%;\n" +
            "\n" +
            "                          \"\n" +
            "\n" +
            "                          id=\"logo\"\n" +
            "\n" +
            "                        >\n" +
            "\n" +
            "                          <a\n" +
            "\n" +
            "                            style=\"color: #f1f1f1; text-decoration: none\"\n" +
            "\n" +
            "                            href=\"https://gxucpc.gxuca.team\"\n" +
            "\n" +
            "                            title=\"广西大学程序设计竞赛\"\n" +
            "\n" +
            "                            >广西大学程序设计竞赛\n" +
            "\n" +
            "                          </a>\n" +
            "\n" +
            "                        </h1>\n" +
            "\n" +
            "                      </td>\n" +
            "\n" +
            "                    </tr>\n" +
            "\n" +
            "                  </table>\n" +
            "\n" +
            "                  <!-- End Header -->\n" +
            "\n" +
            "                </td>\n" +
            "\n" +
            "              </tr>\n" +
            "\n" +
            "              <tr>\n" +
            "\n" +
            "                <td align=\"center\" valign=\"top\">\n" +
            "\n" +
            "                  <!-- Body -->\n" +
            "\n" +
            "                  <table\n" +
            "\n" +
            "                    border=\"0\"\n" +
            "\n" +
            "                    cellpadding=\"0\"\n" +
            "\n" +
            "                    cellspacing=\"0\"\n" +
            "\n" +
            "                    width=\"100%\"\n" +
            "\n" +
            "                    id=\"template_body\"\n" +
            "\n" +
            "                  >\n" +
            "\n" +
            "                    <tr>\n" +
            "\n" +
            "                      <td\n" +
            "\n" +
            "                        valign=\"top\"\n" +
            "\n" +
            "                        style=\"background-color: #fafafa\"\n" +
            "\n" +
            "                        id=\"mailtpl_body_bg\"\n" +
            "\n" +
            "                      >\n" +
            "\n" +
            "                        <!-- Content -->\n" +
            "\n" +
            "                        <table\n" +
            "\n" +
            "                          border=\"0\"\n" +
            "\n" +
            "                          cellpadding=\"20\"\n" +
            "\n" +
            "                          cellspacing=\"0\"\n" +
            "\n" +
            "                          width=\"100%\"\n" +
            "\n" +
            "                        >\n" +
            "\n" +
            "                          <tr>\n" +
            "\n" +
            "                            <td valign=\"top\">\n" +
            "\n" +
            "                              <div\n" +
            "\n" +
            "                                style=\"\n" +
            "\n" +
            "                                  color: #888;\n" +
            "\n" +
            "                                  font-family: Arial;\n" +
            "\n" +
            "                                  font-size: 14px;\n" +
            "\n" +
            "                                  line-height: 150%;\n" +
            "\n" +
            "                                  text-align: left;\n" +
            "\n" +
            "                                \"\n" +
            "\n" +
            "                                id=\"mailtpl_body\"\n" +
            "\n" +
            "                              >\n" +
            "\n" +
            "                              <!-- “搜索标记1” -->\n" +
            "\n" +
            "                                <p align = \"left\">%s：</p>\n" +
            "\n" +
            "                                <p style=\"text-indent:2em;\">组委会已收到你的参赛申请，请验证你以下的报名信息是否正确！</p>\n" +
            "                                <p style=\"text-indent:2em;\">姓名：%s</p>\n" +
            "                                <p style=\"text-indent:2em;\">学院：%s</p>\n" +
            "                                <p style=\"text-indent:2em;\">性别：%s</p>\n" +
            "                                <p style=\"text-indent:2em;\">学号：%s</p>\n" +
            "                                <p style=\"text-indent:2em;\">报名组别：%s</p>\n" +
            "                                <p style=\"text-indent:2em;\">参赛方式：%s</p>\n" +
            "\n" +
            "                                <p style=\"text-indent:2em;\">祝:武运昌隆</p>\n" +
            "\n" +
            "                                <a href=\"https://cpc.gxuca.team\">广西大学程序设计竞赛官方网站:https://cpc.gxuca.team</a>\n" +
            "\n" +
            "                                <p align = \"right\">广西大学程序设计竞赛组委会</p>\n" +
            "\n" +
            "                                <p align = \"right\">%s</p>\n" +
            "\n" +
            "                              </div>\n" +
            "\n" +
            "                            </td>\n" +
            "\n" +
            "                          </tr>\n" +
            "\n" +
            "                        </table>\n" +
            "\n" +
            "                        <!-- End Content -->\n" +
            "\n" +
            "                      </td>\n" +
            "\n" +
            "                    </tr>\n" +
            "\n" +
            "                  </table>\n" +
            "\n" +
            "                  <!-- End Body -->\n" +
            "\n" +
            "                </td>\n" +
            "\n" +
            "              </tr>\n" +
            "\n" +
            "              <tr>\n" +
            "\n" +
            "                <td align=\"center\" valign=\"top\">\n" +
            "\n" +
            "                  <!-- Footer -->\n" +
            "\n" +
            "                  <table\n" +
            "\n" +
            "                    border=\"0\"\n" +
            "\n" +
            "                    cellpadding=\"10\"\n" +
            "\n" +
            "                    cellspacing=\"0\"\n" +
            "\n" +
            "                    width=\"100%\"\n" +
            "\n" +
            "                    id=\"template_footer\"\n" +
            "\n" +
            "                    style=\"\n" +
            "\n" +
            "                      border-top: 1px solid #e2e2e2;\n" +
            "\n" +
            "                      background: #eee;\n" +
            "\n" +
            "                      -webkit-border-radius: 0px 0px 6px 6px;\n" +
            "\n" +
            "                      -o-border-radius: 0px 0px 6px 6px;\n" +
            "\n" +
            "                      -moz-border-radius: 0px 0px 6px 6px;\n" +
            "\n" +
            "                      border-radius: 0px 0px 6px 6px;\n" +
            "\n" +
            "                    \"\n" +
            "\n" +
            "                  >\n" +
            "\n" +
            "                    <tr>\n" +
            "\n" +
            "                      <td valign=\"top\">\n" +
            "\n" +
            "                        <table\n" +
            "\n" +
            "                          border=\"0\"\n" +
            "\n" +
            "                          cellpadding=\"10\"\n" +
            "\n" +
            "                          cellspacing=\"0\"\n" +
            "\n" +
            "                          width=\"100%\"\n" +
            "\n" +
            "                        >\n" +
            "\n" +
            "                          <tr>\n" +
            "\n" +
            "                            <td\n" +
            "\n" +
            "                              colspan=\"2\"\n" +
            "\n" +
            "                              valign=\"middle\"\n" +
            "\n" +
            "                              id=\"credit\"\n" +
            "\n" +
            "                              style=\"\n" +
            "\n" +
            "                                border: 0;\n" +
            "\n" +
            "                                color: #777;\n" +
            "\n" +
            "                                font-family: Arial;\n" +
            "\n" +
            "                                font-size: 12px;\n" +
            "\n" +
            "                                line-height: 125%;\n" +
            "\n" +
            "                                text-align: center;\n" +
            "\n" +
            "                              \"\n" +
            "\n" +
            "                            >\n" +
            "\n" +
            "                              &copy;%s 广西大学程序设计竞赛组委会\n" +
            "\n" +
            "                            </td>\n" +
            "\n" +
            "                          </tr>\n" +
            "\n" +
            "                        </table>\n" +
            "\n" +
            "                      </td>\n" +
            "\n" +
            "                    </tr>\n" +
            "\n" +
            "                  </table>\n" +
            "\n" +
            "                  <!-- End Footer -->\n" +
            "\n" +
            "                </td>\n" +
            "\n" +
            "              </tr>\n" +
            "\n" +
            "            </table>\n" +
            "\n" +
            "          </td>\n" +
            "\n" +
            "        </tr>\n" +
            "\n" +
            "      </table>\n" +
            "\n" +
            "    </div>\n" +
            "\n" +
            "  </body>\n" +
            "\n" +
            "</html>";
}
