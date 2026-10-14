package com.oauth2.api.util;

import java.io.InputStreamReader;
import java.io.Reader;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class KeyLoader {

	private final PrivateKey privateKey;

	private final PublicKey publicKey;

	public KeyLoader() throws Exception {

		this.privateKey = loadPrivateKey();

		this.publicKey = loadPublicKey();
	}

	public PrivateKey getPrivateKey() {

		return privateKey;
	}

	public PublicKey getPublicKey() {

		return publicKey;
	}

	private PrivateKey loadPrivateKey() throws Exception {

		Reader reader = new InputStreamReader(new ClassPathResource("keys/private.pem").getInputStream());

		PEMParser parser = new PEMParser(reader);

		Object object = parser.readObject();

		parser.close();

		JcaPEMKeyConverter converter = new JcaPEMKeyConverter();

		if (object instanceof org.bouncycastle.asn1.pkcs.PrivateKeyInfo privateKeyInfo) {

			return converter.getPrivateKey(privateKeyInfo);

		}

		if (object instanceof PEMKeyPair pemKeyPair) {

			return converter.getKeyPair(pemKeyPair).getPrivate();

		}

		throw new IllegalArgumentException("Unsupported private key format");
	}

	private PublicKey loadPublicKey() throws Exception {

		Reader reader = new InputStreamReader(

				new ClassPathResource("keys/public.pem").getInputStream());

		PEMParser parser = new PEMParser(reader);

		Object object = parser.readObject();

		parser.close();

		return new JcaPEMKeyConverter()

				.getPublicKey(

						(org.bouncycastle.asn1.x509.SubjectPublicKeyInfo) object);
	}
}