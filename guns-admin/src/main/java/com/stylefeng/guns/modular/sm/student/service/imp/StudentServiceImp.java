package com.stylefeng.guns.modular.sm.student.service.imp;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.sm.student.dao.StudentDao;
import com.stylefeng.guns.modular.sm.student.entity.StudentEntity;
import com.stylefeng.guns.modular.sm.student.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImp extends ServiceImpl<StudentDao, StudentEntity> implements StudentService {


}
