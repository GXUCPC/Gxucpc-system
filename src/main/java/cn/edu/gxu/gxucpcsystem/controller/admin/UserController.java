package cn.edu.gxu.gxucpcsystem.controller.admin;

import cn.edu.gxu.gxucpcsystem.Service.AdminService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.controller.entity.AdminPages;
import cn.edu.gxu.gxucpcsystem.domain.Admin;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sct
 * @date 2022/7/30
 */

@RestController
@RequestMapping("/api/admin/user")
@CrossOrigin
public class UserController {
    @Autowired
    AdminService adminService;

    /**
     * 添加管理员
     *
     * @param admin 管理员信息
     * @return
     * @method POST
     * @url /admin/user
     */
    @PostMapping
    public Re addUser(@RequestBody Admin admin) {
        if (adminService.addAdmin(admin)) {
            return new Re(Code.STATUS_OK, null, "添加成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "数据库系统存在异常,请重试！");
    }

    /**
     * 删除管理员
     *
     * @param id 管理员ID
     * @return
     */
    @DeleteMapping
    public Re delUser(Integer id) {
        if(adminService.delAdmin(id)) {
            return new Re(Code.STATUS_OK, null, "删除成功");
        }
        return new Re(Code.RESOURCE_DISABLE, null, "没有该用户，删除失败");
    }

    /**
     * 修改用户
     *
     * @param request 请求头
     * @param admin 修改后的信息
     * @return
     */
    @PutMapping
    public Re updateUser(@RequestBody Admin admin, HttpServletRequest request) {
        if(request.getHeader("isChangePassword") == null)
            return new Re(Code.RESOURCE_DISABLE, null, "错误的请求格式");
        int isChangePassword = Integer.parseInt(request.getHeader("isChangePassword"));
        if(adminService.updateAdmin(admin, isChangePassword == 1)) {
            return new Re(Code.STATUS_OK, null, "修改成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "修改失败");
    }

    /**
     * 分页获取管理员列表
     *
     * @param currentPage 当前页
     * @param numberPerPage 每页数量
     * @return
     */
    @GetMapping
    public Re getByPage(Integer currentPage, Integer numberPerPage) {
        AdminPages adminPages =  adminService.getByPage(currentPage, numberPerPage);
        return new Re(Code.STATUS_OK, adminPages, "请求成功");
    }
}
