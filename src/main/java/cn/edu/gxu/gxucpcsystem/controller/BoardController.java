package cn.edu.gxu.gxucpcsystem.controller;

import cn.edu.gxu.gxucpcsystem.domain.utils.Re;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sct
 * @date 2022/6/19
 */

@RestController
@RequestMapping("/api/board")
@CrossOrigin
public class BoardController {

    @GetMapping("/{itemID}")
    public Re getBoard(@PathVariable String itemID) {
        return new Re(50000, "", "");
    }
}
