package cn.edu.gxu.gxucpcsystem.utils;

/**
 * @Author: MaoMao
 * @License: (C) Copyright 2005-2019, xxx Corporation Limited.
 * @Contact: 2986325137@qq.com
 * @Date: 9/6/2022 7:32 PM
 * @Version: 1.0
 * @Description:
 */

import cn.edu.gxu.gxucpcsystem.domain.LqPlayer;
import cn.edu.gxu.gxucpcsystem.domain.Medal;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;


import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


/**
 * ExcelHandle
 *
 * @author gongweixin
 * @date 2021/3/15
 */
@Slf4j
public class ExcelHandle {

    private static Map<Integer, List<String>> heads = Maps.newHashMap();

    static {
        heads.put(1, Lists.newArrayList("编号", "学号", "姓名", "性别", "学院", "班级", "QQ", "邮箱", "手机号", "是否打星", "组别"));
        heads.put(2, Lists.newArrayList("编号", "学号", "姓名", "性别", "学院", "班级", "QQ", "邮箱", "手机号", "折扣", "转账截图"));
    }

    /**
     * Excel表格导出
     *
     * @param excelData   Excel表格的数据，封装为List<Player>
     * @param sheetName   sheet的名字
     * @param fileName    导出Excel的文件名
     * @param columnWidth Excel表格的宽度，建议为15
     * @throws IOException 抛IO异常
     */
    public static void exportExcel(HttpServletResponse response, List<Player> excelData, String sheetName, String fileName, int columnWidth) throws IOException {
        fileName += ".xls";
        setResponseHeader(response, fileName);
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格，设置表格名称
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //设置表格列宽度
        sheet.setDefaultColumnWidth(columnWidth);
        //写入List<List<String>>中的数据
        int rowIndex = 0;
        HSSFRow r = sheet.createRow(rowIndex++);

        List<String> head = heads.get(1);
        for (int i = 0; i < head.size(); i++) {
            HSSFCell cell = r.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(head.get(i));
            cell.setCellValue(text);
        }

        for (Player data : excelData) {
            //创建一个row行，然后自增1
            r = sheet.createRow(rowIndex++);
            HSSFCell cell = r.createCell(0);
            cell.setCellValue(new HSSFRichTextString(String.valueOf(data.getInformationId())));
            cell = r.createCell(1);
            cell.setCellValue(new HSSFRichTextString(data.getUserId()));
            cell = r.createCell(2);
            cell.setCellValue(new HSSFRichTextString(data.getUserName()));
            cell = r.createCell(3);
            cell.setCellValue(new HSSFRichTextString(data.getUserSex()));
            cell = r.createCell(4);
            cell.setCellValue(new HSSFRichTextString(data.getUserCourse()));
            cell = r.createCell(5);
            cell.setCellValue(new HSSFRichTextString(data.getUserClass()));
            cell = r.createCell(6);
            cell.setCellValue(new HSSFRichTextString(data.getUserQQ()));
            cell = r.createCell(7);
            cell.setCellValue(new HSSFRichTextString(data.getUserMail()));
            cell = r.createCell(8);
            cell.setCellValue(new HSSFRichTextString(data.getUserPhone()));
            cell = r.createCell(9);
            if (data.getStar()) {
                cell.setCellValue(new HSSFRichTextString("打星"));
            } else {
                cell.setCellValue(new HSSFRichTextString("非打星"));
            }
            cell = r.createCell(10);
            if (data.getGroup()) {
                cell.setCellValue(new HSSFRichTextString("正式组"));
            } else {
                cell.setCellValue(new HSSFRichTextString("新生组"));
            }
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        workbook.write(response.getOutputStream());

        workbook.close();

    }


    public static void exportExcelLQ(HttpServletResponse response, List<LqPlayer> excelData, Map<String, byte[]> imgs, String sheetName, String fileName, int columnWidth) throws IOException {
        fileName += ".xls";
        setResponseHeader(response, fileName);
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格，设置表格名称
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //设置表格列宽度
        sheet.setDefaultColumnWidth(columnWidth);
        //写入List<List<String>>中的数据
        int rowIndex = 0;
        HSSFRow r = sheet.createRow(rowIndex++);

        List<String> head = heads.get(2);
        for (int i = 0; i < head.size(); i++) {
            HSSFCell cell = r.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(head.get(i));
            cell.setCellValue(text);
        }

        for (LqPlayer data : excelData) {
            //创建一个row行，然后自增1
            r = sheet.createRow(rowIndex++);
            HSSFCell cell = r.createCell(0);
            cell.setCellValue(new HSSFRichTextString(String.valueOf(rowIndex)));
            cell = r.createCell(1);
            cell.setCellValue(new HSSFRichTextString(data.getUserId()));
            cell = r.createCell(2);
            cell.setCellValue(new HSSFRichTextString(data.getUserName()));
            cell = r.createCell(3);
            cell.setCellValue(new HSSFRichTextString(data.getUserSex()));
            cell = r.createCell(4);
            cell.setCellValue(new HSSFRichTextString(data.getUserCourse()));
            cell = r.createCell(5);
            cell.setCellValue(new HSSFRichTextString(data.getUserClass()));
            cell = r.createCell(6);
            cell.setCellValue(new HSSFRichTextString(data.getUserQQ()));
            cell = r.createCell(7);
            cell.setCellValue(new HSSFRichTextString(data.getUserMail()));
            cell = r.createCell(8);
            cell.setCellValue(new HSSFRichTextString(data.getUserPhone()));
            cell = r.createCell(9);
            cell.setCellValue(new HSSFRichTextString(data.getIsDiscount() == 1 ? "无折扣" : "九折"));
            cell = r.createCell(10);
            CreationHelper creationHelper = workbook.getCreationHelper();
            Hyperlink hyperlink = creationHelper.createHyperlink(HyperlinkType.URL);
            hyperlink.setAddress("https://acm.gxu.edu.cn/api/public/image/" + data.getImgURI());
            cell.setHyperlink(hyperlink);
            cell.setCellValue("https://acm.gxu.edu.cn/api/public/image/" + data.getImgURI());
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        workbook.write(response.getOutputStream());

        workbook.close();
    }

    private static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
