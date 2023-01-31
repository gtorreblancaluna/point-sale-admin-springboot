package com.mx.gtorreblanca.pointsaleadmin.controllers;

import com.mx.gtorreblanca.pointsaleadmin.constants.ApiUriConstant;
import com.mx.gtorreblanca.pointsaleadmin.security.JwtTokenUtil;
import com.mx.gtorreblanca.pointsaleadmin.dtos.LoginDto;
import com.mx.gtorreblanca.pointsaleadmin.services.user.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUriConstant.API+ApiUriConstant.AUTH)
@Log4j
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    private final CustomUserDetailsService customUserDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/signing")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsernameOrEmail(), loginDto.getPassword()));

            final UserDetails userDetails = customUserDetailsService
                    .loadUserByUsername(loginDto.getUsernameOrEmail());

            final String token = jwtTokenUtil.generateToken(userDetails);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(token);
        } catch (UsernameNotFoundException e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.noContent().build();
    }

}
