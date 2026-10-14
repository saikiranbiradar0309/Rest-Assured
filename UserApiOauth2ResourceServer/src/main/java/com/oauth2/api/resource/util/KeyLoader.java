package com.oauth2.api.resource.util;

import java.io.InputStreamReader;
import java.io.Reader;
import java.security.PublicKey;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class KeyLoader {

    private final PublicKey publicKey;

    public KeyLoader() throws Exception {

        Reader reader = new InputStreamReader(
                new ClassPathResource("keys/public.pem")
                        .getInputStream());

        PEMParser parser = new PEMParser(reader);

        SubjectPublicKeyInfo keyInfo =
                (SubjectPublicKeyInfo) parser.readObject();

        parser.close();

        this.publicKey = new JcaPEMKeyConverter()
                .getPublicKey(keyInfo);
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}