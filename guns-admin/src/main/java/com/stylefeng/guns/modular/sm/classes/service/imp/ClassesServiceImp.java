package com.stylefeng.guns.modular.sm.classes.service.imp;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.sm.classes.dao.ClassesDao;
import com.stylefeng.guns.modular.sm.classes.entity.ClassesEntity;
import com.stylefeng.guns.modular.sm.classes.service.ClassesService;
import org.springframework.stereotype.Service;

@Service
public class ClassesServiceImp extends ServiceImpl<ClassesDao, ClassesEntity> implements ClassesService {


}
