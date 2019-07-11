package com.stylefeng.guns.modular.crm.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;

import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.CrmCusrecord;
import com.stylefeng.guns.modular.crm.service.ICrmCusrecordService;

/**
 * 客户跟进记录控制器
 *
 * @author fengshuonan
 * @Date 2018-09-30 09:26:11
 */
@Controller
@RequestMapping("/crmCusrecord")
public class CrmCusrecordController extends BaseController {

    private String PREFIX = "/crm/crmCusrecord/";

    @Autowired
    private ICrmCusrecordService crmCusrecordService;

    /**
     * 跳转到客户跟进记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "crmCusrecord.html";
    }

    /**
     * 跳转到添加客户跟进记录
     */
    @RequestMapping("/crmCusrecord_add/{khGhId}")
    public String crmCusrecordAdd(@PathVariable Integer khGhId, Model model) {
    	model.addAttribute("customerId", khGhId);
        return PREFIX + "crmCusrecord_add.html";
    }

    /**
     * 跳转到修改客户跟进记录
     */
    @RequestMapping("/crmCusrecord_update/{crmCusrecordId}")
    public String crmCusrecordUpdate(@PathVariable Integer crmCusrecordId, Model model) {
        CrmCusrecord crmCusrecord = crmCusrecordService.selectById(crmCusrecordId);
        model.addAttribute("item",crmCusrecord);
        LogObjectHolder.me().set(crmCusrecord);
        return PREFIX + "crmCusrecord_edit.html";
    }

    /**
     * 获取客户跟进记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return crmCusrecordService.selectList(null);
    }

    /**
     * 新增客户跟进记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CrmCusrecord crmCusrecord) {
    	int salerId = ShiroKit.getUser().getId();
    	crmCusrecord.setSalerId((long) salerId);
    	crmCusrecord.setCreateDate(new Date());
        crmCusrecordService.insert(crmCusrecord);
        return SUCCESS_TIP;
    }

    /**
     * 删除客户跟进记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer crmCusrecordId) {
        crmCusrecordService.deleteById(crmCusrecordId);
        return SUCCESS_TIP;
    }

    /**
     * 修改客户跟进记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CrmCusrecord crmCusrecord) {
        crmCusrecordService.updateById(crmCusrecord);
        return SUCCESS_TIP;
    }

    /**
     * 客户跟进记录详情
     */
    @RequestMapping(value = "/detail/{crmCusrecordId}")
    @ResponseBody
    public Object detail(@PathVariable("crmCusrecordId") Integer crmCusrecordId) {
        return crmCusrecordService.selectById(crmCusrecordId);
    }
}
