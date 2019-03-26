package com.itzst.tracktedu.test.repository;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.itzst.tracktedu.model.Course;
import com.itzst.tracktedu.repository.CourseRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CourseRepositoryTest {
	
	@Autowired
	private CourseRepository courseRepository;
	
	private Course course;
	
	@Before
	public void setUp() {
		course = new Course();
		course.setCourseId("5b04f7411764e3765c35f8f6");
		course.setSchoolId(1001L);
		course.setCourseCode("ABC");
		course.setCourseName("ABC Course");
		course.setCourseDescription("ABC Description");
		course.setIsActive(false);
		course.setCourseCreated(LocalDateTime.now());
	}
	
	
	
	
	
}
