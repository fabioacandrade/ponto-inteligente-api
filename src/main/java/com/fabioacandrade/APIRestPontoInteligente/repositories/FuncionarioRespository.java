package com.fabioacandrade.APIRestPontoInteligente.repositories;

import com.fabioacandrade.APIRestPontoInteligente.entities.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface FuncionarioRespository extends JpaRepository<Funcionario,Long> {
    Funcionario findByCpf(String cpf);
    Funcionario findByEmail(String email);
    Funcionario findByCpfOrEmail(String cpf,String email);
}
