package cn.edu.gxu.gxucpcsystem.utils;

/**
 * @Author: MaoMao
 * @License: (C) Copyright 2005-2019, xxx Corporation Limited.
 * @Contact: 2986325137@qq.com
 * @Date: 9/6/2022 7:32 PM
 * @Version: 1.0
 * @Description:
 */

import cn.edu.gxu.gxucpcsystem.domain.Medal;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import lombok.extern.slf4j.Slf4j;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * ExcelHandle
 *
 * @author gongweixin
 * @date 2021/3/15
 */
@Slf4j
public class ExcelHandle {


    /**
     * Excel表格导出
     * @param response HttpServletResponse对象
     * @param excelData Excel表格的数据，封装为List<List<String>>
     * @param sheetName sheet的名字
     * @param fileName 导出Excel的文件名
     * @param columnWidth Excel表格的宽度，建议为15
     * @throws IOException 抛IO异常
     */
    public static void exportExcel(HttpServletResponse response,
                                   List<Player> excelData,
                                   String sheetName,
                                   String fileName,
                                   int columnWidth) throws IOException {
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格，设置表格名称
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //设置表格列宽度
        sheet.setDefaultColumnWidth(columnWidth);
        //写入List<List<String>>中的数据
        int rowIndex = 0;
        HSSFRow r = sheet.createRow(rowIndex++);
        HSSFCell cell = r.createCell(0);
        cell.setCellValue(new HSSFRichTextString("表单号"));
        cell = r.createCell(1);
        cell.setCellValue(new HSSFRichTextString("学号"));
        cell = r.createCell(2);
        cell.setCellValue(new HSSFRichTextString("姓名"));
        cell = r.createCell(3);
        cell.setCellValue(new HSSFRichTextString("性别"));
        cell = r.createCell(4);
        cell.setCellValue(new HSSFRichTextString("专业"));
        cell = r.createCell(5);
        cell.setCellValue(new HSSFRichTextString("班级"));
        cell = r.createCell(6);
        cell.setCellValue(new HSSFRichTextString("QQ"));
        cell = r.createCell(7);
        cell.setCellValue(new HSSFRichTextString("邮箱"));
        cell = r.createCell(8);
        cell.setCellValue(new HSSFRichTextString("是否打星"));
        cell = r.createCell(9);
        cell.setCellValue(new HSSFRichTextString("组别"));
        for(Player data : excelData){
            //创建一个row行，然后自增1
            r = sheet.createRow(rowIndex++);
            cell = r.createCell(0);
            cell.setCellValue(new HSSFRichTextString(String.valueOf( data.getInformationId())));
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
            if(data.isStar())
            cell.setCellValue(new HSSFRichTextString("打星"));

            cell = r.createCell(9);
            if(data.isGroup())
            cell.setCellValue(new HSSFRichTextString("正式组"));
            else
                cell.setCellValue(new HSSFRichTextString("新生组"));
        }
        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");
        //设置导出Excel的名称
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        //刷新缓冲
        response.flushBuffer();

        //测试写入本地文件
        //workbook.write(new File("C:\\Users\\Administrator\\Desktop\\excel测试\\fileName.xlsx"));
        //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
        workbook.write(response.getOutputStream());

        //关闭workbook
        workbook.close();
    }


}
