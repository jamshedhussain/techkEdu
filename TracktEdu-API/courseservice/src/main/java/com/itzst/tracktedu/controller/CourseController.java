package com.itzst.tracktedu.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itzst.tracktedu.exception.CourseAllReadyExistException;
import com.itzst.tracktedu.exception.CourseNotCreatedException;
import com.itzst.tracktedu.exception.CourseNotFoundException;
import com.itzst.tracktedu.model.Course;
import com.itzst.tracktedu.service.CourseService;

@RestController
@RequestMapping("/api/v1/")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@PostMapping("course")
	public ResponseEntity<?> createCourse(@RequestBody Course course){
		
		try {
			Course c = courseService.createCourse(course);
			if(c != null) {
				return new ResponseEntity<>(c, HttpStatus.CREATED);
			}
		} catch (CourseAllReadyExistException | CourseNotCreatedException e) {
			return new ResponseEntity<>("Unable to create course.\n "+e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("Unable to create course. ", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("course/{id}")
	public ResponseEntity<?> updateCourse(@RequestBody Course course, @PathVariable("id") String courseId){
		
		if(!courseId.equals(course.getCourseId())) {
			return new ResponseEntity<>("Unable to update course. ", HttpStatus.BAD_REQUEST);
		}
		
		try {
			Course c = courseService.updateCourse(course, courseId);
			if(c != null) {
				return new ResponseEntity<>(c, HttpStatus.OK);
			}
		} catch (CourseNotFoundException | CourseAllReadyExistException e) {
			return new ResponseEntity<>("Unable to update course.\n "+e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("Unable to update course. ", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("course/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable("id") String courseId){
		
		try {
			Boolean flag = courseService.deleteCourse(courseId);
			if(flag) {
				return new ResponseEntity<>("Course deleted successfully.", HttpStatus.OK);
			}
		} catch (CourseNotFoundException e) {
			return new ResponseEntity<>("Unable to delete course.\n "+e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Unable to delete course.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("course/{id}")
	public ResponseEntity<?> getCourseById(@PathVariable("id") String courseId){
		try {
			Course course = courseService.getCourseByCourseId(courseId);
			return new ResponseEntity<>(course, HttpStatus.OK);
		} catch (CourseNotFoundException e) {
			return new ResponseEntity<>("Unable to get course.\n"+e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("courses/all/{id}")
	public ResponseEntity<?> getCoursesBySchoolId(@PathVariable("id") Long schoolId){
		
		try {
			List<Course> listOfCourses = courseService.getCousesBySchoolId(schoolId);
			return new ResponseEntity<>(listOfCourses, HttpStatus.OK);
		} catch (CourseNotFoundException e) {
			return new ResponseEntity<>("Unable to get courses.\n"+e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("test")
	public ResponseEntity<?> getTestData(){
		Course c = new Course();
		c.setCourseCode("ABC");
		c.setCourseName("ABC Course");
		c.setCourseDescription("ABC course description");
		c.setSchoolId(1001L);
		c.setCourseCreated(LocalDateTime.now());
		c.setIsActive(true);
		
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
}