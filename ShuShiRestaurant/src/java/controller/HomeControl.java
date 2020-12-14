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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SushiDAO;


@WebServlet(name = "HomeControl", urlPatterns = {"/HomeControl"})
public class HomeControl extends HttpServlet {
/**
 * Servlet HomeControl to control Home.jsp
 * 
 * process
 * 
 * get images folder link and set to jsp page
 * 
 * get page Index from jsp page
 * 
 * set list sushi to jsp page by pageIndex and pageSize
 * 
 * forward to Home.jsp
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
            
            
            //begin of get index
            String pageIndex = request.getParameter("pageIndex");
            pageIndex = pageIndex == null ? "1" : pageIndex;
            int index = 0;
            try {
                index = Integer.parseInt(pageIndex);
            } catch (Exception e) {
                index = 1;
            }
            //end of get index
            
            //begin count total and maxPage sushi
            SushiDAO sushiDAO = new SushiDAO();
            int pageSize = 3;
            int total = sushiDAO.getTotalSushi();
            int maxPage = total / pageSize + (total % pageSize > 0 ? 1 : 0);
            if(index <= 0 || index > maxPage){
                index = 1;
            }
            //end of count total and maxPage sushi
            
            //begin of check: if sushi is null then index = 1
            List<Sushi> list = sushiDAO.ListSuShiPagging(pageSize, pageSize);
            if (list == null) {
                request.setAttribute("listSushi", sushiDAO.ListSuShiPagging(1, pageSize));
            } else {
                request.setAttribute("listSushi1", list);
            }
            //end of check: if sushi is null then index = 1
            
            //begin set value from servlet to jsp
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("pageIndex", index);
            request.setAttribute("activeHome", "activeHome");
            //end of set value from servlet to jsp
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        } catch (Exception ex) {
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
