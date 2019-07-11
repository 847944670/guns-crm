package com.stylefeng.guns.modular.crm.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;

import org.springframework.web.bind.annotation.RequestParam;
//import com.stylefeng.guns.modular.system.model.KhGh;
//import com.stylefeng.guns.modular.system.warpper.KhGhWarpper;
import com.stylefeng.guns.modular.crm.service.ICrmCustomerService;
import com.stylefeng.guns.modular.crm.wrapper.CrmCustomerWarpper;
import com.stylefeng.guns.modular.system.dao.CrmCustomerMapper;
import com.stylefeng.guns.modular.system.dao.UserMapper;
import com.stylefeng.guns.modular.system.model.CrmCustomer;
//import com.stylefeng.guns.modular.khgh.service.IKhGhService;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.system.warpper.CustomerSeasWarpper;

/**
 * 公海客户控制器
 *
 * @author fengshuonan
 * @Date 2018-09-19 16:27:16
 */
@Controller
@RequestMapping("/crmCustomerSeas")
public class CrmCustomerSeasController extends BaseController {

    private String PREFIX = "/crm/crmCustomerSeas/";

    @Autowired
    private ICrmCustomerService crmCustomerService;
    @Autowired
    private IUserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CrmCustomerMapper customerMapper;
    
    
    
    //private IKhGhService khGhService;

    /**
     * 跳转到公海客户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "crmCustomerSeas.html";
    }

    /**
     * 查看客户详情
     */
    @RequestMapping("/detail")
    public String crmCustomerDetail() {
        return PREFIX + "khGh_add.html";
    }
    /**
     * 进入分配页面
     */
    @RequestMapping("/assign/{customerId}")
    public String crmCustomerAssign(@PathVariable Integer customerId, Model model) {
    	model.addAttribute("customerId",customerId);
    	return PREFIX + "crmCustomerSeas_assign.html";
    }
    /**
     * 提交分配
     */
    @RequestMapping("/assignSubmit")
    @ResponseBody
    public Object crmCustomerAssignSubmit(@RequestParam String saleId,@RequestParam Integer customerId) {//@PathVariable String id, "id" + id + 
//    	System.out.println("saleId" + saleId + "customerId" + customerId);
    	User saleUser = userMapper.selectById(saleId);
    	EntityWrapper<CrmCustomer> userEntity = new EntityWrapper<>();
    	Wrapper<CrmCustomer> userWrapper = userEntity.eq("IsDelete", 0).eq("id", customerId);
    	List<CrmCustomer> crmCustomerList = customerMapper.selectList(userWrapper);
    	if(crmCustomerList.isEmpty()) {
    		return new ErrorTip(500, "该用户已被分配");
    	}
    	CrmCustomer crmCustomer = crmCustomerList.get(0);
    	crmCustomer.setSalerId(Long.valueOf(saleId));
    	crmCustomer.setSalerName(saleUser.getName());
    	crmCustomer.setIsDelete(1);
    	crmCustomer.setSalerCreateTime(new Date());
    	customerMapper.updateById(crmCustomer);
    	return SUCCESS_TIP;
    }
    
 // 获取客户经理列表（业务员）
 	@RequestMapping(value = "/getCustomManger")
 	@ResponseBody
 	public Object getCustomManger() {
 		EntityWrapper<User> userEntity = new EntityWrapper<>();
 		// 总监 销售经理  销售人员
 		Integer[] roleIds = { 7, 8 };
 		Wrapper<User> userWrapper = userEntity.in("roleid", roleIds).eq("status", 1);
 		List<User> userList = userService.selectList(userWrapper);
 		return userList;
 	}
 	
 	
   /**
    *  捞取公海客户
    * @param customerId
    * @return
    */
   @RequestMapping("/gain/{customerId}")
   @ResponseBody
   public synchronized Object gain(@PathVariable Integer customerId) {
   		int saleId=ShiroKit.getUser().getId();
   		User saleUser = userMapper.selectById(saleId);
//    System.out.println(a+"?????????" + customerId);
   		EntityWrapper<CrmCustomer> userEntity = new EntityWrapper<>();
    	Wrapper<CrmCustomer> userWrapper = userEntity.eq("IsDelete", 0).eq("id", customerId);
    	List<CrmCustomer> crmCustomerList = customerMapper.selectList(userWrapper);
    	if(crmCustomerList.isEmpty()) {
    		return new ErrorTip(500, "该用户已被捞取");
    	}
    	CrmCustomer crmCustomer = crmCustomerList.get(0);
    	crmCustomer.setSalerId(Long.valueOf(saleId));
    	crmCustomer.setSalerName(saleUser.getName());
    	crmCustomer.setIsDelete(1);
    	crmCustomer.setSalerCreateTime(new Date());
    	customerMapper.updateById(crmCustomer);
        return SUCCESS_TIP;
   } 
   
   /**
    * 获取公海客户列表
    * @param condition
    * @param laoquTime
    * @param creatTime
    * @return
    */
  @RequestMapping(value = "/list")
  @ResponseBody
  public Object list(String condition,String laoquTime,String creatTime) {
	  EntityWrapper<CrmCustomer> userEntity = new EntityWrapper<>();
  	  Wrapper<CrmCustomer> userWrapper = userEntity.eq("IsDelete", 0);//.eq("id", customerId);
//  	  List<CrmCustomer> crmCustomerList = customerMapper.selectList(userWrapper);
//  	  CrmCustomerWarpper(crmCustomerList).warp();
//    List<Map<String, Object>> khgh = this.khGhService.qualllist(condition,laoquTime,creatTime);
//  	  return crmCustomerList;
  	List<Map<String, Object>> customerList = customerMapper.selectMaps(userWrapper);
  	  return new CustomerSeasWarpper(customerList).warp();//数据字典查询
  }
  
