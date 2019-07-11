package com.stylefeng.guns.modular.pm.function.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.pm.function.entity.Function;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sunwei
 * @date 2019/5/29
 */
public interface FunctionDao extends BaseMapper<Function> {
    List<Function> selectListCondition(@Param("functionName") String functionName);
    //根据模块ID查询出功能列表
    List<Function> selectByModuleId(@Param("moduleId") String id);
}