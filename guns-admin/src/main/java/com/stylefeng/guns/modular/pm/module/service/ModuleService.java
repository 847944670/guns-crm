package com.stylefeng.guns.modular.pm.module.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.pm.module.entity.Module;

import java.util.List;

public interface ModuleService extends IService<Module> {
    List<ZTreeNode> tree();

    List<Module> selectListCondition(String moduleName);
    //通过项目ID删除模块
   List<Module> selectByProjectId(String projectId);
    //获取当前项目组长的新建模块列表
    List<Module> selectMyModuleList(String id, String deptId);
}
