package com.stylefeng.guns.modular.system.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.dao.StudentMapper;
import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.modular.system.service.IStudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-17
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

//    @Override
//    public List<Map<String, Object>> list(Page<Student> page, String condition, String orderByField, boolean asc) {
//        return this.baseMapper.list(page,condition, orderByField, asc);
//    }

    @Override
    public List<Map<String, Object>> list(String condition) {
        return this.baseMapper.list(condition);
    }
}
