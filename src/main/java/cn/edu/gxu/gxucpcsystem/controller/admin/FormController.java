package cn.edu.gxu.gxucpcsystem.controller.admin;

import cn.edu.gxu.gxucpcsystem.service.PlayerService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import cn.edu.gxu.gxucpcsystem.utils.LogsUtil;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sct
 * @date 2022/8/28
 */
@RestController
@RequestMapping("/api/admin/form")
@CrossOrigin
public class FormController {
    @Autowired
    PlayerService playerService;

    /**
     * 按页查询表单
     *
     * @param q 查询内容
     * @param currentPage 当前页
     * @param numberPerPage 每页数量
     * @param id 比赛id
     * @return
     */
    @GetMapping
    public Re getLimitFormsByPage(String q, Integer currentPage, Integer numberPerPage, Integer id) {
        return new Re(Code.STATUS_OK, playerService.getByPagesRegex(currentPage, numberPerPage, id, q), "获取成功");
    }

    /**
     * 修改表单
     *
     * @param request HTTP请求头
     * @param player 修改后的表单信息
     * @return
     */
    @PostMapping
    public Re updateForm(HttpServletRequest request, @RequestBody Player player) {
        if(playerService.updatePlayer(player)) {
            LogsUtil.logOfOperation(request.getHeader("username"), "修改了报名表单：" + player.getInformationId());
            return new Re(Code.STATUS_OK, null, "修改成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "修改失败");
    }

    /**
     * 删除表单
     *
     * @param request HTTP请求头
     * @param id 表单id
     * @return
     */
    @DeleteMapping("/{id}")
    public Re deleteForm(HttpServletRequest request, @PathVariable Integer id) {
        if(playerService.deletePlayer(id)) {
            LogsUtil.logOfOperation(request.getHeader("username"), "删除了报名表单：" + id);
            return new Re(Code.STATUS_OK, null, "删除成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "删除失败");
    }



}
