/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.InfinitePetsServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import services.AccountServices;
import services.AddPetServices;

/**
 *
 * @author BTran
 */
public class LoginServlet extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                System.out.println("page loading..");
        //gets session and invalidates if you go to the login page specifically
        HttpSession session = request.getSession();
        session.invalidate();
        
        getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request,response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("processing the doPost..");
              
        String email = request.getParameter("username");
        String password = request.getParameter("password");
        boolean found = false;
        Account account = null;
        
        if((email!=null) && !email.equals("") && (password!=null) && (!password.equals(""))){
            try{
                AccountServices accServ = new AccountServices();
                account = accServ.checkCreds(email, password);
                if (account != null){
                    found = true;
                }
            }
            catch(Exception e){
                
            }
            finally{
                if(found){
                    System.out.println("account found");
                     HttpSession session = request.getSession();
                     session.setAttribute("user", account.getEmail());
                     response.sendRedirect("MyPets");
                }
                else
                {
                    System.out.println("Wrong creds");
                    request.setAttribute("message", "Wrong Username or Password");
                    getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request,response);
                }
            }     
        }
        else
            getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request,response);
        
    }
}
