/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import context.DBContext;
import entity.Sushi;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SushiDAO;

@WebServlet(name = "DetailSushi", urlPatterns = {"/DetailSushi"})
public class DetailSushi extends HttpServlet {
/**
 * Process
 * 
 * get images  folder link and set to jsp page
 * 
 * get id from jsp page and set object sushi from servlet to jsp page
 * 
 * forward to DetailSushi.jsp
 * if process if fail then forward to error.jsp
     * @param request is required from client to server
     * @param response is the server return the result to the client
     * @param getParameter is get string from jsp page to servlet
     * @param setAttribute is set object from servlet to jsp page
 * @throws ServletException
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

            //begin get id of sushi when user click title of sushi
            String id = request.getParameter("sushiId");
            int idSushi = 0;
            try {
                idSushi = Integer.parseInt(id);
            } catch (Exception e) {
                idSushi = 1;
            }
            //end of get id of sushi when user click title of sushi
            
            
            //begin set object sushi by id to SushiDetail.jsp
            SushiDAO sushiDAO = new SushiDAO();
            Sushi sushi = sushiDAO.getOne(idSushi);
            if (sushi == null) {
                request.setAttribute("sushi", sushiDAO.getOne(1));
            } else {
                request.setAttribute("sushi", sushi);
            }
            //end of set object sushi by id to SushiDetail.jsp
            
            request.getRequestDispatcher("SushiDetail.jsp").forward(request, response);
        } catch (Exception e) {
            //if try fail, send to Error.jsp
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
