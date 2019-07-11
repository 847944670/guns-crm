package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Weekly;
import com.stylefeng.guns.modular.system.service.IWeeklyService;

import java.util.List;

/**
 * 我的周报控制器
 *
 * @author fengshuonan
 * @Date 2019-03-04 18:08:45
 */
@Controller
@RequestMapping("/weekly")
public class WeeklyController extends BaseController {

    private String PREFIX = "/system/weekly/";

    @Autowired
    private IWeeklyService weeklyService;

    /**
     * 跳转到我的周报首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "weekly.html";
    }

    /**
     * 跳转到添加我的周报
     */
    @RequestMapping("/weekly_add")
    public String weeklyAdd() {

        return PREFIX + "weekly_add.html";
    }

    /**
     * 跳转到修改我的周报
     */
    @RequestMapping("/weekly_update/{weeklyId}")
    public String weeklyUpdate(@PathVariable Integer weeklyId, Model model) {
        Weekly weekly = weeklyService.selectById(weeklyId);
        model.addAttribute("item",weekly);
        LogObjectHolder.me().set(weekly);
        return PREFIX + "weekly_edit.html";
    }

    /**
     * 获取我的周报列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Weekly> weeklies = weeklyService.selectList(null);
        return weeklies;
    }

    /**
     * 新增我的周报
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Weekly weekly) {
        weeklyService.insert(weekly);
        return SUCCESS_TIP;
    }

    /**
     * 删除我的周报
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer weeklyId) {
        weeklyService.deleteById(weeklyId);
        return SUCCESS_TIP;
    }

    /**
     * 修改我的周报
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Weekly weekly) {
        weeklyService.updateById(weekly);
        return SUCCESS_TIP;
    }

    /**
     * 我的周报详情
     */
    @RequestMapping(value = "/detail/{weeklyId}")
    @ResponseBody
    public Object detail(@PathVariable("weeklyId") Integer weeklyId) {
        return weeklyService.selectById(weeklyId);
    }
}
