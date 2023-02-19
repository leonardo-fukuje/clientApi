package com.devsuperior.client.ClientApi.services.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.client.ClientApi.dto.ClientDTO;
import com.devsuperior.client.ClientApi.entities.ClientEntity;
import com.devsuperior.client.ClientApi.repositories.ClientRepository;
import com.devsuperior.client.ClientApi.services.ClientService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(Pageable pageable) {
		Page<ClientEntity> list = repository.findAll(pageable);
		return list.map(x -> new ClientDTO(x));
	}

	@Override
	public ClientDTO findById(Long id) {
		Optional<ClientEntity> obj = repository.findById(id);
		ClientEntity entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new ClientDTO(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public ClientDTO insert(ClientDTO dto) {
		ClientEntity entity =  new ClientEntity();
		entity.setName(dto.getName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		entity.setIncome(dto.getIncome());
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	@Override
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			ClientEntity entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity.setBirthDate(dto.getBirthDate());
			entity.setChildren(dto.getChildren());
			entity.setIncome(dto.getIncome());
			entity = repository.save(entity);
			return new ClientDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new EntityNotFoundException("id not found");
		}
	}

	@Override
	public void delete(Long id) {
		try {
		repository.deleteById(id);
	} catch(EmptyResultDataAccessException e) {
		throw new EmptyResultDataAccessException("id not found", 1);
	} catch(DataIntegrityViolationException e) {
		throw new DataIntegrityViolationException("Integrity violation");
		
	}

}}
