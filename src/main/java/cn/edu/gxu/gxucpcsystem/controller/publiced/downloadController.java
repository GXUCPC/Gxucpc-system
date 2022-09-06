package cn.edu.gxu.gxucpcsystem.controller.publiced;

import cn.edu.gxu.gxucpcsystem.Service.MedalService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

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
    @GetMapping()
    public Re download(@PathParam("itemNumber" )String contestId,@PathParam("number" )String number,@PathParam("name") String name ){
        String fileName = number+ "-" + name +".pdf";
        byte[] bytes = medalService.getFile(fileName,contestId);
        if(bytes.length == 0){
            return new Re(Code.STATUS_OK,null,"查询不到有效获奖名单");
        }
        return new Re(Code.STATUS_OK,bytes,"查询成功");
    }

}
