package com.stylefeng.guns.modular.pm.project.service.imp;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.pm.project.entity.Project;
import com.stylefeng.guns.modular.pm.project.dao.ProjectDao;
import com.stylefeng.guns.modular.pm.project.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunwei
 * @date 2019/5/29
 */
@Service
public class ProjectServiceImp extends ServiceImpl<ProjectDao, Project> implements ProjectService {
    @Override
    public List<ZTreeNode> tree() {
        return this.baseMapper.tree();
    }

    @Override
    public List<Project> selectListCondition(String projectName) {
        return this.baseMapper.selectListCondition(projectName);
    }


}