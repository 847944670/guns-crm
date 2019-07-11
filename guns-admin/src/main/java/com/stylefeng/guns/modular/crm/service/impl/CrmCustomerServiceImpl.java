package com.stylefeng.guns.modular.crm.service.impl;

import com.stylefeng.guns.modular.system.model.CrmCustomer;
import com.stylefeng.guns.modular.system.dao.CrmCustomerMapper;
import com.stylefeng.guns.modular.crm.service.ICrmCustomerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author wzb
 * @since 2018-09-30
 */
@Service
public class CrmCustomerServiceImpl extends ServiceImpl<CrmCustomerMapper, CrmCustomer> implements ICrmCustomerService {

	@Override
	public int laoqu(Integer id, int salerId, String salerName,int i) {
		// TODO Auto-generated method stub
		return this.baseMapper.laoqu(id,(long) salerId,salerName,i);
	}

	@Override
	public List<Map<String, Object>> selectMyList(int salerId) {
		// TODO Auto-generated method stub
		return this.baseMapper.selectMyList((long) salerId);
	}

	@Override
	public int setState(Integer crmCustomerId, int i) {
		// TODO Auto-generated method stub
		return this.baseMapper.setState(crmCustomerId,i);
	}

	@Override
	public List<Map<String, Object>> selectGhList() {
		// TODO Auto-generated method stub
		return this.baseMapper.selectGhList();
	}

	@Override
	public int findPhone(String phone) {
		// TODO Auto-generated method stub
		return this.baseMapper.findPhone(phone);
	}

	@Override
	public int tuihui(Integer crmCustomerId) {
		// TODO Auto-generated method stub
		return this.baseMapper.tuihui(crmCustomerId);
	}

	@Override
	public int personCount(int userId, int i, int j) {
		// TODO Auto-generated method stub
		return this.baseMapper.personCount(userId,i,j);
	}

}
