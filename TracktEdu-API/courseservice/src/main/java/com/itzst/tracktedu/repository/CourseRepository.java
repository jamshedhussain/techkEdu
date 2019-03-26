package com.itzst.tracktedu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.itzst.tracktedu.model.Course;

public interface CourseRepository extends MongoRepository<Course, String>{
	
	public Optional<Course> findCourseBySchoolIdAndCourseCode(Long schoolId, String schoolCode);
	
	public Optional<List<Course>> findCoursesBySchoolId(Long schoolId);
	
	public Optional<List<Course>> findCoursesBySchoolIdAndIsActive(Long schoolId, Boolean isActive);
}
