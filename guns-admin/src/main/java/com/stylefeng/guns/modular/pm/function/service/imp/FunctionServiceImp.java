package com.stylefeng.guns.modular.pm.function.service.imp;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.pm.function.dao.FunctionDao;
import com.stylefeng.guns.modular.pm.function.entity.Function;
import com.stylefeng.guns.modular.pm.function.service.FunctionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunwei
 * @date 2019/5/29
 */
@Service
public class FunctionServiceImp extends ServiceImpl<FunctionDao, Function>implements FunctionService {
    @Override
    public List<Function> selectListCondition(String functionName) {
        return this.baseMapper.selectListCondition(functionName);
    }

    @Override
    public List<Function> selectByModuleId(String id) {
        return this.baseMapper.selectByModuleId(id);
    }
}