package com.hqlip.modules.imooc.service;

import org.springframework.stereotype.Service;

import com.hqlip.modules.imooc.model.Course;

@Service
public interface CourseService {
	
	Course getCourseById(Integer courseId);

}
