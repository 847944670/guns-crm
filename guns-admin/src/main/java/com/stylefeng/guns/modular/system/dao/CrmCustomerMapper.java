package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.CrmCustomer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 客户表 Mapper 接口
 * </p>
 *
 * @author wzb
 * @since 2018-09-30
 */
public interface CrmCustomerMapper extends BaseMapper<CrmCustomer> {

	int laoqu(@Param("id")Integer id, @Param("salerId")Long salerId, @Param("salerName")String salerName, @Param("i")int i);

	List<Map<String, Object>> selectMyList(@Param("salerId")long salerId);

	int setState(@Param("id")Integer crmCustomerId, @Param("i")int i);

	List<Map<String, Object>> selectGhList();

	int findPhone(@Param("phone")String phone);

	int tuihui(@Param("id")Integer crmCustomerId);

	int personCount(@Param("id")int userId, @Param("i")int i, @Param("j")int j);

}
