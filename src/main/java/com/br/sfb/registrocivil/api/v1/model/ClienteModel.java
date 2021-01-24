package com.br.sfb.registrocivil.api.v1.model;

import java.io.Serializable;
import java.sql.Date;

import org.modelmapper.ModelMapper;

import com.br.sfb.registrocivil.domain.entities.Cliente;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteModel implements Serializable{



	private static final long serialVersionUID = 1L;
	@Getter	@Setter 
	private long codigo;
	@Getter	@Setter 
	@ApiModelProperty(example = "João")
	private String nome; 
	@Getter	@Setter 
	@ApiModelProperty(example = "000-000-000-00")
	private String cpf;
	@Getter	@Setter 
	@ApiModelProperty(example = "000000-000")
	private String rg;
	@Getter	@Setter
	@ApiModelProperty(example = "(00) 0000-0000")
	private String telefone1;
	@Getter	@Setter
	@ApiModelProperty(example = "(00) 00000-0000")
	private String telefone2;
	@Getter	@Setter
	@ApiModelProperty(example = "1989-10-10")
	private Date dataNascimento; 
	@Getter	@Setter
	@ApiModelProperty(example = "Rua São Geraldo")
	private String rua; 
	@Getter	@Setter
	@ApiModelProperty(example = "00000-000")
	private String cep; 
	@Getter	@Setter
	@ApiModelProperty(example = "Bairro São João")
	private String bairro;
	@Getter	@Setter 
	@ApiModelProperty(example = "SP")
	private String estado;
	@Getter	@Setter 
	@ApiModelProperty(example = "São Paulo")
	private String cidade;
	@Getter	@Setter 
	@ApiModelProperty(example = "Numero")
	private String numero;
	@Getter	@Setter 
	@ApiModelProperty(example = "Casa A")
	private String complemento;
	
	

	public static ClienteModel toModel(Cliente cliente) {
		return new ModelMapper().map(cliente, ClienteModel.class);	
		
	}
	
}