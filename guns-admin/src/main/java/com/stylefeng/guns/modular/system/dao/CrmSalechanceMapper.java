package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.CrmSalechance;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 销售机会表 Mapper 接口
 * </p>
 *
 * @author wzb
 * @since 2018-09-30
 */
public interface CrmSalechanceMapper extends BaseMapper<CrmSalechance> {

	List<Map<String, Object>> getSalesChanceDetail(@Param("crmSalechanceId") Integer crmSalechanceId);

	List<Map<String, Object>> getChanceList(@Param("customId") Integer customId, @Param("saleState") Integer saleState);

}
