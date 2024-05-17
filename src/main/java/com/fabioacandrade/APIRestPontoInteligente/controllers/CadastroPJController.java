package com.fabioacandrade.APIRestPontoInteligente.controllers;


import com.fabioacandrade.APIRestPontoInteligente.dtos.CadastroPJDto;
import com.fabioacandrade.APIRestPontoInteligente.entities.Empresa;
import com.fabioacandrade.APIRestPontoInteligente.entities.Funcionario;
import com.fabioacandrade.APIRestPontoInteligente.enums.PerfilEnum;
import com.fabioacandrade.APIRestPontoInteligente.response.Response;
import com.fabioacandrade.APIRestPontoInteligente.services.EmpresaService;
import com.fabioacandrade.APIRestPontoInteligente.services.FuncionarioService;
import com.fabioacandrade.APIRestPontoInteligente.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api/cadastrar-pj")
@CrossOrigin(origins = "*")
public class CadastroPJController {
    private static final Logger log = LoggerFactory.getLogger(CadastroPJController.class);

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private EmpresaService empresaService;

    public CadastroPJController() {}

    @PostMapping
    public ResponseEntity<Response<CadastroPJDto>> cadastrar(@Valid @RequestBody CadastroPJDto cadastroPJDto,
                                                             BindingResult result) throws NoSuchAlgorithmException {
        log.info("Cadastrando PJ: {}", cadastroPJDto);
        Response<CadastroPJDto> response = new Response<CadastroPJDto>();

        validarDadosExistentes(cadastroPJDto,result);
        Empresa empresa = this.converterDtoParaEmpresa(cadastroPJDto);
        Funcionario funcionario = this.converterDtoParaFuncionario(cadastroPJDto,result);

        if(result.hasErrors()) {
            log.error("Erro validando dados de cadastro PJ:{}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        empresa = this.empresaService.persistir(empresa);
        funcionario.setEmpresa(empresa);
        funcionario = this.funcionarioService.persistir(funcionario);

        response.setData(this.converterCadastroPJDto(funcionario));
        return ResponseEntity.ok(response);
    }

    private void validarDadosExistentes(CadastroPJDto cadastroPJDto, BindingResult result) {
        this.empresaService.buscarPorCnpj(cadastroPJDto.getCnpj())
                .ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente.")));

        this.funcionarioService.buscarPorCpf(cadastroPJDto.getCpf())
                .ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));

        this.funcionarioService.buscarPorEmail(cadastroPJDto.getEmail())
                .ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));
    }

    private Empresa converterDtoParaEmpresa(CadastroPJDto cadastroPJDto) {
        Empresa empresa = new Empresa();
        empresa.setCnpj(cadastroPJDto.getCnpj());
        empresa.setRazaoSocial(cadastroPJDto.getRazaoSocial());

        return empresa;
    }

    private Funcionario converterDtoParaFuncionario(CadastroPJDto cadastroPJDto, BindingResult result)
            throws NoSuchAlgorithmException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(cadastroPJDto.getNome());
        funcionario.setEmail(cadastroPJDto.getEmail());
        funcionario.setCpf(cadastroPJDto.getCpf());
        funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
        funcionario.setSenha(PasswordUtils.gerarBCrypt(cadastroPJDto.getSenha()));

        return funcionario;
    }

    private CadastroPJDto converterCadastroPJDto(Funcionario funcionario) {
        CadastroPJDto cadastroPJDto = new CadastroPJDto();
        cadastroPJDto.setId(funcionario.getId());
        cadastroPJDto.setNome(funcionario.getNome());
        cadastroPJDto.setEmail(funcionario.getEmail());
        cadastroPJDto.setCpf(funcionario.getCpf());
        cadastroPJDto.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial());
        cadastroPJDto.setCnpj(funcionario.getEmpresa().getCnpj());

        return cadastroPJDto;
    }


}
