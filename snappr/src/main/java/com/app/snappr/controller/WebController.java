package com.app.snappr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.snappr.Entity.User;
import com.app.snappr.Service.UserService;


@Controller
public class WebController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value={"/","/index"})
	public ModelAndView showHome(@RequestParam(name = "login", required = false) String login)
	{
		ModelAndView mv = new ModelAndView("index");
		if(login!=null)
			mv.addObject("message","Inavlid email or password");
		return mv;
	}
	
	
	@RequestMapping(value="/handleLogin")
	public String handlelogin(@RequestParam("email") String email,@RequestParam("pass") String pass)
	{
		User user = userService.getUserFromEmail(email);
		if(user!=null)
		{
			if(pass.equals(user.getPassword()))
			{
				return "redirect:/basePage";
			}
		}
		return "redirect:/index?login=error";
	}
	
	
	@RequestMapping(value="/basePage")
	public ModelAndView showBasePage()
	{
		ModelAndView mv = new ModelAndView("basepage");
		return mv;
	}
}
