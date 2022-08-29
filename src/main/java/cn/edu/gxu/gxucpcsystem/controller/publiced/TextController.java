package cn.edu.gxu.gxucpcsystem.controller.publiced;

import cn.edu.gxu.gxucpcsystem.Service.ContestService;
import cn.edu.gxu.gxucpcsystem.Service.TextService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Text;
import cn.edu.gxu.gxucpcsystem.utils.LogsUtil;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sct
 * @date 2022/8/15
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class TextController {

    @Autowired
    TextService textService;

    @Autowired
    ContestService contestService;

    /**
     * 根据文章ID查询
     *
     * @param id 文章id
     * @return
     */
    @GetMapping("/public/text")
    public Re getByID(Integer id) {
        Text queryR = textService.getByID(id);
        if(queryR == null) {
            return new Re(Code.RESOURCE_DISABLE, null, "该文章已失效");
        }
        return new Re(Code.STATUS_OK, queryR, "获取成功");
    }

    /**
     * 根据文章类型查询文章
     *
     * @param type 文章类型
     * @param currentPage 当前页
     * @param numberPerPage 每页数量
     * @return
     */
    @GetMapping("/public/text/{type}")
    public Re getByType(@PathVariable("type") String type, Integer currentPage, Integer numberPerPage) {
        if(currentPage < 0 || numberPerPage < 0 || !(type.equals("news") || type.equals("notice") || type.equals("winners") || type.equals("prize") || type.equals("board") || type.equals("signup"))) {
            return new Re(Code.RESOURCE_DISABLE, null, "无效请求");
        }
        if(type.equals("board") || type.equals("signup")) {
            return new Re(Code.STATUS_OK, contestService.getByPages(currentPage, numberPerPage), "获取成功");
        }
        return new Re(Code.STATUS_OK, textService.getByType(type, currentPage, numberPerPage), "获取成功");
    }

    /**
     * 获取所有的文章
     *
     * @param currentPage 当前页
     * @param numberPerPage 每页数量
     * @return
     */
    @GetMapping("/admin/allList")
    public Re getAll(Integer currentPage, Integer numberPerPage) {
        if(currentPage < 0 || numberPerPage < 0) {
            return new Re(Code.RESOURCE_DISABLE, null, "无效请求");
        }
        return new Re(Code.STATUS_OK, textService.getAll(currentPage, numberPerPage), "成功");
    }

    /**
     * 新增文章
     *
     * @param request HTTP请求头
     * @param text 文章内容
     * @return
     */
    @PostMapping("/admin/text")
    public Re insert(HttpServletRequest request, @RequestBody Text text) {
        if(textService.insert(text, request.getHeader("username"))) {
            LogsUtil.logOfOperation(request.getHeader("username"), "添加了一篇文章《" + text.getTitle() + "》");
            return new Re(Code.STATUS_OK, null, "成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "文章缺少必要信息，添加失败");
    }

    /**
     * 根据ID更新文章
     *
     * @param request HTTP请求头
     * @param text 修改后的文章内容
     * @return
     */
    @PutMapping("/admin/text")
    public Re updateByID(HttpServletRequest request, @RequestBody Text text) {
        if(textService.updateByID(text)) {
            LogsUtil.logOfOperation(request.getHeader("username"), "修改了文章：id=" + text.getId());
            return new Re(Code.STATUS_OK, null, "成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "无效id或文章缺少必要信息，修改失败");
    }

    /**
     * 根据文章id删除
     * @param request HTTP请求头
     * @param id 文章id
     * @return
     */
    @DeleteMapping("/admin/text")
    public Re deleteById(HttpServletRequest request, Integer id) {
        if(textService.deleteByID(id)) {
            LogsUtil.logOfOperation(request.getHeader("username"), "删除了文章：id=" + id);
            return new Re(Code.STATUS_OK, null, "成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "无效的id");
    }
}
