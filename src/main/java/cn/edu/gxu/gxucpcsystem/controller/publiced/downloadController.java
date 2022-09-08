package cn.edu.gxu.gxucpcsystem.controller.publiced;

import cn.edu.gxu.gxucpcsystem.Service.MedalService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Medal;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * @author MaoMao
 * @Description
 * @create 2022-09-06 9:25 AM
 */

@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class downloadController {
    @Autowired
    MedalService medalService;
    @PostMapping("/download/checkStatus")
    public Re download(@RequestBody Map<String, String> mp){
        String fileName = mp.get("name")+ "-" + mp.get("number") +".pdf";
        List<Medal>lsMedal = medalService.queryFile(fileName,mp.get("itemNumber"));
        if(lsMedal.size() == 0){
            return new Re(Code.RESOURCE_DISABLE,null,"查询不到有效获奖名单");
        }
        return new Re(Code.STATUS_OK,null,"查询成功");
    }

    @PostMapping("/download")
    public byte[] down(@RequestBody Map<String, String> mp){
        String fileName = mp.get("name")+ "-" + mp.get("number") +".pdf";
        byte[] bytes = medalService.getFile(fileName,mp.get("itemNumber"));
        return bytes;
    }
}
