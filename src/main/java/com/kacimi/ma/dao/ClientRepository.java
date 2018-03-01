package com.kacimi.ma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kacimi.ma.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
