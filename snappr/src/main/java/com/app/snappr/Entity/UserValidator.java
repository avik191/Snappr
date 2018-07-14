package com.app.snappr.Entity;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		User user = (User) target;
		if(user.getFile() == null || user.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file", null, "Please select a file to upload!");
			return;
		}
		if(! (user.getFile().getContentType().equals("image/jpeg") || 
				user.getFile().getContentType().equals("image/png")) ||
				user.getFile().getContentType().equals("image/gif")
			 )
			{
				errors.rejectValue("file", null, "Please select an image file to upload!");
				return;	
			}

	}

}
