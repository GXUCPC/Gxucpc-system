package cn.edu.gxu.gxucpcsystem.controller.admin;

import cn.edu.gxu.gxucpcsystem.Service.MedalService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import cn.edu.gxu.gxucpcsystem.utils.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

/**
 * @author MaoMao
 * @Description
 * @create 2022-09-06 9:07 AM
 */

@RestController
@RequestMapping("/api/admin/contest/file")
@CrossOrigin
public class UploadMedalController {
    @Autowired
    MedalService medalService;
    @Autowired
    ZipUtil zipUtil;
    @PostMapping("/{contestId}")
    public Re upload(@PathVariable int contestId,@RequestBody File file) throws IOException {
        medalService.addList(ZipUtil.unzipFile(file));
        return new Re(Code.STATUS_OK,null,"上传成功");
    }
}