  /**
   * 跳转到添加公海客户
   * @return
   */
  @RequestMapping("/customer_add")
  public String customerAdd() {
      return PREFIX + "crmCustomerSeas_add.html";
  }
 	
  /**
   * 新增公海客户
   * @param crmCustomer
   * @return
   */
 @RequestMapping("/add")
 @ResponseBody
 public Object add(@Valid CrmCustomer crmCustomer) {
// 	int a=ManagerStatus.OK.getCode();
// 	Date time=null;
// 	SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
//		try {
//			time = formatter.parse(formatter.format(new Date()));
//		} catch (ParseException e) {
//						e.printStackTrace();
//		}
// 	khGh.setCreatTime(time);
// 	khGh.setIsDelete(a);
// 	khGh.setCreatePersion(ShiroKit.getUser().getId());
//    khGhService.insert(khGh);
		String phone =crmCustomer.getCusPhoneNum();
        int num= crmCustomerService.findPhone(phone);
        if(num!=0) {
        	return new ErrorTip(500, "该手机号已存在");
        }
    	int salerId = ShiroKit.getUser().getId();
    	String salerName = ConstantFactory.me().getUserNameById(salerId);
    	crmCustomer.setCreateTime(new Date());
    	crmCustomer.setSalerId((long) salerId);
    	crmCustomer.setSalerName(salerName);
    	crmCustomer.setIsDelete(0);//0:公海客户
    	
        crmCustomerService.insert(crmCustomer);
        return SUCCESS_TIP;
 }
 	

    /**
     * 跳转到修改公海客户
     */
  /*  @RequestMapping("/khGh_update/{khGhId}")
    public String khGhUpdate(@PathVariable Integer khGhId, Model model) {
        KhGh khGh = khGhService.selectById(khGhId);
        model.addAttribute("item",khGh);
        Date date=khGh.getCreatTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        model.addAttribute("creatTime", dateString);
        model.addAttribute("FzrNowId", ConstantFactory.me().getUserNameById(khGh.getFzrNowId()));
        model.addAttribute("FzrBeforId", ConstantFactory.me().getUserNameById(khGh.getFzrBeforId()));
        model.addAttribute("createPersion", ConstantFactory.me().getUserNameById(khGh.getCreatePersion()));
        LogObjectHolder.me().set(khGh);
        System.out.println(khGh.getCreatTime()+"时间时间手机");
        return PREFIX + "khGh_edit.html";
    }
    
    *//**
     * 跳转到公海客户详情
     *//*
    @RequestMapping("/khGh_update1/{khGhId}")
    public String khGhUpdate1(@PathVariable Integer khGhId, Model model) {
        KhGh khGh = khGhService.selectById(khGhId);
        model.addAttribute("item",khGh);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=khGh.getCreatTime();
        Date date1=khGh.getLaoquTime();
        String dateString = formatter.format(date);
        String dateString1 = formatter.format(date1);
        model.addAttribute("creatTime", dateString);
        model.addAttribute("laoquTime", dateString1);
        model.addAttribute("FzrNowId", ConstantFactory.me().getUserNameById(khGh.getFzrNowId()));
        model.addAttribute("FzrBeforId", ConstantFactory.me().getUserNameById(khGh.getFzrBeforId()));
        model.addAttribute("createPersion", ConstantFactory.me().getUserNameById(khGh.getCreatePersion()));
        model.addAttribute("GJJL", ConstantFactory.me().getGenName(khGh.getKhGjjl()));
        model.addAttribute("ZYCD", ConstantFactory.me().getZhongName(khGh.getKhZycd()));
        model.addAttribute("KHHY", ConstantFactory.me().getKhhangy(khGh.getKhHy()));
        LogObjectHolder.me().set(khGh);
       return PREFIX + "khGh_xq.html";
    }
    *//**
     * 获取公海客户列表(未删除)
     *//*
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition,String laoquTime,String creatTime) {
        //return khGhService.selectList(null);
    	System.out.println(condition+"123456789");
    	 List<Map<String, Object>> khgh = this.khGhService.qualllist(condition,laoquTime,creatTime);
    	 return new KhGhWarpper(khgh).warp();
    }

    *//**
     * 新增公海客户
     *//*
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid KhGh khGh) {
    	int a=ManagerStatus.OK.getCode();
    	Date time=null;
    	SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		try {
			time = formatter.parse(formatter.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	khGh.setCreatTime(time);
    	khGh.setIsDelete(a);
    	khGh.setCreatePersion(ShiroKit.getUser().getId());
        khGhService.insert(khGh);
        return SUCCESS_TIP;
    }

    *//**
     * 删除wod客户(逻辑删除)
     *//*
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer khGhId) {
    	int a=ManagerStatus.DELETED.getCode();
    	khGhService.setState(khGhId,a);
        return SUCCESS_TIP;
    }
    
    *//**
     * 捞取公海客户
     *//*
    @RequestMapping(value = "/laoqu")
    @ResponseBody
    public Object laoqu(@RequestParam Integer khGhId) {
    	int a=ShiroKit.getUser().getId();
    	System.out.println(a+"?????????");
    	Date time=new Date();
    	khGhService.setfzrNow(khGhId,a,time);
        return SUCCESS_TIP;
    } 

    *//**
     * 修改公海客户
     *//*
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(KhGh khGh) {
        khGhService.updateById(khGh);
        return SUCCESS_TIP;
    }

    *//**
     * 公海客户详情
     *//*
    @RequestMapping(value = "/detail/{khGhId}")
    @ResponseBody
    public Object detail(@PathVariable("khGhId") Integer khGhId) {
        return khGhService.selectById(khGhId);
    }*/
}
