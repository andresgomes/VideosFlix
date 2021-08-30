package br.com.videos.config.secutiry;

import br.com.videos.modelo.User;
import br.com.videos.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationByTokenFilter extends OncePerRequestFilter {

    private TokenServices tokenServices;

    private UserRepository userRepository;

    public AuthenticationByTokenFilter(TokenServices tokenServices, UserRepository userRepository) {
        this.tokenServices = tokenServices;
        this.userRepository= userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recoveryToken(request);
        boolean valid = tokenServices.isTokenValid(token);
        if (valid){
            authenticateClient(token);

        }

        filterChain.doFilter(request,response);
    }

    private void authenticateClient(String token) {
        Long idUser = tokenServices.getIdUser(token);
        User user = userRepository.findById(idUser).get();
        UsernamePasswordAuthenticationToken authetication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authetication);
    }

    private String recoveryToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7, token.length());
    }
}
