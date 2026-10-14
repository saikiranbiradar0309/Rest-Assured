package com.oauth2.api.util;

import java.io.File;
import java.io.FileWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

public class RsaKeyGenerator {

    public static void main(String[] args) throws Exception {

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);

        KeyPair pair = generator.generateKeyPair();

        File dir = new File("src/main/resources/keys");
        dir.mkdirs();

        writePem(
                "src/main/resources/keys/private.pem",
                "PRIVATE KEY",
                pair.getPrivate().getEncoded());

        writePem(
                "src/main/resources/keys/public.pem",
                "PUBLIC KEY",
                pair.getPublic().getEncoded());

        System.out.println("RSA Keys Generated Successfully.");
    }

    private static void writePem(
            String file,
            String type,
            byte[] encoded) throws Exception {

        String pem =
                "-----BEGIN " + type + "-----\n"
                + Base64.getMimeEncoder(64, "\n".getBytes())
                        .encodeToString(encoded)
                + "\n-----END " + type + "-----";

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(pem);
        }
    }
}