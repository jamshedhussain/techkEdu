package com.itzst.tracktedu.service;

import java.util.List;

import com.itzst.tracktedu.exception.CourseAllReadyExistException;
import com.itzst.tracktedu.exception.CourseNotCreatedException;
import com.itzst.tracktedu.exception.CourseNotFoundException;
import com.itzst.tracktedu.model.Course;

public interface CourseService {
	
	public Course createCourse(Course course) throws CourseAllReadyExistException, CourseNotCreatedException;
	
	public Course updateCourse(Course course, String courseId) throws CourseAllReadyExistException, CourseNotFoundException;
	
	public Boolean deleteCourse(String courseId) throws CourseNotFoundException;
	
	public Course getCourseByCourseId(String courseId) throws CourseNotFoundException;
	
	public Course getCourseByCodeForSchool(Long schoolId, String courseCode) throws CourseNotFoundException;
	
	public List<Course> getCousesBySchoolId(Long schoolId) throws CourseNotFoundException;
	
	public List<Course> getActiveCousesBySchoolId(Long schoolId) throws CourseNotFoundException;
}
