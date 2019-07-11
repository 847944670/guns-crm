package com.stylefeng.guns.modular.pm.project.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.pm.project.entity.Project;

import java.util.List;

public interface ProjectService extends IService<Project> {
    List<ZTreeNode> tree();

    List<Project> selectListCondition(String projectName);
}
