/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import context.DBContext;
import entity.AboutShop;
import entity.Sushi;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AboutShopDAO;
import model.SushiDAO;

/**
 *
 * @author Hoan toan hanh phuc
 */
@WebServlet(name = "InforControl", urlPatterns = {"/InforControl"})
public class InforControl extends HttpServlet {

    /**
     *Servlet InforControl to control FindUs.jsp
     *
     * process
     *
     * get images folder link and set to jsp page
     *
     * set object sboutShop to jsp page
     * 
     * forward to FindUs.jsp
     * if process if fail then forward to error.jsp
     * @param request is required from client to server
     * @param response is the server return the result to the client
     * @param getParameter is get string from jsp page to servlet
     * @param setAttribute is set object from servlet to jsp page
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //begin of get and set imagePath
            DBContext bContext = new DBContext();
            String imagePath = bContext.getImagePath();
            request.setAttribute("imagePath", imagePath);
            //end of get and set imagePath

            //begin of get and set information
            AboutShopDAO aboutShopDAO = new AboutShopDAO();
            AboutShop aboutShop = aboutShopDAO.getInformation();
            request.setAttribute("info", aboutShop);
            //end of get and set information

            //begin set value from servlet to jsp 
            request.setAttribute("activeInfor", "activeInfor");
            //end of set value from servlet to jsp
            request.getRequestDispatcher("FindUs.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("Error.jsp");
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
