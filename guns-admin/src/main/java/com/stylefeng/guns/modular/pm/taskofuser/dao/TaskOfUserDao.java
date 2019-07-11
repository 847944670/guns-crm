package com.stylefeng.guns.modular.pm.taskofuser.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.pm.taskofuser.entity.TaskOfUser;

import java.util.List;

/**
 * @author sunwei
 * @date 2019/5/31
 */
public interface TaskOfUserDao extends BaseMapper<TaskOfUser> {
    List<TaskOfUser> selectTaskOfList();
    void deleteAll();
}