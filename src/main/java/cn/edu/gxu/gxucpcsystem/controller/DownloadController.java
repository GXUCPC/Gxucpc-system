package cn.edu.gxu.gxucpcsystem.controller;


import cn.edu.gxu.gxucpcsystem.domain.UserOfDownload;
import cn.edu.gxu.gxucpcsystem.domain.utils.Re;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author Sct
 * @Date 2022/6/12 13:01
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/download")
public class DownloadController {

    /*
    * @return
    *   200: success
    *   404: 项目中无该成员信息
    *   500: 项目未开启查询
    *
    * */
    @PostMapping("/checkStatus")
    public Re checkDownloadStatus(@RequestBody UserOfDownload userOfDownload) {
        return new Re(404, null, "未在" + userOfDownload.getItem() + "项目中找到该成员");
    }

    /*
    * 根据数据库内保存的文件地址，获取文件流，返回给前端相应的文件
    * */
    @PostMapping
    public ResponseEntity<ByteArrayResource> downloadFile(HttpServletRequest request) throws IOException {
        String fileName = "ch8 输入输出系统 .pdf";
        String filePath = "D:\\Code\\Gxucpc-system\\src\\main\\resources\\static\\ch8 输入输出系统 .pdf";
        // 2. 解决下载的文件的文件名出现中文乱码
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            // IE浏览器
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            // 非IE浏览器
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        }

        // 3. 下载文件
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        ByteArrayResource resource = new ByteArrayResource(data);
        ResponseEntity<ByteArrayResource> resourceResponseEntity = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(data.length)
                .body(resource);
        return resourceResponseEntity;
    }
}
