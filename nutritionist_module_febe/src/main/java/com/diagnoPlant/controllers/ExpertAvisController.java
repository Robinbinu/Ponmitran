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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diagnoPlant.models.Image;
import com.diagnoPlant.models.MaladiePlante;
import com.diagnoPlant.repositories.ImageRepository;
import com.diagnoPlant.repositories.MaladiePlanteRepository;


/**
 * @author JAVA WARIORS
 * Ce controleur permet de gérer l'espace de l'expert 
 * L'expert donne son avis sur l'image à traiter
 */
@Controller
public class ExpertAvisController {

	@Autowired
	private MaladiePlanteRepository maladiePlanteRepository;

	@Autowired
	private ImageRepository imageRepository;
	
	@Value("${dir.images}")
	private String imageDir;

	/**
	 * 
	 * 
	 * Ces deux méthodes permettent de remplir de récupérer les données saisies
	 * et de les enregistrer dans la base de données pour ajouter une nouvelle
	 * maladie 
	 */
	
	
	@RequestMapping(value = "/ajoutermaladie", method = RequestMethod.GET)
	public String ajouterMaladie(Model model) {
		model.addAttribute("maladiePlante", new MaladiePlante());

		return "ajoutermaladie";
	}

	@RequestMapping(value = "/ajoutermaladie", method = RequestMethod.POST)
	public String ajouterMaladie(MaladiePlante m) {
		maladiePlanteRepository.save(m);

		return "confirmationexpert";
	}

	/**
	 * Cette méthode permet d'afficher les images dans plusieurs pages
	 * @param model
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/pageexpert")

	
	public String Page(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "4") int size) {
		PageRequest pageable = PageRequest.of(page, size);
		Page<Image> im = imageRepository.findAll(pageable);
		model.addAttribute("currentpage", page);
		model.addAttribute("size", size);
		model.addAttribute("image", im);
        model.addAttribute("pages",new int[im.getTotalPages()]);
        return"pageexpert";
	}
    
	
	
	/**
	 * Cette méthode permet de récuperer les images d'un dossier et 
	 * les afficher
	 * @param id
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "/pageexpert", produces = MediaType.IMAGE_JPEG_VALUE)
	
	@ResponseBody
	public byte[] index(Long id) throws FileNotFoundException, IOException {
		File f = new File(imageDir+id);

		return IOUtils.toByteArray(new FileInputStream(f));
	}
    
	
	
	/**
	 * Cette méthode permet d'afficher l'image à triater et
	 * les champes à remplir
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/donneravis",method = RequestMethod.GET)
    
	public String Avis(@RequestParam(value ="id") Long id, Model model) {

		Image img = imageRepository.getOne(id);
		model.addAttribute("image", img);

		
		  Image mage = new Image(); 
		  mage.setId(img.getId());
		  mage.setImage(img.getImage()); 
		  mage.setEtatTraitement(true);
		  mage.setInfosCompl(null);
		  mage.setMaladiePlante(null);
		  mage.setUser(img.getUser());
		  
		  model.addAttribute("magic", mage);
		 

		Iterable<MaladiePlante> list = maladiePlanteRepository.findAll();
		model.addAttribute("maladie", list);
		
		return "donneravis";
	}
	
	
	 /**
	 * Cette méthode permet d'enregistrer les données saisies dans 
	 * la base de donnée
	 * @param m
	 * @param model
	 * @param maladiePlante
	 * @return
	 */
	@RequestMapping(value = "/donneravis", method = RequestMethod.POST)
	  
	  public String Maladie (@ModelAttribute("magic") Image m) {
		
		  m.setEtatTraitement(true);
		  imageRepository.save(m);
		  
	  return "confirmationexpert"; }
 
	
}