package com.stylefeng.guns.modular.crm.service;

import com.stylefeng.guns.modular.system.model.CrmCusrecord;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 跟进记录表 服务类
 * </p>
 *
 * @author wzb
 * @since 2018-09-30
 */
public interface ICrmCusrecordService extends IService<CrmCusrecord> {

	List<Map<String, Object>> showJLlist(Integer crmCustomerId);

}
