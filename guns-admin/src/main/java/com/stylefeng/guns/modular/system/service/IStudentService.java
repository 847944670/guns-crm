package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Student;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-17
 */

public interface IStudentService extends IService<Student> {

//    List<Map<String, Object>> list(Page<Student> page, String condition, String orderByField, boolean asc);
    List<Map<String, Object>> list(String condition);
}
