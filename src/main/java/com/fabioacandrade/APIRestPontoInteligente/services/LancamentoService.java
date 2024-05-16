package com.fabioacandrade.APIRestPontoInteligente.services;

import com.fabioacandrade.APIRestPontoInteligente.entities.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface LancamentoService {
    Page<Lancamento>  buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest);

    Optional<Lancamento> buscarPorId(Long id);

    Lancamento persistir(Lancamento lancamento);

    void remover(Long id);
}
