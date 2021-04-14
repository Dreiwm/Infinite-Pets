/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.InfinitePetsServlets.promotions;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Account;
import models.Discount;
import models.Employee;
import models.Promotion;
import services.AccountServices;
import services.ValidationServices;

/**
 *
 * @author Riley
 */
public class NewPromotionServlet extends HttpServlet {
    private final String path = "/WEB-INF/promotions/NewPromotion.jsp";
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
          // This page is only for admin. So, first we need to check if it is in session,
        // if yes, check is this account an admin?
        // If yes, serve the page. Otherwise, redirect to login page.
        String email = (String) request.getSession().getAttribute("email");
        
        AccountServices acs = new AccountServices();
        Account acc = null;
        Employee emp = null;
        try {
            acc = acs.getAccount(email);
        } catch (Exception ex) {
            Logger.getLogger(PromotionsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (acc == null) {
            System.out.println("account null, going to login page...");
            response.sendRedirect(response.encodeRedirectURL("Login"));  
            return;
        } else {
            // check if admin
            if (acc.getIsEmployee()) {
                System.out.println("Account is not null, checking is admin?");
                System.out.println("name: " + acc.getFirstName() + "userID: " + acc.getUserId());
                try {
                    emp = acs.getEmployeeAccount(email);
                } catch (Exception ex) {
                    Logger.getLogger(PromotionsServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                // now check if is admin
                if (emp != null &&  emp.getIsAdmin()) {
                    System.out.println("Eomployee is an admin");

                } else {
                    System.out.println("Employee is not an admin");
                    response.sendRedirect(response.encodeRedirectURL("Login"));    
                    return; // to get out of here
                }
                
            } else {
                System.out.println("emp obj is null");
                response.sendRedirect(response.encodeRedirectURL("Login"));
                return;
            }
        }
        
             
        // if tests were passed, this section will be read.
        getServletContext().getRequestDispatcher(path).forward(request, response);
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
        Promotion pr = createPromotionFromParameters(request, response);
    }
    
    /**
     * Initializes the Promotion obj gleaned from parameters upon submission.
     * @param request
     * @param response
     * @return Promotion object.
     */
    private Promotion createPromotionFromParameters(HttpServletRequest request, HttpServletResponse response) {
        
        Promotion pr = new Promotion();
        
        String prName = request.getParameter("promotionName");
        String prDesc = request.getParameter("promotionDesc");
        String discPercent = request.getParameter(("discountPercent"));
        String discType = request.getParameter("discType");
        
        // Dates 
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        System.out.println("start date: " + startDate);
        System.out.println("end date: " + endDate);
        
        // Service ID
        String serviceId = request.getParameter("selectService");

        
        // if valid, thken create new Promotion
        ValidationServices vs = new ValidationServices();
        if (vs.isPromotionInputsValid(prName, prDesc, discPercent, serviceId,
                                        startDate, endDate)) {
            // create service object from DB based on ID from value in the selection -> option
            System.out.println("Inputs is valid");
            
            pr.setPromotionName(prName);
            pr.setPromoDescription(prDesc);
            
            Discount disc = new Discount(0, new BigDecimal(discPercent), discType.charAt(0));
            
        }
        
        return null;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
