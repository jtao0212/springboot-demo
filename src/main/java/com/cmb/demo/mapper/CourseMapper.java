package com.cmb.demo.mapper;

import com.cmb.demo.bean.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CourseMapper {

    List<Course> getCourseByTime(@Param(value = "courseTime") String courseTime);

}
