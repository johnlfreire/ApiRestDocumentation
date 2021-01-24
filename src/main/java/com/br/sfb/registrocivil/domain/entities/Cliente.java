package com.br.sfb.registrocivil.domain.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;

import com.br.sfb.registrocivil.api.v1.model.ClienteModel;

import lombok.Getter;
import lombok.Setter;

@Entity
@Validated
@Table(name="cliente")
public class Cliente implements Serializable {


	private static final long serialVersionUID = 1L;	
	
	@Getter	@Setter @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	@Getter	@Setter @Column()
	private String nome; 
	@Getter	@Setter @Column(unique = true)
	private String cpf;
	@Getter	@Setter @Column
	private String rg;
	@Getter	@Setter @Column
	private String telefone1;
	@Getter	@Setter @Column
	private String telefone2;
	@Getter	@Setter @Column
	private Date dataNascimento; 
	@Getter	@Setter @Column
	private String rua; 
	@Getter	@Setter @Column
	private String cep; 
	@Getter	@Setter @Column
	private String bairro;
	@Getter	@Setter @Column
	private String estado;
	@Getter	@Setter @Column
	private String cidade;
	@Getter	@Setter @Column
	private String numero;
	@Getter	@Setter @Column
	private String complemento;
	
	public static Cliente toModel(ClienteModel clienteModelo) {
		return new ModelMapper().map(clienteModelo, Cliente.class);		
		
	}
}
