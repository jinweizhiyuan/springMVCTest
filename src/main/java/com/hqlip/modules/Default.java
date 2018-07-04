package com.hqlip.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class Default {
	
	@RequestMapping("/ModelAndView")
	public ModelAndView index() {
		
		List<Object> li = new ArrayList<>();
		
		for (int i = 0, len = 50; i < len; i++) {
			Map o = new HashMap();
			o.put("qq", "qq:" + i);
			li.add(o);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ModelAndView.jsp");
//		mav.addObject("name", "hqlip");
		mav.addObject("qqs", li);
		
		return mav;
	}
}
