package cn.edu.gxu.gxucpcsystem.controller.publiced;

import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.ImageMedal;
import cn.edu.gxu.gxucpcsystem.service.ImageMedalService;
import cn.edu.gxu.gxucpcsystem.utils.MD5Utils;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import cn.edu.gxu.gxucpcsystem.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/public/image")
@CrossOrigin
public class ImageMedalController {
    @Resource
    private ImageMedalService imageMedalService;

    @Value("${buffSize}")
    private int buffer;

    @PostMapping
    public Re upload(MultipartFile file) {
        String fileType = file.getContentType(); // 获取文件类型
        if (fileType == null || (!fileType.equals("image/jpeg") && !fileType.equals("image/png"))) {
            return new Re(Code.RESOURCE_DISABLE, null, "只能上传jpg和png格式的图片");
        }
        ImageMedal medal = new ImageMedal();
        int len = 0;
        String URI = UUIDUtil.createUUID();
        try {
            medal.setFiles(file.getBytes());
            len = file.getBytes().length;
            if (len > buffer) {
                return new Re(Code.RESOURCE_DISABLE, null, "文件过大，请压缩后上传");
            }
            medal.setSize(len);
            medal.setFileName(file.getOriginalFilename());
            medal.setFileURI(URI);
            medal.setDate(System.currentTimeMillis());
            medal.setMd5(MD5Utils.string2MD5(medal.getFiles()));
            imageMedalService.addMedal(medal);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
            return new Re(Code.DATABASE_ERROR, null, e.getMessage());
        }

        return new Re(Code.STATUS_OK, URI, "上传成功");
    }

    @GetMapping("/{Uri}")
    public ResponseEntity<byte[]> query(@PathVariable String Uri) throws IOException {
        ImageMedal medal = imageMedalService.queryFile(Uri);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(medal.getFiles(), headers, HttpStatus.OK);
    }
}
