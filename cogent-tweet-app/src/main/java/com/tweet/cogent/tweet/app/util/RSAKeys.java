package com.tweet.cogent.tweet.app.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Data
@AllArgsConstructor
@Component
public class RSAKeys {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RSAKeys() {
        KeyPair pair = KeyGeneratorUtil.generateRsaKey();
        this.publicKey = (RSAPublicKey) pair.getPublic();
        this.privateKey = (RSAPrivateKey) pair.getPrivate();
    }
}
