package com.stylefeng.guns.modular.crm.service;

import com.stylefeng.guns.modular.system.model.CrmSalechance;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 销售机会表 服务类
 * </p>
 *
 * @author wzb
 * @since 2018-09-30
 */
public interface ICrmSalechanceService extends IService<CrmSalechance> {

	List<Map<String, Object>> getSalesChanceDetail(Integer crmSalechanceId);

	List<Map<String, Object>> getChanceList(Integer customId, Integer saleState);

}
