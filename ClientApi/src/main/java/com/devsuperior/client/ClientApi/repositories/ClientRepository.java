package com.devsuperior.client.ClientApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.client.ClientApi.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{

}
