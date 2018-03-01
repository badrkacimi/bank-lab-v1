package com.kacimi.ma.metier;

import org.springframework.data.domain.Page;

import com.kacimi.ma.entities.Compte;
import com.kacimi.ma.entities.Operation;

public interface IBanqueService {
	public Compte consulterCompte(String CodeCpte);
	public void verser(String CodeCpte,double Montant);
	public void retirer(String CodeCpte,double Montant);
	public void virement(String CodeCpte1,String CodeCpte2, double Montant);
	public Page<Operation> listOperations(String CodeCpte,int page,int size);


	
	

}
