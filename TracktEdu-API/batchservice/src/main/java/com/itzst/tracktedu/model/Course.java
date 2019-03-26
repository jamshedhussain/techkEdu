package com.itzst.tracktedu.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString


public class Course {

	
	private String courseId;
	private Long schoolId;
	private String courseCode;
	private String courseName;
	private String courseDescription;
	private Boolean isActive;
	private LocalDateTime courseCreated;
	
}
