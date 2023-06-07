package com.diagnoPlant.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Image implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	private String image;
	private Boolean etatTraitement;
	private String infosCompl ;
	@ManyToOne
	private MaladiePlante maladiePlante;

	@ManyToOne
	private User user;
    
	public Image() {
		super();
		// TODO Auto-generated constructor stub
		this.etatTraitement =false;
	}
    
	public Image(Long id, String image, MaladiePlante maladiePlante) {
		super();
		this.id = id;
		this.image = image;
		this.maladiePlante = maladiePlante;
	}

	public Image(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	public Boolean getEtatTraitement() {
		return etatTraitement;
	}

	public void setEtatTraitement(Boolean etatTraitement) {
		this.etatTraitement = etatTraitement;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
    
	public String getInfosCompl() {
		return infosCompl;
	}

	public void setInfosCompl(String infosCompl) {
		this.infosCompl = infosCompl;
	}

	public MaladiePlante getMaladiePlante() {
		return maladiePlante;
	}

	public void setMaladiePlante(MaladiePlante maladiePlante) {
		this.maladiePlante = maladiePlante;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
		

}
