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


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Document
public class Batch {
	
	@Id
	private String batchId;
	private Long schoolId;
	private String batchCode;
	private String batchName;
	private String batchDescription;
	private Boolean isActive;
	private LocalDateTime batchCreated;
	
}