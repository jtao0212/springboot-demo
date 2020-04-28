package com.cmb.demo.mapper;

import com.cmb.demo.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentMapper {

    List<Student> getStudentByTime(@Param(value = "studentTime") String studentTime);

}
