package com.dasial.springTodoList.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dasial.springTodoList.user.IUserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

  @Autowired
  IUserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    if (request.getServletPath().startsWith("/tasks/")) {
      var headerAuthorization = request.getHeader("authorization");

      var authorizationEncoded = headerAuthorization.substring("Basic".length()).trim();

      byte[] authDecode = Base64.getDecoder().decode(authorizationEncoded);

      var authString = new String(authDecode);

      String[] credentials = authString.split(":");

      var username = credentials[0];
      var password = credentials[1];

      var user = this.userRepository.findByUsername(username);
      if (user == null) {
        response.sendError(401);
      } else {

        var result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if (result.verified) {

          request.setAttribute("userId", user.getId());

          filterChain.doFilter(request, response);
        } else {
          response.sendError(401);
        }
      }

    } else {
      filterChain.doFilter(request, response);
    }

  }

}
