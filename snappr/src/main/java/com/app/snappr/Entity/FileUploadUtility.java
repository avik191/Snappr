package com.app.snappr.Entity;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	private static final String ABS_PATH = "F:\\eclipse-workspace\\DemoSpring\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH = null;
	public static String uploadFile(HttpServletRequest request, MultipartFile file, String code) 
	{				
		// get the real server path
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		System.out.println(REAL_PATH);
		
		
		if(!new File(REAL_PATH).exists()) {
			new File(REAL_PATH).mkdirs();
		}
		
//		if(!new File(ABS_PATH).exists()) {
//			new File(ABS_PATH).mkdirs();
//		}
//		
		try {
			//transfer the file to both the location
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			//file.transferTo(new File(ABS_PATH + code + ".jpg"));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return REAL_PATH + code + ".jpg";
	}
}
