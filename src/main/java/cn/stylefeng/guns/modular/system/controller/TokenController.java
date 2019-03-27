package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Token;
import cn.stylefeng.guns.modular.system.service.ITokenService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2019-03-27 13:25:43
 */
@Controller
@RequestMapping("/token")
public class TokenController extends BaseController {

    private String PREFIX = "/system/token/";

    @Autowired
    private ITokenService tokenService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "token.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/token_add")
    public String tokenAdd() {
        return PREFIX + "token_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/token_update/{tokenId}")
    public String tokenUpdate(@PathVariable Integer tokenId, Model model) {
        Token token = tokenService.selectById(tokenId);
        model.addAttribute("item",token);
        LogObjectHolder.me().set(token);
        return PREFIX + "token_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return tokenService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Token token) {
        tokenService.insert(token);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tokenId) {
        tokenService.deleteById(tokenId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Token token) {
        tokenService.updateById(token);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{tokenId}")
    @ResponseBody
    public Object detail(@PathVariable("tokenId") Integer tokenId) {
        return tokenService.selectById(tokenId);
    }
}
