package com.stylefeng.guns.modular.pm.function.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.pm.function.entity.Function;

import java.util.List;

public interface FunctionService extends IService<Function> {
    List<Function> selectListCondition(String functionName);
    //根据模块ID查询出功能列表
    List<Function> selectByModuleId(String id);
}
