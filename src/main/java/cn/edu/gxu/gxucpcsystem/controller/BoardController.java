package cn.edu.gxu.gxucpcsystem.controller;

import cn.edu.gxu.gxucpcsystem.domain.Board;
import cn.edu.gxu.gxucpcsystem.domain.utils.Re;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sct
 * @date 2022/6/19
 */

@RestController
@RequestMapping("/api/public/enroll")
@CrossOrigin
public class BoardController {
    @PostMapping("/{itemID}")
    public Re getBoard(@PathVariable String itemID, @RequestBody Board board) {
        return new Re(50000, "报名成功，我们已发送验证邮箱，请查看邮箱是否能正常接收报名邮件", "报名成功，我们已发送验证邮箱，请查看邮箱是否能正常接收报名邮件");
    }
}
