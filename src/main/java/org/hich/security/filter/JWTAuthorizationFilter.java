package org.hich.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hich.security.constants.SecurityContants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends OncePerRequestFilter  {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-headers","Origin, Accept, Content-Type, Access-Control-Request-Methods, Access-Control-Request-Headers, Authorization");
		response.addHeader("Access-Control-Expose-headers","Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Authorization");
		response.addHeader("Access-Control-Allow-Methods", "HEAD, OPTIONS, GET, POST, PUT, PATCH");

		String jwtToken = request.getHeader(SecurityContants.HEADER_STRING);
		System.out.println(jwtToken);
		
		if (request.getMethod().equals("OPTIONS")) {
			
			response.setStatus(HttpServletResponse.SC_OK);
			
		} else {
			
			if (jwtToken == null || !jwtToken.startsWith(SecurityContants.TOKEN_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}
			
			Claims claims = Jwts.parser().setSigningKey(SecurityContants.SECRET)
							.parseClaimsJws(jwtToken.replace(SecurityContants.TOKEN_PREFIX, ""))
							.getBody();
			String username = claims.getSubject();
			@SuppressWarnings("unchecked")
			ArrayList<Map<String, String>> roles = (ArrayList<Map<String,String>>) claims.get("roles");
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			roles.forEach(role ->{
				authorities.add(new SimpleGrantedAuthority(role.get("authority")));
			});
			UsernamePasswordAuthenticationToken authenticatedUser = 
					new UsernamePasswordAuthenticationToken(username, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			filterChain.doFilter(request, response);
		}
		
	}
}
