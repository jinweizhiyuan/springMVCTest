package com.hqlip.modules.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class Default {
	
	private static Logger log = LoggerFactory.getLogger(Default.class);
	
	@RequestMapping("/ModelAndView")
	public ModelAndView index() {
		
		log.debug("===========ModlAndView");
		
		List<Object> li = new ArrayList<>();
		
		for (int i = 0, len = 50; i < len; i++) {
			Map o = new HashMap();
			o.put("qq", "qq:" + i);
			li.add(o);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/test/ModelAndView");
//		mav.addObject("name", "hqlip");
		mav.addObject("qqs", li);
		
		return mav;
	}
}
