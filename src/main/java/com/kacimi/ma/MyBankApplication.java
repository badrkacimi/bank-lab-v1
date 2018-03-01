package com.kacimi.ma;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kacimi.ma.dao.ClientRepository;
import com.kacimi.ma.dao.CompteRepository;
import com.kacimi.ma.dao.OperationRepository;
import com.kacimi.ma.entities.Client;
import com.kacimi.ma.entities.Compte;
import com.kacimi.ma.entities.CompteCourant;
import com.kacimi.ma.entities.CompteEpargne;
import com.kacimi.ma.entities.Retrait;
import com.kacimi.ma.entities.Versement;
import com.kacimi.ma.metier.IBanqueService;

@SpringBootApplication
public class MyBankApplication implements CommandLineRunner {
	@Autowired
    ClientRepository clientrepository;
	@Autowired
	CompteRepository compterepository;
	@Autowired
	OperationRepository operationrepository;
	@Autowired
	IBanqueService ibanqueService;
 
	public static void main(String[] args) {
		SpringApplication.run(MyBankApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Client c1=clientrepository.save(new Client("BADR" , " badrvkacimi@gmail.com "));
		Client c2=clientrepository.save(new Client("SAAD" , " saadvkacimi@gmail.com "));
		
		Compte ctp1=compterepository.save(new CompteCourant("c1", new Date(), 9000, c1, 2000));
		Compte ctp2=compterepository.save(new CompteEpargne("c2", new Date(), 6000, c2, 5.5));
		
		operationrepository.save(new Versement(new Date(), 9000, ctp1));
		operationrepository.save(new Versement(new Date(), 6000, ctp1));
		operationrepository.save(new Versement(new Date(), 2000, ctp1));
		operationrepository.save(new Retrait(new Date(), 9000, ctp1));
 
		operationrepository.save(new Versement(new Date(), 2300, ctp2));
		operationrepository.save(new Versement(new Date(), 6000, ctp2));
		operationrepository.save(new Versement(new Date(), 2300, ctp2));
		operationrepository.save(new Retrait(new Date(), 3000, ctp2));
         
		ibanqueService.verser("c1", 10000);
		ibanqueService.verser("c2", 700);
		ibanqueService.retirer("c2", 6000);

		
		

		


		
	}
}
