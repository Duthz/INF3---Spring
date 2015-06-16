/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ejb.BookException;
import ejb.CartBeanRemote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Arles Mathieu
 */
@WebServlet(name = "CartClientServlet", urlPatterns = {"/CartClientServlet"})
public class CartClientServlet extends HttpServlet {
    CartBeanRemote cartBean = lookupCartBeanRemote();


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet CartClientServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet CartClientServlet at " + request.getContextPath() + "</h1>");
        try {
            cartBean.initialize("Duke d'Url", "123");
            cartBean.addBook("Infinite Jest");
            cartBean.addBook("Bel Canto");
            cartBean.addBook("Kafka on the Shore");
            Vector bookList = cartBean.getContent();
            Enumeration enumer = bookList.elements();
            while (enumer.hasMoreElements()) {
                String title = (String) enumer.nextElement();
                out.println("Retrieving book title from cart: " + title);
                out.println("<br>");
            }
            out.println("Removing \"Gravity's Rainbow\" from cart.");
            out.println("<br>");
            cartBean.removeBook("Gravity's Rainbow");
            cartBean.remove();
        } catch (BookException ex) {
            out.println("Caught a BookException: " + ex.getMessage());
            out.println("<br>");
        } catch (Exception ex) {
            out.println("Caught an unexpected exception!");
            out.println("<br>");
            ex.printStackTrace();
        }
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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

    private CartBeanRemote lookupCartBeanRemote() {
        try {
            Context c = new InitialContext();
            return (CartBeanRemote) c.lookup("java:global/EJBCart/CartBean!ejb.CartBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }



}
