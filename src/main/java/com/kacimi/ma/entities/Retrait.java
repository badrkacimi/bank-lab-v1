package com.kacimi.ma.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(Date dateOpertion, double montant, Compte compte) {
		super(dateOpertion, montant, compte);
		// TODO Auto-generated constructor stub
	}

}
