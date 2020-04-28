package com.cmb.demo.bean;


import com.cmb.demo.utils.DateUtil;

import java.io.Serializable;
import java.sql.Timestamp;

public class Course implements Serializable {

    private String courseId;
    private String studentId;

    private String courseName;

    private String teacher;

    private double mark;

    private Timestamp lastModifiedTime;


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public Timestamp getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Timestamp lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teacher='" + teacher + '\'' +
                ", mark=" + mark +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}
