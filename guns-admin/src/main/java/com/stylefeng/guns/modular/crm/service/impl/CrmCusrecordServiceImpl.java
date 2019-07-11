package com.stylefeng.guns.modular.crm.service.impl;

import com.stylefeng.guns.modular.system.model.CrmCusrecord;
import com.stylefeng.guns.modular.system.dao.CrmCusrecordMapper;
import com.stylefeng.guns.modular.crm.service.ICrmCusrecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 跟进记录表 服务实现类
 * </p>
 *
 * @author wzb
 * @since 2018-09-30
 */
@Service
public class CrmCusrecordServiceImpl extends ServiceImpl<CrmCusrecordMapper, CrmCusrecord> implements ICrmCusrecordService {

	@Override
	public List<Map<String, Object>> showJLlist(Integer crmCustomerId) {
		// TODO Auto-generated method stub
		return this.baseMapper.showJLlist(crmCustomerId);
	}

}
