package com.stylefeng.guns.modular.pm.taskofuser.service.imp;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.pm.taskofuser.dao.TaskOfUserDao;
import com.stylefeng.guns.modular.pm.taskofuser.entity.TaskOfUser;
import com.stylefeng.guns.modular.pm.taskofuser.service.TaskOfUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunwei
 * @date 2019/5/31
 */
@Service
public class TaskOfUserServiceImp extends ServiceImpl<TaskOfUserDao, TaskOfUser>implements TaskOfUserService {
    @Override
    public List<TaskOfUser> selectTaskOfList() {
        return this.baseMapper.selectTaskOfList();
    }

    @Override
    public void deleteAll() {
        this.baseMapper.deleteAll();
    }
}