package com.golearn.security;

public class JwtProperties {

	private String header = "Authorization";

	private String tokenPrefix = "Bearer ";

	private String secret = "custom";

	private int expirationTime = 300000;

	public String getHeader() {
		return header;
	}

	public String getTokenPrefix() {
		return tokenPrefix;
	}

	public String getSecret() {
		return secret;
	}

	public int getExpirationTime() {
		return expirationTime;
	}

}
