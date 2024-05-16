package com.fabioacandrade.APIRestPontoInteligente.repositories;

import com.fabioacandrade.APIRestPontoInteligente.entities.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface EmpresaRepository extends JpaRepository<Empresa,Long> {
    Empresa findByCnpj(String cnpj);
}
