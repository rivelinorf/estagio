package br.com.sonner.estagio.util;

import br.com.sonner.estagio.model.Usuario;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "authenticationFilter")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("chegou no filter");
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        Usuario usuario = (Usuario) request.getSession().getAttribute("USER");

        if (usuario == null) {
            ((HttpServletResponse)servletResponse).sendRedirect("/index.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
