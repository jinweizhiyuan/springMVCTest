package com.hqlip.modules.imooc.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hqlip.modules.imooc.model.Course;
import com.hqlip.modules.imooc.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {
	
	private static Logger log = LoggerFactory.getLogger(CourseController.class);
	
	private CourseService courseService;
	
	@Autowired
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String viewCourse(@RequestParam("courseId") Integer courseId, Model model) {
		log.debug("In viewCourse, courseId = {}", courseId);
		Course course = courseService.getCourseById(courseId);
		model.addAttribute(course);
		return "/imooc/course_overview";
	}
	
	@RequestMapping(value="/view2/{courseId}", method=RequestMethod.GET)
	public String viewCourse2(@PathVariable("courseId") Integer courseId, Map<String, Object> model) {
		log.debug("In viewCourse2, courseId = {}", courseId);
		Course course = courseService.getCourseById(courseId);
		model.put("course", course);
		return "/imooc/course_overview";
	}
	
	@RequestMapping(value="/view3")
	public String viewCourse3(HttpServletRequest request) {
		Integer courseId = Integer.valueOf(request.getParameter("courseId"));
		Course course = courseService.getCourseById(courseId);
		request.setAttribute("course", course);
		return "/imooc/course_overview";
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.GET, params="add")
	public String createCourse() {
		return "/imooc/course_admin/edit";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String doSave(@ModelAttribute Course course) {
		log.debug("Info of Course:");
		log.debug(ReflectionToStringBuilder.toString(course));
		
//		由此操作业务，比如数据库持久化
		course.setCourseId(123);
		return "redirect:view2/" + course.getCourseId();
	}
	
	@RequestMapping(value="upload", method=RequestMethod.GET)
	public String showUploadPage(@RequestParam(value="multi", required=false) Boolean multi) {
		if (multi != null && multi) {
			return "/imooc/course_admin/multifile";
		} 
		return "/imooc/course_admin/file";
	}
	
	@RequestMapping(value="/doUpload", method=RequestMethod.POST)
	public String doUploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			log.debug("Process file: {}", file.getOriginalFilename());
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File("d:\\", System.currentTimeMillis() + file.getOriginalFilename()));
		}
		return "/imooc/success";
	}
	
	@RequestMapping(value="/doUpload2", method=RequestMethod.POST)
	public String doUploadFile2(MultipartHttpServletRequest multipartRequest) throws IOException {
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile file = multipartRequest.getFile(fileName);
			if(!file.isEmpty()) {
				log.debug("Process file:{}", file.getOriginalFilename());
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File("d:\\", System.currentTimeMillis() + file.getOriginalFilename()));
			}
		}
		return "/imooc/success";
	}
	
	@RequestMapping(value="/{courseId}", method=RequestMethod.GET)
	public @ResponseBody Course getCourseInJson(@PathVariable Integer courseId) {
		return courseService.getCourseById(courseId);
	}
	
	@RequestMapping(value="/jsontype/{courseId}", method=RequestMethod.GET)
	public ResponseEntity<Course> getCourseInJson2(@PathVariable Integer courseId) {
		Course course = courseService.getCourseById(courseId);
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}
	
}