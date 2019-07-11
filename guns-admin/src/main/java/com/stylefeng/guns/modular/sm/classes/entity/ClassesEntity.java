package com.stylefeng.guns.modular.sm.classes.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("classes")
public class ClassesEntity {
    @TableId(value = "id",type = IdType.UUID)
    private  String id;
    //班级姓名
    private  String classesName;
    //学生姓名
    private  String studentName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "ClassesEntity{" +
                "id='" + id + '\'' +
                ", classesName='" + classesName + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
