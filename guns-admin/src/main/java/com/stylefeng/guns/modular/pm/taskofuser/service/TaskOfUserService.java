package com.stylefeng.guns.modular.pm.taskofuser.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.pm.taskofuser.entity.TaskOfUser;

import java.util.List;

/**
 * @author sunwei
 * @date 2019/5/31
 */
public interface TaskOfUserService extends IService<TaskOfUser> {
    List<TaskOfUser> selectTaskOfList();

    void deleteAll();
}