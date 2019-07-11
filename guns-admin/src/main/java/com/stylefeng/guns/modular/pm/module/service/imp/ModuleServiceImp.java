package com.stylefeng.guns.modular.pm.module.service.imp;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.pm.module.dao.ModuleDao;
import com.stylefeng.guns.modular.pm.module.entity.Module;
import com.stylefeng.guns.modular.pm.module.service.ModuleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunwei
 * @date 2019/5/29
 */
@Service
public class ModuleServiceImp extends ServiceImpl<ModuleDao, Module> implements ModuleService {

    @Override
    public List<ZTreeNode> tree(){
       return this.baseMapper.tree();
   }

    @Override
    public List<Module> selectListCondition(String moduleName) {
        return this.baseMapper.selectListCondition(moduleName);
    }

    @Override
    public List<Module> selectByProjectId(String projectId) {
        return this.baseMapper.selectByProjectId(projectId);
    }

    @Override
    public List<Module> selectMyModuleList(String id, String deptId) {
        return this.baseMapper.selectMyModuleList(id, deptId);
    }
}