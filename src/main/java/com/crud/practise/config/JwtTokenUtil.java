package com.crud.practise.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.crud.practise.model.Login;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	
	private static final long serialVersionUID = 154545L;

	public static final long JWT_TOKEN_VALIDITY = 5*60*60; // 5 minutes

	// this is to fetch the value from application.prop
	@Value("${jwt.secret}")
	private String secret;
	
//	private String SECRET = "y/B?E(H+MbQeShVmYq3t6w9z$C&F)J@NcRfUjWnZr4u7x!A%D*G-KaPdSgVkYp2s";
	
	//retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	//retrieve issued date from jwt token
	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	  //for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	// to check if the token is expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}


		//generate token for user with my project
		public String generateToken(Login login) {
			Map<String, Object> claims = new HashMap<>();
			return doGenerateToken(claims, login.getUsername());
		}

		//while creating the token -
		//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
		//2. Sign the JWT using the HS512 algorithm and secret key.
		//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
		//   compaction of the JWT to a URL-safe string 
		
		private String doGenerateToken(Map<String, Object> claims, String subject) {
			return Jwts.builder()
					.setClaims(claims)
					.setSubject(subject)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY *3000))
//					//this is the default encoder
					.signWith(SignatureAlgorithm.HS512, secret).compact();
//					 this is for customized key
//					.signWith( SignatureAlgorithm.HS512, getSignKey()).compact();
		}
		
//		private Key getSignKey() {
//			byte[] keyBytes = Decoders.BASE64.decode(secret);
////			byte[] keyBytes = Base64.getDecoder().decode(secret);
//			return Keys.hmacShaKeyFor(keyBytes);
//		}
		
		// to validate the token with project
		public Boolean validateToken(String token, Login login) {
			final String username = getUsernameFromToken(token);
			return (username.equals(login.getUsername()) && !isTokenExpired(token));
		}
}
