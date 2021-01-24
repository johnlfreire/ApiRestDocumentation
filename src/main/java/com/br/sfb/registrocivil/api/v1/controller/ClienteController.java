package com.br.sfb.registrocivil.api.v1.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.br.sfb.registrocivil.api.v1.model.ClienteModel;
import com.br.sfb.registrocivil.api.v1.service.ClienteApiService;
import com.br.sfb.registrocivil.domain.entities.Cliente;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "Cliente")
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteApiService clienteService;

	@ApiOperation(value = "Listar Clientes", nickname = "listarCliente", notes = "Retorna todos os clientes cadastrados", response = ClienteModel.class, tags = {
			"Cliente", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping()
	public List<ClienteModel> listarCliente() {
		return clienteService.findAll().stream().map(ClienteModel::toModel).collect(Collectors.toList());

	}

	@ApiOperation(value = "Cadastra Cliente", nickname = "cadastraCliente", notes = "Cadastra um novo cliente", response = ClienteModel.class, tags = {
			"Cliente", })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cadastro efetuado com sucesso", response = Cliente.class),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public ClienteModel cadastrarCliente(@Valid @RequestBody ClienteModel clienteModel) {
		ClienteModel cliente = clienteService.cadastrarCliente(clienteModel);
		log.info("Cliente cadastrado com sucesso");
		return cliente;
	}

	@ApiOperation(value = "Atualizar Cliente", nickname = "editarCliente", notes = "Efetua a atualização das informações do cliente", response = ClienteModel.class, tags = {
			"Cliente", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PutMapping("/{clienteID}")
	public ClienteModel editarCliente(@PathVariable Long clienteID, @Valid @RequestBody ClienteModel clienteModelo) {
		return clienteService.editarCliente(clienteModelo, clienteID);

	}

	@ApiOperation(value = "Excluir Cliente", nickname = "deletarCliente", notes = "Exclui o cliente indicado", response = ClienteModel.class, tags = {
			"Cliente", })
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Excluido com sucesso! "),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{clienteID}")
	public void deletarCliente(@PathVariable Long clienteID) {
		System.out.println(clienteID);
		clienteService.excluirCliente(clienteID);

	}

}
