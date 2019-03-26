package com.itzst.tracktedu.aspectj;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	
	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution(* createCourse(..))")
	public void beforeCreate(){
		logger.info("Logger called before create.");
	}

	@After("execution(* updateCourse(..))")
	public void afterUpdate(){
		logger.info("Logger called after update.");
	}

	@AfterReturning("execution(* getCourseById*(..))")
	public void afterGet(){
		logger.info("Logger called after returning get.");
	}

	@AfterThrowing("execution(* deleteCourse(..))")
	public void afterError(){
		logger.info("Logger called after throwing error.");
	}
	
}
