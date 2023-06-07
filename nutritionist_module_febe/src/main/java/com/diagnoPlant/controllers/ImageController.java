package com.diagnoPlant.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.diagnoPlant.models.Image;
import com.diagnoPlant.models.User;
import com.diagnoPlant.repositories.ImageRepository;
import com.diagnoPlant.repositories.UserRepository;

/**
 * This class used to upload image and store it in a folder 
 * @author lahcen
 *
 */
@Controller
public class ImageController {
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private UserRepository usrRepo;

	@Value("${dir.images}")
	private String imageDir;
	
	@GetMapping(value ="/image")
	public String telechargerImage(Model model) {
		Image mg = new Image();
		model.addAttribute("image",mg );
		
		
  
		return"telechargerimage";
	}
	  
	@PostMapping(value ="/image")
	public String validerImage(Image im,@RequestParam(name="photo")MultipartFile file,
										@RequestParam(name="idUser")Long idUser) 
												throws IllegalStateException, IOException {
  
		im.setEtatTraitement(false);
		
  
		imageRepository.save(im);
		if(!file.isEmpty()) {
			im.setImage(file.getOriginalFilename());
			file.transferTo(new File(imageDir+im.getId()));
		} 
		
		User usr= usrRepo.getOne(idUser);
		im.setUser(usr);
		imageRepository.save(im);
		
   
	return"confirmationupload";
	}
	
	
}