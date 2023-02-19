package com.devsuperior.client.ClientApi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.client.ClientApi.dto.ClientDTO;

@Service
public interface ClientService {
	
	public Page<ClientDTO> findAllPaged(Pageable pageable);

	public ClientDTO findById(Long id);
	
	public ClientDTO insert(ClientDTO dto);

	public ClientDTO update(Long id, ClientDTO dto);

	public void delete(Long id);
	
}
