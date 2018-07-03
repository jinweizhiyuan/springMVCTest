package com.hqlip.modules;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class Default {
	
	@RequestMapping("/ModelAndVIew")
	public ModelAndView index() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ModelAndView.jsp");
		mav.addObject("name", "hqlip");
		
		return mav;
	}
}
