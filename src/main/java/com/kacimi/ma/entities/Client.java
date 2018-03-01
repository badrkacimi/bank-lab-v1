package com.kacimi.ma.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;

@Entity
public class Client implements Serializable {
	@Id @GeneratedValue
	private long code;
	private String nom;
	private String email;
	@OneToMany(mappedBy="client",fetch=FetchType.LAZY)
    private Collection<Compte> comptes;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public Client() {
		super();
	}
	public Client(String nom, String email) {
		super();
		this.nom = nom;
		this.email = email;
	}
	 
	

}
