package com.app.snappr.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.app.snappr.DAO.UserDAO;
import com.app.snappr.Entity.Comment;
import com.app.snappr.Entity.CommentDisplay;
import com.app.snappr.Entity.FileUploadUtility;
import com.app.snappr.Entity.Likes;
import com.app.snappr.Entity.Post;
import com.app.snappr.Entity.User;
import com.app.snappr.Entity.UserValidator;
import com.app.snappr.Service.CommentService;
import com.app.snappr.Service.LikeService;
import com.app.snappr.Service.PostService;
import com.app.snappr.Service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


@Controller
public class WebController {

	@Autowired
	UserService userService;
	@Autowired
	PostService postService;
	@Autowired
	CommentService commentService;
	@Autowired
	LikeService likeService;
	
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
	public String handlelogin(@RequestParam("email") String email,@RequestParam("pass") String pass,HttpServletRequest request )
	{
		User user = userService.getUserFromEmail(email);
		if(user!=null)
		{
			if(pass.equals(user.getPassword()))
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("loggedUser",user);
				session.setAttribute("isLoggedIn", true);
				return "redirect:/homePage";
			}
		}
		return "redirect:/index?login=error";
	}
	
	
	@RequestMapping(value="/homePage")
	public ModelAndView showBasePage()
	{
		ModelAndView mv = new ModelAndView("basepage");
		mv.addObject("showFeeds",true);
		mv.addObject("title","Feeds");
		return mv;
	}
	
	@RequestMapping(value="/postPage")
	public ModelAndView showPostPage(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("basepage");
		mv.addObject("showPosts",true);
		mv.addObject("title","Posts");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedUser");
		List<Post> list = postService.getPostListFromUserId(user.getId());
		
		
		mv.addObject("postList", list);
		return mv;
	}
	
	/////////////////////////////////////////////////////////
	@RequestMapping(value="/testPage")
	public ModelAndView test()
	{
		ModelAndView mv = new ModelAndView("test");
		return mv;
	}
	
	@RequestMapping(value="/picPage")
	public ModelAndView uploadPic()
	{
		ModelAndView mv = new ModelAndView("picUpload");
		return mv;
	}
	
	
	
	@RequestMapping(value="/test/ajax")
	@ResponseBody
	public List<User> fetchUser(@RequestParam("start") int start,@RequestParam("limit") int limit)
	{
		List<User> user = userService.getAllUsers(start,limit);
		return user;
	}
	
	///////////////////////////////////////////////////////
	
	@RequestMapping(value="/commentList")
	@ResponseBody
	public List<CommentDisplay> fetchCommentList(@RequestParam("id") int id)
	{
		List<Comment> commentList = commentService.getCommentListFromPostId(id);
		List<CommentDisplay> list = new ArrayList();
		for(Comment comment : commentList)
		{
			CommentDisplay cd = new CommentDisplay();
			User u = userService.getUserFromId(comment.getUser_id());
			cd.setId(comment.getId());
			cd.setDescription(comment.getDescription());
			cd.setUsername(u.getName());
			
			list.add(cd);
		}
		return list;
	}
	
	@RequestMapping(value="/getPosterName")
	@ResponseBody
	public String getPosterName(@RequestParam("id") int id)
	{
		User user = userService.getUserFromId(id);
		return user.getName();
	}
	
	@RequestMapping(value="/addComment")
	@ResponseBody
	public int addComment(@RequestParam("post_id") int postId,@RequestParam("user_id") int userId,@RequestParam("descritpion") String comment)
	{
		int b = commentService.addComment(postId, userId, comment);
		return b;

	}
	
	@RequestMapping(value="/removeComment")
	@ResponseBody
	public String removeComment(@RequestParam("comment_id") int commentId)
	{
		System.out.println(commentId);
		Comment comment = commentService.getCommentFromId(commentId);
		System.out.println(comment.getId()+" / "+comment.getDescription());
		boolean b = commentService.removeComment(comment);
		return "success";

	}
	
	@RequestMapping(value="/updateLike")
	@ResponseBody
	public String updateLike(@RequestParam("id") int postId,@RequestParam("like_count") int like)
	{
		Post post = postService.getPostFromId(postId);
		post.setLikes(like);
		boolean b = postService.updateLike(post);
		return "success";

	}
	
	@RequestMapping(value="/getLike")
	@ResponseBody
	public int getLike(@RequestParam("id") int postId)
	{
		
		Post post = postService.getPostFromId(postId);
		return post.getLikes();
	}
	
	@RequestMapping(value="/checkLike")
	@ResponseBody
	public int checkLike(@RequestParam("postid") int postId,@RequestParam("userid") int userId)
	{
		
		Likes like = likeService.getLikeFromPostIdAndUserId(postId, userId);
		if(like == null)
			return 0;
		else
			return 1;
	}
	
	@RequestMapping(value="/likeUpdateUrl")
	@ResponseBody
	public boolean likeUpdateUrl(@RequestParam("post_id") int postId,@RequestParam("user_id") int userId)
	{
		
		Likes like = likeService.getLikeFromPostIdAndUserId(postId, userId);
		if(like == null)
		{
			Likes likes = new Likes();
			likes.setPost_id(postId);
			likes.setUser_id(userId);
			return likeService.insertLike(likes);
		}
			
		else
			return likeService.deleteLike(like);
	}
	
	@RequestMapping(value="/handlePicUpload")
	public String handlePicUpload(@RequestParam("file")MultipartFile file,@RequestParam("desc")String desc,@RequestParam("location")String location,HttpServletRequest request)
	{
		String s = FileUploadUtility.uploadFile(request, file, "abcd");
		System.out.println(s);
		Post post = new Post();
		post.setDescription(desc);
		post.setLocation(location);
		
		Date d = new Date();
        String temp[] = d.toString().split(" ");
        System.out.println(temp[1]+" "+temp[2]);
		
		post.setDate(temp[1]+" "+temp[2]);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedUser");
		post.setUser_id(user.getId());
		
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "avik191",
				  "api_key", "115178129114887",
				  "api_secret", "EtXCkchaXbz2l3GedKeAfaaBLdc"));
		
		File toUpload = new File(s);
		Map params = ObjectUtils.asMap("public_id", "Snappr/Posts/"+UUID.randomUUID().toString().substring(26).toUpperCase());
		Map uploadResult;
		try {
			uploadResult = cloudinary.uploader().upload(toUpload, params);
			String publicId = (String) uploadResult.get("secure_url");
			System.out.println(publicId);
			post.setPath(publicId);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		
		boolean b = postService.addPost(post);
		user.setPosts(user.getPosts()+1);
		userService.updateUser(user);

		return "redirect:/homePage";
	}
}
