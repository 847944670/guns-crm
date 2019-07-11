package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.CrmCusrecord;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 跟进记录表 Mapper 接口
 * </p>
 *
 * @author wzb
 * @since 2018-09-30
 */
public interface CrmCusrecordMapper extends BaseMapper<CrmCusrecord> {

	List<Map<String, Object>> showJLlist(@Param("id")Integer crmCustomerId);

}
