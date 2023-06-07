package com.diagnoPlant.controllers;


import java.io.IOException;
import java.text.DecimalFormat;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;





@Controller
public class PredictionController {
	 
   
	@GetMapping(value ="/modell")
	public String telechargerImagee() {
		
		
		return"imagepredict";
	
	}
	
	@PostMapping(value="/model")
	public String info(Model model,@RequestParam("photo")MultipartFile file) throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException  {
		
	try {
			
			
			MultiLayerNetwork mode = KerasModelImport.importKerasSequentialModelAndWeights("../DiagnoPlant/src/main/resources/static/model4.h5",false);
		
		    
	        NativeImageLoader loader = new NativeImageLoader(256, 256, 3); 
	  
	        
	        INDArray image = loader.asMatrix(file.getInputStream());
	  
	       image.divi(255.0);
	       
	  
	       DecimalFormat f = new DecimalFormat();
		    f.setMaximumFractionDigits(6);
		    
		 
		  
	       model.addAttribute("cas1","Bacteriel_spot: " +f.format(mode.output(image).getDouble(0)));
	       model.addAttribute("cas2","Early_Blight: " +f.format(mode.output(image).getDouble(1)));
	       model.addAttribute("cas3","Late_Blight: " +f.format(mode.output(image).getDouble(2)));
	       model.addAttribute("cas4","Target_Spot: " +f.format(mode.output(image).getDouble(3)));
	       model.addAttribute("cas5","Healthy: " +f.format(mode.output(image).getDouble(4)));
	       
	       
		}
		catch(IOException | InvalidKerasConfigurationException | UnsupportedKerasConfigurationException error)
		{
		    error.printStackTrace();
		}
		
		
		return "predictmodel";
	
	}	
	
	
	  
	 
}
