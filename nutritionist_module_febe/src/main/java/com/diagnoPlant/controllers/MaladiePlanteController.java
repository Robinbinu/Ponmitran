package com.diagnoPlant.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.diagnoPlant.models.MaladiePlante;
import com.diagnoPlant.repositories.MaladiePlanteRepository;


@Controller
public class MaladiePlanteController {
	@Autowired
	MaladiePlanteRepository maladiePRepo;
	
	@GetMapping(value="/listMaladiePlant")
	public String listMaladiePlant(Model model,
			@RequestParam(name="page",defaultValue="0")int page ,
			@RequestParam(name="size", defaultValue="5")int size,
			@RequestParam(name="Chercher", defaultValue="")String Chercher) {
		Page<MaladiePlante> pageMaladiePlante =maladiePRepo
				.findByNomMaladieContains(Chercher,PageRequest.of(page, size));
		model.addAttribute("pageMaladiePlante", pageMaladiePlante);
		model.addAttribute("currentPage",page);
		model.addAttribute("size",size);
		model.addAttribute("Chercher",Chercher);
		model.addAttribute("pages", new int[pageMaladiePlante.getTotalPages()]);
		return "listMaladie";
	}
	
	@GetMapping(value="/info")
	public String Info(Model model, @RequestParam(value="id")Long idMaladiePlante ) {
		MaladiePlante mpl = maladiePRepo.getOne(idMaladiePlante);
		model.addAttribute("infoMaladieP", mpl);
		return "fichemaladieplante";
	}

}