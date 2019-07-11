package com.stylefeng.guns.modular.crm.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.ToolUtil;

import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.CrmSalechance;
import com.stylefeng.guns.modular.system.model.Role;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IRoleService;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.crm.service.ICrmSalechanceService;

/**
 * 销售机会控制器
 *
 * @author fengshuonan
 * @Date 2018-09-30 09:27:26
 */
@Controller
@RequestMapping("/crmSalechance")
public class CrmSalechanceController extends BaseController {

	private String PREFIX = "/crm/crmSalechance/";

	@Autowired
	private ICrmSalechanceService crmSalechanceService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Value("${crm.max-sales-chance}")
	private String maxSalesChance;

	/**
	 * 跳转到销售机会首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "crmSalechance.html";
	}

	/**
	 * 跳转到添加销售机会
	 */
	@RequestMapping("/crmSalechance_add")
	public String crmSalechanceAdd(Integer customId, Model model) {
		model.addAttribute("customId", customId);
		return PREFIX + "crmSalechance_add.html";
	}

	/**
	 * 跳转到修改销售机会
	 */
	@RequestMapping("/crmSalechance_update/{crmSalechanceId}")
	public String crmSalechanceUpdate(@PathVariable Integer crmSalechanceId, Model model) {
		if (ToolUtil.isEmpty(crmSalechanceId)) {
			model.addAttribute("item", new CrmSalechance());
		}
		List<Map<String, Object>> xsjhList = crmSalechanceService.getSalesChanceDetail(crmSalechanceId);
		if (xsjhList.size() > 0) {
			model.addAttribute("item", xsjhList.get(0));
		} else {
			model.addAttribute("item", new CrmSalechance());
		}
		return PREFIX + "crmSalechance_edit.html";
	}

	/**
	 * 获取销售机会列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(Integer customId, Integer saleState) {
		if (ToolUtil.isEmpty(customId)) {
			return new ArrayList<Map<String, Object>>();
		}
		List<Map<String, Object>> chanceList = crmSalechanceService.getChanceList(customId, saleState);
		return chanceList;
	}

	@RequestMapping(value = "/listPage")
	public String listPage(Integer customId, Integer saleState, Model model) {
		model.addAttribute("id", 1);
		return PREFIX + "crmSalechance_list.html";
	}

	// 获取客户经理列表（业务员）
	@RequestMapping(value = "/getCustomManger")
	@ResponseBody
	public Object getCustomManger() {
		EntityWrapper<User> userEntity = new EntityWrapper<>();
		// 读取销售相关角色
		String[] roles = { "salesTop", "salesManager", "sales" };
		EntityWrapper<Role> roleEntity = new EntityWrapper<>();
		Wrapper<Role> roleWrapper = roleEntity.in("tips", roles);
		List<Role> roleList = roleService.selectList(roleWrapper);
		// 销售经理和销售人员
		// Integer[] roleIds = { 6, 7, 8 };
		if (roleList.size() > 0) {
			Integer[] roleIds = new Integer[roleList.size()];
			for (int i = 0; i < roleList.size(); i++) {
				roleIds[i] = roleList.get(i).getId();
			}
			Wrapper<User> userWrapper = userEntity.in("roleid", roleIds).eq("status", 1);
			List<User> userList = userService.selectList(userWrapper);
			return userList;
		} else {
			return new ArrayList<User>();
		}
	}

	/**
	 * 新增销售机会
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	@Transactional
	public Object add(CrmSalechance crmSalechance) {
		if (ToolUtil.isEmpty(crmSalechance.getCustomerId()) || ToolUtil.isEmpty(crmSalechance.getChangeType())
				|| ToolUtil.isEmpty(crmSalechance.getFstate())) {
			return new ErrorTip(5003, "机会类型、客户名称、销售阶段不得为空");
		}
		// 当前客户有多少销售机会
		EntityWrapper<CrmSalechance> chanceEntity = new EntityWrapper<>();
		Wrapper<CrmSalechance> chanceWrapper = chanceEntity.eq("customerId", crmSalechance.getCustomerId())
				.eq("IsDelete", 1);
		List<CrmSalechance> chanceList = crmSalechanceService.selectList(chanceWrapper);
		if (chanceList.size() >= Integer.valueOf(maxSalesChance)) {
			return new ErrorTip(5004, "每个客户最多可以拥有" + maxSalesChance + "销售机会");
		}
		ShiroUser user = ShiroKit.getUser();
		crmSalechance.setSaleId(Long.valueOf(String.valueOf(user.getId())));
		crmSalechance.setCreateDate(new Date());
		crmSalechance.setIsDelete(1);
		if (crmSalechance.getFstate() == 5 || crmSalechance.getFstate() == 6) {
			crmSalechance.setFinishDate(new Date());
		}
		crmSalechanceService.insert(crmSalechance);
		return SUCCESS_TIP;
	}

	// 变更记录保存
	@RequestMapping(value = "/saveSalesChanceChange")
	@ResponseBody
	@Transactional
	public Object saveXsjhBg(Integer salesId, Integer fstateId) {
		if (ToolUtil.isEmpty(salesId) || ToolUtil.isEmpty(fstateId)) {
			return new ErrorTip(5004, "销售机会和销售阶段不得为空");
		}
		CrmSalechance chance = crmSalechanceService.selectById(salesId);
		if (ToolUtil.isEmpty(chance)) {
			return new ErrorTip(5001, "销售机会ID错误！");
		}
		if (chance.getFstate() == 5 || chance.getFstate() == 6) {
			return new ErrorTip(5005, "销售机会已终结，不可以进行变更！");
		}
		chance.setFstate(fstateId);
		if (fstateId == 5 || fstateId == 6) {
			chance.setFinishDate(new Date());
		}
		crmSalechanceService.updateById(chance);
		return SUCCESS_TIP;
	}

	/**
	 * 删除销售机会
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer crmSalechanceId) {
		if (ToolUtil.isEmpty(crmSalechanceId)) {
			return new ErrorTip(5004, "销售机会不得为空");
		}
		CrmSalechance chance = crmSalechanceService.selectById(crmSalechanceId);
		if (ToolUtil.isEmpty(chance)) {
			return new ErrorTip(5001, "销售机会ID错误！");
		}
		chance.setIsDelete(2);
		crmSalechanceService.updateById(chance);
		return SUCCESS_TIP;
	}

	/**
	 * 修改销售机会
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(CrmSalechance crmSalechance) {
		crmSalechanceService.updateById(crmSalechance);
		return SUCCESS_TIP;
	}

	/**
	 * 销售机会详情
	 */
	@RequestMapping(value = "/detail/{crmSalechanceId}")
	@ResponseBody
	public Object detail(@PathVariable("crmSalechanceId") Integer crmSalechanceId) {
		if (ToolUtil.isEmpty(crmSalechanceId)) {
			return new CrmSalechance();
		}
		List<Map<String, Object>> xsjhList = crmSalechanceService.getSalesChanceDetail(crmSalechanceId);
		if (xsjhList.size() > 0) {
			return xsjhList.get(0);
		} else {
			return new CrmSalechance();
		}
	}
}
