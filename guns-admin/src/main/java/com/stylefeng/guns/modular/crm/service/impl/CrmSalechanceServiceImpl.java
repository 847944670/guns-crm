package com.stylefeng.guns.modular.crm.service.impl;

import com.stylefeng.guns.modular.system.model.CrmSalechance;
import com.stylefeng.guns.modular.system.dao.CrmSalechanceMapper;
import com.stylefeng.guns.modular.crm.service.ICrmSalechanceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 销售机会表 服务实现类
 * </p>
 *
 * @author wzb
 * @since 2018-09-30
 */
@Service
public class CrmSalechanceServiceImpl extends ServiceImpl<CrmSalechanceMapper, CrmSalechance>
		implements ICrmSalechanceService {

	@Autowired
	private CrmSalechanceMapper salesChanceMapper;

	@Override
	public List<Map<String, Object>> getSalesChanceDetail(Integer crmSalechanceId) {
		return salesChanceMapper.getSalesChanceDetail(crmSalechanceId);
	}

	@Override
	public List<Map<String, Object>> getChanceList(Integer customId, Integer saleState) {
		return salesChanceMapper.getChanceList(customId, saleState);
	}

}
