/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sv.edu.udb.entities.UsuarioEntity;

/**
 *
 * @author fuego
 */
@WebFilter(filterName = "SessionFilter", urlPatterns = {"/*"})
public class SessionFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public SessionFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        /*chain.doFilter(request, response);
        return;*/
        HttpServletRequest peticion = (HttpServletRequest) request;
        HttpServletResponse respuesta = (HttpServletResponse) response;
        HttpSession session = peticion.getSession(false);
        String loginURI = peticion.getContextPath() + "/";

        String debug = peticion.getRequestURI();
        boolean loggedIn = (session != null && session.getAttribute("Session") != null);
        boolean IsLoginRequest = (peticion.getRequestURI().equals(loginURI) || peticion.getRequestURI().equals(loginURI + "Login.xhtml"));

        if (peticion.getRequestURI().contains("javax.faces.resource")) {
            chain.doFilter(request, response);
            return;
        }
        if (!loggedIn && IsLoginRequest) {
            chain.doFilter(request, response);
            return;
        }
        if (!loggedIn && !IsLoginRequest) {
            //JSFUtil.addErrorMessage("Debe iniciar sesion");
            respuesta.sendRedirect(loginURI);
            return;
        }
        System.out.println("Esta logueado");
        if (loggedIn) {
            UsuarioEntity u = (UsuarioEntity) session.getAttribute("Session");
            String path = peticion.getRequestURI().substring(peticion.getContextPath().length());

            System.out.println("UDBLOG: El usuario tiene id=" + u.getTipoUsuario().getId());

            if (path.startsWith("/Administrador/") && !(u.getTipoUsuario().getId() == 1)) {
                //JSFUtil.addErrorMessage("Debe iniciar sesion");
                respuesta.sendRedirect(loginURI);
            } else if (path.startsWith("/Medicos/") && !(u.getTipoUsuario().getId() == 2 )) {
                respuesta.sendRedirect(loginURI);

            } else if (path.startsWith("/Paciente/") && !(u.getTipoUsuario().getId() == 3)) {
                respuesta.sendRedirect(loginURI);

            } else {
                //JsfUtil.setErrorMessage(null, "Debe iniciar sesion");
                chain.doFilter(request, response);
                return;
            }
            //JsfUtil.setErrorMessage(null, "Debe iniciar sesion");
            chain.doFilter(request, response);
            return;
        }

        chain.doFilter(request, response);
        return;
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("SessionFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SessionFilter()");
        }
        StringBuffer sb = new StringBuffer("SessionFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
