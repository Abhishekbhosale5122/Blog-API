package com.Blog.security;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {

	private final String secretKey = "your_secret_key"; // Replace with a strong secret key
	private final long tokenValidity = 5 * 60 * 60 * 1000; // Token validity (e.g., 5 hours)

	// 1. Generate Token
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + tokenValidity))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	// 2. Get Username from Token
	public String getUserFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// 3. Get Expiration Date from Token
	public Date getExpiryDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	// 4. Get Specific Claim from Token
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// 5. Get All Claims from Token
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	// 6. Check if Token is Expired
	public boolean isTokenExpired(String token) {
		final Date expiration = getExpiryDateFromToken(token);
		return expiration.before(new Date());
	}

	// 7. Validate Token
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUserFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
