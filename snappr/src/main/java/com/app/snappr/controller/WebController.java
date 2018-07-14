package com.app.snappr.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.snappr.Entity.FileUploadUtility;
import com.app.snappr.Entity.User;
import com.app.snappr.Entity.UserValidator;
import com.app.snappr.Service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


@Controller
public class WebController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value={"/","/index"})
	public ModelAndView showHome(@RequestParam(name = "login", required = false) String login)
	{
		ModelAndView mv = new ModelAndView("index");
		if(login!=null)
		{
			switch(login)
			{
				case "error" : 
					mv.addObject("error_message","Inavlid email or password");
					break;
				case "regFail":
					mv.addObject("message","User registration failed");
					break;
				case "regSuccess":
					mv.addObject("message","User registered successfully");
					break;
			}
		}
		return mv;
	}
	
	@RequestMapping(value="/signup")
	public ModelAndView showRegister()
	{
		User user = new User();
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("user",user);
		return mv;
	}
	
	@RequestMapping(value="/addUser")
	public String addUser(@Valid @ModelAttribute("user") User mUser,BindingResult results, Model model, HttpServletRequest request)
	{
		
//		if(!mUser.getFile().getOriginalFilename().equals(""))
//			new UserValidator().validate(mUser,results);
		if(results.hasErrors())
		{		
			return "register";
		}
		String s="";
//		if(!mUser.getFile().getOriginalFilename().equals(""))
//		{
//			 s = FileUploadUtility.uploadFile(request, mUser.getFile(), mUser.getCode());
//		}
		boolean b = userService.addUser(mUser);
//		if(b)
//		{
//			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//					  "cloud_name", "avik191",
//					  "api_key", "115178129114887",
//					  "api_secret", "EtXCkchaXbz2l3GedKeAfaaBLdc"));
//			
//			File toUpload = new File(s);
//			Map params = ObjectUtils.asMap("public_id", "Snappr/Profile_Pic/"+mUser.getCode());
//			Map uploadResult;
//			try {
//				uploadResult = cloudinary.uploader().upload(toUpload, params);
//				String publicId = (String) uploadResult.get("secure_url");
//				System.out.println(publicId);
//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			//			user.setPath(publicId);
//		}
		if(b)
			return "redirect:/index?login=regSuccess";
		else
			return "redirect:/index?login=regFail";

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
