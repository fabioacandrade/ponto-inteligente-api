package com.fabioacandrade.APIRestPontoInteligente.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    public static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);

    /*
     * Gera um hash utilizando o BCrypt.
     *
     * @param senha
     * @return String
     */
    public static String gerarBCrypt(String senha) {
        if (senha == null) {
            return senha;
        }
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.encode(senha);
    }

    /*
     * Verifica se a senha é válida.
     *
     * @param senha
     * @param senhaEncoded
     * @return boolean
     */
    public static boolean senhaValida(String senha, String senhaEncoded) {
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.matches(senha, senhaEncoded);
    }

}
