package com.fabioacandrade.APIRestPontoInteligente.services;

import com.fabioacandrade.APIRestPontoInteligente.entities.Empresa;

import java.util.Optional;

public interface EmpresaService {
    /*
        Retorna uma empresa dado um cnpj
     */
    Optional<Empresa> buscarPorCnpj(String cnpj);
    /*
        Cadastra nova empresa na base de dados
     */
    Empresa persistir(Empresa empresa);
}
