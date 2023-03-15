/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import services.AccountServices;

/**
 *
 * @author Chris
 */
public class UserFilter implements Filter {
    
   
   
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
        
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            HttpSession session = httpRequest.getSession();
            
            String email = (String)session.getAttribute("email");
            String password = (String)session.getAttribute("password");
                       
        try {     
            if(!loginCheck(email, password)){       
                httpResponse.sendRedirect("Login");
                return;
            }          
            chain.doFilter(request, response);          
        } catch (Exception ex) {
            httpResponse.sendRedirect("Login");
            Logger.getLogger(UserFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    /**
     * Checks the if the login is valid
     * @param email
     * @param password
     * @return 
     */
    private boolean loginCheck(String email, String password){
        try {
            if(email==null||password==null)
                return false;
            
            AccountServices as = new AccountServices();
            Account account = new Account();
            
            account = as.getAccount(email);
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger(UserFilter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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
    }
}
