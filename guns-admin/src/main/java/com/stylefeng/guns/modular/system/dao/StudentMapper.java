package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.system.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-17
 */
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 获取通知列表
     */
//    List<Map<String, Object>> list(@Param("page") Page<Student> page, @Param("condition") String condition, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);

    List<Map<String, Object>> list(@Param("condition") String condition);
}
