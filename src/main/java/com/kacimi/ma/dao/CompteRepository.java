package com.kacimi.ma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kacimi.ma.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String> 

{

}
