package com.stylefeng.guns.modular.pm.project.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.pm.project.entity.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectDao extends BaseMapper<Project> {

    List<ZTreeNode> tree();
    List<Project> selectListCondition(@Param("projectName") String projectName);

}
