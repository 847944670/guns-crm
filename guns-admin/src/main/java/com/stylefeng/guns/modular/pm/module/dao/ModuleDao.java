package com.stylefeng.guns.modular.pm.module.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.pm.module.entity.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleDao extends BaseMapper<Module> {
    List<ZTreeNode> tree();
    List<Module> selectListCondition(@Param("moduleName") String moduleName);
    List< Module> selectByProjectId(@Param("projectId")String projectId);
    List<Module> selectMyModuleList(@Param("id") String id, @Param("deptId") String deptId);
}
