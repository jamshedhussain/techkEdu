package com.itzst.tracktedu.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itzst.tracktedu.exception.CourseAllReadyExistException;
import com.itzst.tracktedu.exception.CourseNotCreatedException;
import com.itzst.tracktedu.exception.CourseNotFoundException;
import com.itzst.tracktedu.model.Course;
import com.itzst.tracktedu.repository.CourseRepository;


@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@Override
	public Course createCourse(Course course) throws CourseAllReadyExistException, CourseNotCreatedException {
		
		Optional<Course> optCourse = courseRepository.findCourseBySchoolIdAndCourseCode(course.getSchoolId(), course.getCourseCode());
		if(optCourse.isPresent()) {
			if(course.getCourseCode().equalsIgnoreCase(optCourse.get().getCourseCode())) {
				throw new CourseAllReadyExistException("Course all ready exist.");
			}
		}		
		course.setCourseCreated(LocalDateTime.now());
		
		return courseRepository.insert(course);
	}

	@Override
	public Course updateCourse(Course course, String courseId) throws CourseAllReadyExistException, CourseNotFoundException {
		
		Optional<Course> optCourse = courseRepository.findById(courseId);
		if(optCourse.isPresent()) {
			optCourse = courseRepository.findCourseBySchoolIdAndCourseCode(course.getSchoolId(), course.getCourseCode());
			if(optCourse.isPresent() && !optCourse.get().getCourseId().equals(courseId)) {
				throw new CourseAllReadyExistException("Course allready exist.");
			}
			return courseRepository.save(course);
		}else {
			throw new CourseNotFoundException("Course not found for update");
		}
		
	}

	@Override
	public Boolean deleteCourse(String courseId) throws CourseNotFoundException {
		Optional<Course> optCourse = courseRepository.findById(courseId);
		if(optCourse.isPresent()) {
			courseRepository.deleteById(courseId);
			return true;
		}else {
			throw new CourseNotFoundException("Course not found for delete.");
		}
	}

	@Override
	public Course getCourseByCourseId(String courseId) throws CourseNotFoundException {
		Optional<Course> optCourse = courseRepository.findById(courseId);
		if(optCourse.isPresent()) {
			return optCourse.get();
		}else {
			throw new CourseNotFoundException("Course not found.");
		}
	}

	@Override
	public Course getCourseByCodeForSchool(Long schoolId, String courseCode) throws CourseNotFoundException {
		Optional<Course> optCourse = courseRepository.findCourseBySchoolIdAndCourseCode(schoolId, courseCode);
		if(optCourse.isPresent()) {
			return optCourse.get();
		}else {
			throw new CourseNotFoundException("Course not found.");
		}
	}

	@Override
	public List<Course> getCousesBySchoolId(Long schoolId) throws CourseNotFoundException {
		Optional<List<Course>> optListOfCourses = courseRepository.findCoursesBySchoolId(schoolId);
		
		if(optListOfCourses.isPresent()) {
			return optListOfCourses.get();
		}else {
			throw new CourseNotFoundException("Course not found");
		}
	}

	@Override
	public List<Course> getActiveCousesBySchoolId(Long schoolId) throws CourseNotFoundException {
		Optional<List<Course>> optListOfCourses = courseRepository.findCoursesBySchoolIdAndIsActive(schoolId, true);
		
		if(optListOfCourses.isPresent()) {
			return optListOfCourses.get();
		}else {
			throw new CourseNotFoundException("Course not found");
		}
	}
}
