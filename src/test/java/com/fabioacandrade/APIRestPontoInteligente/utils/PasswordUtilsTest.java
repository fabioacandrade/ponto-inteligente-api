package com.fabioacandrade.APIRestPontoInteligente.utils;

import org.junit.jupiter.api.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {
    private static final String SENHA = "123456";
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    public void testSenhaNula () throws Exception {
        Assertions.assertNull(PasswordUtils.gerarBCrypt(null));
    }

    @Test
    public void testSenhaInvalida () throws Exception {
        String hash = PasswordUtils.gerarBCrypt(SENHA);
        Assertions.assertTrue(encoder.matches(SENHA, hash));
    }
}
