package com.fabioacandrade.APIRestPontoInteligente.impl;

import com.fabioacandrade.APIRestPontoInteligente.entities.Funcionario;
import com.fabioacandrade.APIRestPontoInteligente.repositories.FuncionarioRespository;
import com.fabioacandrade.APIRestPontoInteligente.services.FuncionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);

    @Autowired
    private FuncionarioRespository funcionarioRespository;

    public Funcionario persistir(Funcionario funcionario) {
        log.info("Persistindo funcionario: {}", funcionario);
        return this.funcionarioRespository.save(funcionario);
    }

    public Optional<Funcionario> buscarPorCpf(String cpf) {
        log.info("Buscando funcionario por CPF: {}", cpf);
        return Optional.ofNullable(this.funcionarioRespository.findByCpf(cpf));
    }

    public Optional<Funcionario> buscarPorEmail(String email) {
        log.info("Buscando funcionario por email: {}", email);
        return Optional.ofNullable(this.funcionarioRespository.findByEmail(email));
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        log.info("Buscando funcionario por ID: {}", id);
        return this.funcionarioRespository.findById(id);
    }
}
