package com.itzst.tracktedu.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Document
public class Course {

	@Id
	private String courseId;
	private Long schoolId;
	private String courseCode;
	private String courseName;
	private String courseDescription;
	private Boolean isActive;
	private LocalDateTime courseCreated;
	
}
