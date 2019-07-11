package com.stylefeng.guns.modular.crm.service;

import com.stylefeng.guns.modular.system.model.CrmCustomer;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author wzb
 * @since 2018-09-30
 */
public interface ICrmCustomerService extends IService<CrmCustomer> {

	/**
	 * 捞取功能
	 * @param id
	 * @param salerId
	 * @param salerName
	 * @param i 
	 * @return
	 */
	int laoqu(Integer id, int salerId, String salerName, int i);
    
	/**
	 * 获取我的客户列表
	 * @param salerId
	 * @return
	 */
	List<Map<String, Object>> selectMyList(int salerId);
	
	/**
	 * 删除公海客户
	 * @param salerId
	 * @return
	 */
	int setState(Integer crmCustomerId, int i);

	/**
	 * 获取公海客户列表
	 * @return
	 */
	List<Map<String, Object>> selectGhList();

	/**
	 * 查电话号码
	 * @param phone
	 * @return
	 */
	int findPhone(String phone);

	/**
	 * 退回公海
	 * @param crmCustomerId
	 * @return
	 */
	int tuihui(Integer crmCustomerId);

	/**
	 * 我的客户人数
	 * @param userId 销售人ID
	 * @param i 成交客户状态 5
	 * @param j 我的客户标识 1
	 * @return
	 */
	int personCount(int userId, int i, int j);

}
