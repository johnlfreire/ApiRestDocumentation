package com.br.sfb.registrocivil.api.v1.service;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.sfb.registrocivil.api.v1.exceptionhandler.ResourceNotFoundException;
import com.br.sfb.registrocivil.api.v1.model.ClienteModel;
import com.br.sfb.registrocivil.domain.entities.Cliente;
import com.br.sfb.registrocivil.domain.repositories.ClienteRepository;

@Service
public class ClienteApiService {

	@Autowired
	private ClienteRepository clienteRepository;

	
	
	
	public ClienteModel cadastrarCliente(ClienteModel clienteModelo) {
		Cliente cliente = clienteRepository.save(Cliente.toModel(clienteModelo));
		return ClienteModel.toModel(cliente);
	}

	public ClienteModel editarCliente(ClienteModel clienteModelo, Long clienteID) {

		Cliente clienteOld = Cliente.toModel(clienteModelo);

		Cliente clienteSave = clienteRepository.findById(clienteID).orElseThrow(() -> new ResourceNotFoundException(
				"\"Não Foi localizado nenhum registro de cliente com id informado "));
		// Copia as propriedades de um Objeto para outro Objeto com exceção do codigo.
		BeanUtils.copyProperties(clienteOld, clienteSave, "codigo");

		return ClienteModel.toModel(clienteRepository.save(clienteSave));

	}

	public void excluirCliente(Long clienteID) {
		try {
			clienteRepository.deleteById(clienteID);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Não Foi localizado nenhum registro de cliente com id informado");

		} catch (DataIntegrityViolationException e) {
			throw new ResourceNotFoundException("Não Foi localizado nenhum registro de cliente com id informado");
		}
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public ClienteModel findById(Long clienteID) {
		Cliente cliente = clienteRepository.findById(clienteID).orElseThrow(() -> new ResourceNotFoundException(
				"\"Não Foi localizado nenhum registro de cliente com id informado "));
		return ClienteModel.toModel(cliente);
	}

}
