/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.ConverterRemote;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Arles Mathieu
 */
@WebServlet(name = "ConverterServlet", urlPatterns = {"/ConverterServlet"})
public class ConverterServlet extends HttpServlet {

    @EJB
    private ConverterRemote converter;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConverterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConverterServlet at " + request.getContextPath() + "</h1>");
            out.println("<h1><b><center>Converter</center></b></h1>");
            out.println("<hr>");
            out.println("<p>Enter an amount to convert:</p>");
            out.println("<form method=\"get\">");
            out.println("<input type=\"text\"name=\"amount\" size=\"25\">");
            out.println("<br>");
            out.println("<p>");
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("<input type=\"reset\" value=\"Reset\">");
            out.println("</form>");
            String amount = request.getParameter("amount");
            if (amount != null && amount.length() > 0) {
                try {
                    java.math.BigDecimal d = new java.math.BigDecimal(amount);
                    out.println("<p>");
                    out.println("<p>");
                    out.println(amount + " Dollars are " + converter.dollarToYen(d) + " Yen.");
                    out.println("<p>");
                    out.println(amount + " Yen are " + converter.yenToEuro(d) + " Euro."
                    );
                } catch (Exception e) {
                    out.println("Cannot execute EJB!");
                }
            }
            out.println("</body>");
            out.println("</html>");
        }
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

}
