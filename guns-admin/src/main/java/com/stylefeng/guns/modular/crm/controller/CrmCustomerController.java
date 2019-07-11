package com.stylefeng.guns.modular.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.crm.service.ICrmCusrecordService;
import com.stylefeng.guns.modular.crm.service.ICrmCustomerService;
import com.stylefeng.guns.modular.crm.service.ICrmCustomerrabinService;
import com.stylefeng.guns.modular.crm.wrapper.CrmCusrecordWarpper;
import com.stylefeng.guns.modular.crm.wrapper.CrmCustomerWarpper;
import com.stylefeng.guns.modular.system.model.CrmCustomer;
import com.stylefeng.guns.modular.system.model.CrmCustomerrabin;
/**
 * 客户管理控制器
 *
 * @author fengshuonan
 * @Date 2018-09-30 09:26:36
 */
@Controller
@RequestMapping("/crmCustomer")
public class CrmCustomerController extends BaseController {

    private String PREFIX = "/crm/crmCustomer/";

    @Autowired
    private ICrmCustomerService crmCustomerService;
    @Autowired
    private ICrmCustomerrabinService crmCustomerrabinService;
    @Autowired
    private ICrmCusrecordService crmCusrecordService;

    @Value("${cusnum.max-person}")
    private String cusnum;
    /**
     * 跳转到我的客户管理首页
     */
/*    @
    public
    */
    @RequestMapping("")
    public String index() {
    	/*int salerId = ShiroKit.getUser().getId();
    	List<Map<String, Object>> MyList = crmCustomerService.selectMyList(salerId);
    	new CrmCustomerWarpper(MyList).warp();*/
        return PREFIX + "crmCustomer.html";
    }

/*    *//**
     * 跳转到公海客户管理首页
     *//*
    @RequestMapping("/index1")
    public String index1() {
    	List<Map<String, Object>> List = crmCustomerService.selectGhList();
        return PREFIX + "crmCustomer.html";
    }*/
    /**
     * 跳转到添加客户管理
     */
    @RequestMapping("/crmCustomer_add")
    public String crmCustomerAdd() {
        return PREFIX + "crmCustomer_add.html";
    }

