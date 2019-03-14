package cn.stylefeng.guns.modular.companymanager.controller;

import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.companymanager.service.ITblCompanyService;
import cn.stylefeng.guns.modular.companymanager.transfer.TblCompanyDto;
import cn.stylefeng.guns.modular.system.factory.TblCompayFactory;
import cn.stylefeng.guns.modular.system.factory.UserFactory;
import cn.stylefeng.guns.modular.system.model.TblCompany;
import cn.stylefeng.guns.modular.system.transfer.UserDto;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.provider.MD5;

import javax.rmi.CORBA.Util;
import javax.validation.Valid;

/**
 *  企业管理控制器
 *
 * @author fengshuonan
 * @Date 2018-12-19 19:00:57
 */
@Controller
@RequestMapping("/tblCompany")
public class TblCompanyController extends BaseController {

    private String PREFIX = "/companymanager/tblCompany/";

    @Autowired
    private ITblCompanyService tblCompanyService;

    /**
     * 跳转到 企业管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tblCompany.html";
    }

    /**
     * 跳转到添加 企业管理
     */
    @RequestMapping("/tblCompany_add")
    public String tblCompanyAdd() {
        return PREFIX + "tblCompany_add.html";
    }

    /**
     * 跳转到修改 企业管理
     */
    @RequestMapping("/tblCompany_update/{tblCompanyId}")
    public String tblCompanyUpdate(@PathVariable Integer tblCompanyId, Model model) {
        TblCompany tblCompany = tblCompanyService.selectById(tblCompanyId);
        model.addAttribute("item",tblCompany);
        LogObjectHolder.me().set(tblCompany);
        return PREFIX + "tblCompany_edit.html";
    }

    /**
     * 获取 企业管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return tblCompanyService.selectList(null);
    }

    /**
     * 新增 企业管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid TblCompanyDto companyDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        String comName = companyDto.getName();
        TblCompany company = tblCompanyService.getByCompanyName(comName);

        if(company != null){
            throw  new ServiceException(BizExceptionEnum.COMPANY_EXISTS);
        }
        tblCompanyService.insert(TblCompayFactory.createCompany(companyDto));
        return SUCCESS_TIP;
    }

    /**
     * 删除 企业管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tblCompanyId) {
        tblCompanyService.deleteById(tblCompanyId);
        return SUCCESS_TIP;
    }

    /**
     * 修改 企业管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Valid TblCompanyDto company, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        TblCompany oldcompany = tblCompanyService.selectById(company.getId());

        if (ShiroKit.hasRole(Const.ADMIN_NAME)) {
            this.tblCompanyService.updateById(TblCompayFactory.editCompany(company, oldcompany));
            return SUCCESS_TIP;
        } else {
            throw new ServiceException(BizExceptionEnum.NO_PERMITION);
        }
    }

    /**
     *  企业管理详情
     */
    @RequestMapping(value = "/detail/{tblCompanyId}")
    @ResponseBody
    public Object detail(@PathVariable("tblCompanyId") Integer tblCompanyId) {
        return tblCompanyService.selectById(tblCompanyId);
    }
}
