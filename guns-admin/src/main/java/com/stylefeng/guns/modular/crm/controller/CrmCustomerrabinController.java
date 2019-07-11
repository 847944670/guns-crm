package com.stylefeng.guns.modular.crm.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.CrmCustomerrabin;
import com.stylefeng.guns.modular.crm.service.ICrmCustomerrabinService;

/**
 * 客户回收管理控制器
 *
 * @author fengshuonan
 * @Date 2018-09-30 09:27:08
 */
@Controller
@RequestMapping("/crmCustomerrabin")
public class CrmCustomerrabinController extends BaseController {

    private String PREFIX = "/crm/crmCustomerrabin/";

    @Autowired
    private ICrmCustomerrabinService crmCustomerrabinService;

    /**
     * 跳转到客户回收管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "crmCustomerrabin.html";
    }

    /**
     * 跳转到添加客户回收管理
     */
    @RequestMapping("/crmCustomerrabin_add")
    public String crmCustomerrabinAdd() {
        return PREFIX + "crmCustomerrabin_add.html";
    }

    /**
     * 跳转到修改客户回收管理
     */
    @RequestMapping("/crmCustomerrabin_update/{crmCustomerrabinId}")
    public String crmCustomerrabinUpdate(@PathVariable Integer crmCustomerrabinId, Model model) {
        CrmCustomerrabin crmCustomerrabin = crmCustomerrabinService.selectById(crmCustomerrabinId);
        model.addAttribute("item",crmCustomerrabin);
        LogObjectHolder.me().set(crmCustomerrabin);
        return PREFIX + "crmCustomerrabin_edit.html";
    }

    /**
     * 获取客户回收管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return crmCustomerrabinService.selectList(null);
    }

    /**
     * 新增客户回收管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CrmCustomerrabin crmCustomerrabin) {
        crmCustomerrabinService.insert(crmCustomerrabin);
        return SUCCESS_TIP;
    }

    /**
     * 删除客户回收管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer crmCustomerrabinId) {
        crmCustomerrabinService.deleteById(crmCustomerrabinId);
        return SUCCESS_TIP;
    }

    /**
     * 修改客户回收管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CrmCustomerrabin crmCustomerrabin) {
        crmCustomerrabinService.updateById(crmCustomerrabin);
        return SUCCESS_TIP;
    }

    /**
     * 客户回收管理详情
     */
    @RequestMapping(value = "/detail/{crmCustomerrabinId}")
    @ResponseBody
    public Object detail(@PathVariable("crmCustomerrabinId") Integer crmCustomerrabinId) {
        return crmCustomerrabinService.selectById(crmCustomerrabinId);
    }
}