    /**
     * 跳转到修改客户管理
     */
    public static Date getTimesnight() {
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.HOUR_OF_DAY, 24);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	return cal.getTime();
    }
    
    @RequestMapping(value = "/checkUpdate")
    @ResponseBody
    public Object checkUpdate(@RequestParam Integer crmCustomerId) {
    	CrmCustomer crmCustomer = crmCustomerService.selectById(crmCustomerId);
    	Date createTime =  crmCustomer.getCreateTime();
        Calendar cal=Calendar.getInstance();
        cal.setTime(createTime); 
        cal.set(Calendar.HOUR_OF_DAY, 24);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	Date daJ = cal.getTime();
        Date da = getTimesnight();
        if(daJ.equals(da)) {
        	return 1;
        }
        else {
        	return 2;
        }
    }
    
    @RequestMapping("/crmCustomer_update/{crmCustomerId}")
    public String crmCustomerUpdate(@PathVariable Integer crmCustomerId, Model model) {
        CrmCustomer crmCustomer = crmCustomerService.selectById(crmCustomerId);
//        2018-09-30 04:59:41
        /*Date createTime =  crmCustomer.getCreateTime();
        Calendar cal=Calendar.getInstance();
        cal.setTime(createTime); 
        cal.set(Calendar.HOUR_OF_DAY, 24);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	Date daJ = cal.getTime();
        Date da = getTimesnight();
        if(daJ.equals(da)){*/
        	model.addAttribute("item",crmCustomer);
            LogObjectHolder.me().set(crmCustomer);
            return PREFIX + "crmCustomer_edit.html";
       // }else {
        	//return "1";
       //	return PREFIX + "crmCustomer.html";
        }
        
        
        
    //}

    /**
     * 获取客户管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return crmCustomerService.selectList(null);
    }
    
    /**
     * 获取我的列表
     */
    @RequestMapping(value = "/Mylist")
    @ResponseBody
    public Object Mylist(String condition) {
    	int salerId = ShiroKit.getUser().getId();
    	List<Map<String, Object>> MyList = crmCustomerService.selectMyList(salerId);
        return new CrmCustomerWarpper(MyList).warp();
    }

    /**
     * 新增我的客户管理
     * IsDelete 0 :公海
     *          1 :我的客户 
     *          2 :删除标识符
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CrmCustomer crmCustomer) {
    	int userId = ShiroKit.getUser().getId();
    	int personNum = crmCustomerService.personCount(userId,5,1);
    	if(personNum<Integer.valueOf(cusnum)) {
    		String phone =crmCustomer.getCusPhoneNum();
            int num= crmCustomerService.findPhone(phone);
            if(num!=0) {
            	return 1;
            }
            else {
            	int salerId = ShiroKit.getUser().getId();
            	String salerName = ConstantFactory.me().getUserNameById(salerId);
            	crmCustomer.setCreateTime(new Date());
            	crmCustomer.setSalerId((long) salerId);
            	crmCustomer.setSalerName(salerName);
            	crmCustomer.setIsDelete(1);
            	
                crmCustomerService.insert(crmCustomer);
                return SUCCESS_TIP;
            }
    	}
    	else {
    		return 2;
    	}
    	
    	
    }
    
    /**
     * 退回到公海客户管理
     * IsDelete 0 :公海
     *          1 :我的客户 
     *          2 :删除标识符
     */
    @RequestMapping(value = "/tuihui")
    @ResponseBody
    public Object tuihui(@RequestParam Integer crmCustomerId) {
    	CrmCustomer crmCustomer = crmCustomerService.selectById(crmCustomerId);
    	long Id= crmCustomer.getId();
    	int saleid=ShiroKit.getUser().getId();
    	
    	CrmCustomerrabin cusrabin=new CrmCustomerrabin();
    	cusrabin.setCreateTime(new Date());
    	cusrabin.setCusOldSaler((long) saleid);
    	cusrabin.setCustomerId(Id);
    	crmCustomerrabinService.insert(cusrabin);
    	
    	crmCustomerService.tuihui(crmCustomerId);
        return SUCCESS_TIP;
    }
    /**
     * 新增公海客户管理
     * IsDelete 0 :公海
     *          1 :我的客户 
     *          2 :删除标识符
     */
    @RequestMapping(value = "/Ghadd")
    @ResponseBody
    public Object Ghadd(CrmCustomer crmCustomer) {
    	crmCustomer.setCreateTime(new Date());
    	crmCustomer.setIsDelete(0);
        crmCustomerService.insert(crmCustomer);
        return SUCCESS_TIP;
    }

    /**
     * 捞取公海客户
     * IsDelete 0 :公海
     *          1 :我的客户 
     *          2 :删除标识符
     */
    @RequestMapping(value = "/laoqu")
    @ResponseBody
    public synchronized Object laoqu(@RequestParam Integer id) {
    	CrmCustomer crmCustomer = crmCustomerService.selectById(id);
    	if(crmCustomer.getSalerId()==null) {
    		int salerId = ShiroKit.getUser().getId();
        	String salerName = ConstantFactory.me().getUserNameById(salerId);
        	crmCustomerService.laoqu(id,salerId,salerName,1);
        	return SUCCESS_TIP;
    	}else {
    		return ERROR;
    	  }
//    }
  }
    /**
     * 删除公海客户
     * IsDelete 0 :公海
     *          1 :我的客户 
     *          2 :删除标识符
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer crmCustomerId) {
        crmCustomerService.setState(crmCustomerId,2);
        return SUCCESS_TIP;
    }

    /**
     * 修改客户管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CrmCustomer crmCustomer) {
        crmCustomerService.updateById(crmCustomer);
        return SUCCESS_TIP;
    }

 
    /**
     * 客户管理详情
     */
    @RequestMapping(value = "/detailPage/{crmCustomerId}")
    public Object detail(@PathVariable("crmCustomerId") Integer crmCustomerId,Model model) {
    	List<Map<String, Object>> gjjl = crmCusrecordService.showJLlist(crmCustomerId);
    	model.addAttribute("gjjl", new CrmCusrecordWarpper(gjjl).warp());
    	CrmCustomer crmCustomer = crmCustomerService.selectById(crmCustomerId);
    	model.addAttribute("crmCustomer", crmCustomer);
    	model.addAttribute("customerHy",ConstantFactory.me().getKhhangy(crmCustomer.getCustomerHy()));
    	//model.addAttribute("customerStar", ConstantFactory.me().getkhStar(crmCustomer.getCustomerStar()));
    	model.addAttribute("customerState", ConstantFactory.me().getkhstare(crmCustomer.getCustomerState()));
    	model.addAttribute("id", crmCustomerId);
    	return PREFIX + "crmCustomer_detail.html";
    }
/*    @RequestMapping(value= "/detailPage/{crmCustomerId}")
    public String detailPage(@PathVariable("crmCustomerId") Integer crmCustomerId, Model model) {
    	CrmCustomer crmCustomer =  crmCustomerService.selectById(crmCustomerId);
    	model.addAttribute("crmCustomer", crmCustomer);
    	model.addAttribute("id", crmCustomerId);
    	return PREFIX + "crmCustomer_detail.html";
     }*/
    /**
     * 跳转到添加客户管理
     */
    @RequestMapping("/transferTijiao")
    @ResponseBody
    public Object transferTijiao(CrmCustomer crmCustomer) {
    	crmCustomerService.updateById(crmCustomer);
    	return SUCCESS_TIP;
    }
}
