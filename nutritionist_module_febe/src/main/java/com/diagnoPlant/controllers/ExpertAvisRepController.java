package com.diagnoPlant.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diagnoPlant.models.Image;
import com.diagnoPlant.models.MaladiePlante;
import com.diagnoPlant.models.User;
import com.diagnoPlant.repositories.ImageRepository;
import com.diagnoPlant.repositories.MaladiePlanteRepository;
import com.diagnoPlant.services.UserService;

@Controller
public class ExpertAvisRepController {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private MaladiePlanteRepository maladieplRepo;
	@Autowired
    private UserService userService;
	
	@Value("${dir.images}")
	private String imageDir;
	
	@RequestMapping(value = "/avisexpert")
	public String Page(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
		@RequestParam(name = "size", defaultValue = "4") int size) {
		PageRequest pageable = PageRequest.of(page, size);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(auth.getName());
		Page<Image> im = imageRepository.findAllByUser(user,pageable);
		
		try {
		
	        model.addAttribute("currentpage", page);
    		model.addAttribute("size", size);
    		model.addAttribute("image", im);
    		model.addAttribute("pages",new int[im.getTotalPages()]);
		
		}catch (NullPointerException e) {
			
		}
		
		
        return"avisexpert";
	}
    
	
	
	/**
	 * Cette méthode permet de récuperer les images d'un dossier et 
	 * les afficher
	 * @param id
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "/avisexpert", produces = MediaType.IMAGE_JPEG_VALUE)
	
	@ResponseBody
	public byte[] index(Long id) throws FileNotFoundException, IOException {
		File f = new File(imageDir+id);

		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@GetMapping("/reponseexpert")
	public String repondreExp(Model model,@RequestParam("id")Long id) {
		Long idMaladiePlante=null;
		Image img = imageRepository.getOne(id);
		idMaladiePlante = img.getMaladiePlante().getIdMaladiePlante();
		MaladiePlante mlp= maladieplRepo.getOne(idMaladiePlante);
		model.addAttribute("reponseMaladiePl",mlp);
		model.addAttribute("image", img);
		return"reponseexpert";
	}

}
