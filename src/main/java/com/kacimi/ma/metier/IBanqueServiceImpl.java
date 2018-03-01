package com.kacimi.ma.metier;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Date;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kacimi.ma.dao.ClientRepository;
import com.kacimi.ma.dao.CompteRepository;
import com.kacimi.ma.dao.OperationRepository;
import com.kacimi.ma.entities.Compte;
import com.kacimi.ma.entities.CompteCourant;
import com.kacimi.ma.entities.Operation;
import com.kacimi.ma.entities.Retrait;
import com.kacimi.ma.entities.Versement;
@Service
@Transactional 
public class IBanqueServiceImpl implements IBanqueService {
	
   @Autowired
	private CompteRepository compterepository;
   @Autowired
	private OperationRepository operationrepository;
   @Autowired
	private ClientRepository clientrepository;

	
	public Compte consulterCompte(String CodeCpte) {
      Compte cpte=compterepository.findOne(CodeCpte);
      if (cpte==null) throw new RuntimeException("Compte introuvable !");
      return cpte;
	}

	@Override
	public void verser(String CodeCpte, double Montant) {
		Compte cpte=consulterCompte(CodeCpte);
         Versement v=new Versement(new Date(), Montant, cpte);
         operationrepository.save(v);
         cpte.setSolde(cpte.getSolde()+Montant);
         compterepository.save(cpte);
	}

	@Override
	public void retirer(String CodeCpte, double Montant) {
		Compte cpte=consulterCompte(CodeCpte);
		double facilitesCaisse=0;
		
		if(cpte instanceof CompteCourant)
			facilitesCaisse=((CompteCourant) cpte).getDecouvert();
		if(cpte.getSolde()+facilitesCaisse<Montant) throw new RuntimeException("Solde insuffisant");
			
        Retrait r=new Retrait(new Date(), Montant, cpte);
        operationrepository.save(r);
        cpte.setSolde(cpte.getSolde()-Montant);
        compterepository.save(cpte);		
	}

	@Override
	public void virement(String CodeCpte1, String CodeCpte2, double Montant) {
		retirer(CodeCpte1, Montant);
		verser(CodeCpte2, Montant);
	}

	@Override
	public Page<Operation> listOperations(String CodeCpte, int page, int size) {
		return operationrepository.listOperations(CodeCpte,
				new PageRequest(page, size));
	}

}
