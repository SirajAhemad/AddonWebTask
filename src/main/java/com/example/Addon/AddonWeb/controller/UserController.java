package com.example.Addon.AddonWeb.controller;

import com.example.Addon.AddonWeb.constants.ApplicationURLConstants;
import com.example.Addon.AddonWeb.entity.User;
import com.example.Addon.AddonWeb.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.servlet.http.HttpSession;

@RequestMapping(ApplicationURLConstants.USER)
@Controller
public class UserController extends BaseController{

    @RequestMapping("")
    public String openHomePage(){

        return "home";
    }

    @RequestMapping(value = ApplicationURLConstants.VIEW, method = RequestMethod.GET)
    public String openViewPage(Model m){    
    	
    	List<User> user = getServiceRegistry().getUserService().findAll();
        m.addAttribute("users", user);
    	return "listView";
    }

    @PostMapping(ApplicationURLConstants.SAVE_USER)
    public String saveUser(@ModelAttribute User user, @RequestParam("image")MultipartFile file){

		if(file.isEmpty())
		{
			System.out.println("File is empty");

		}
		user.setImageUrl(file.getOriginalFilename());
		getFileUploadHelper().uploadFile(file);

		getServiceRegistry().getUserService().saveUser(user);

            return "home";
    }
    
	@GetMapping(ApplicationURLConstants.DELETE_USER+ApplicationURLConstants.ID)
	public String delete(@PathVariable("id") Integer id,HttpSession session)
	{
	
		getServiceRegistry().getUserService().deleteById(id);
		return "redirect:/user/view";
	}
	
	//open update form handler
	
		@PostMapping(ApplicationURLConstants.UPDATE+ApplicationURLConstants.ID)
		public String update(@PathVariable("id") Integer id  ,Model m)
		{
			m.addAttribute("title" , "update contact");
			User user = getServiceRegistry().getUserService().findById(id).get();
			m.addAttribute("users", user);
			
			return "form_update";
		}
		//update contact handler
		@RequestMapping(value = "/process-update",method = RequestMethod.POST)
		public String updateHandler(@ModelAttribute User user,@RequestParam("profileImage") MultipartFile file,Model m,HttpSession session,Principal principal)
		{
			try {
				User oldUser = null;
				//old contact detail
				
				Optional<User> optionalUser = getServiceRegistry().getUserService().findById(user.getId());
				if (optionalUser.isPresent()){
					oldUser = optionalUser.get();
				}
				
				//image..
				if(!file.isEmpty())
				{
					//file work..
					//delete old photo
					//update new photo
					getFileUploadHelper().uploadFile(file);
					 user.setImageUrl(file.getOriginalFilename());
				}
				else
				{
					user.setImageUrl(oldUser.getImageUrl());
				}

				getServiceRegistry().getUserService().saveUser(user);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/user/"+user.getId()+"user";
			
		}
}
