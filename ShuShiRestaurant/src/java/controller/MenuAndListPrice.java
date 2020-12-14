/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import context.DBContext;
import entity.Menu;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MenuDAO;

@WebServlet(name = "MenuAndListPrice", urlPatterns = {"/MenuAndListPrice"})
public class MenuAndListPrice extends HttpServlet {

    /**
     * Servlet InforControl to control MenuAndListPrice.jsp
     *
     * process
     *
     * get images folder link and set to jsp page
     *
     * get pageIndex from jsp page
     * 
     * get menu by pageIndex and PageSize from DAO then set to jsp page
     * 
     * forward MenuAndListPrice.jsp
     * 
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
            //begin of set imagePath
            DBContext bContext = new DBContext();
            String imagePath = bContext.getImagePath();
            request.setAttribute("imagePath", imagePath);
            //end of set imagePath

            //begin get and check pageIndex from jsp
            String pageIndex = request.getParameter("pageIndex");
            pageIndex = pageIndex == null ? "1" : pageIndex;
            int index = Integer.parseInt(pageIndex);
           
            //end of get and check pageIndex from jsp

            //begin get list menu by pageIndex and pageSize
            MenuDAO menuDAO = new MenuDAO();
            int pageSize = 3;
            
            
            int total = menuDAO.getTotalMenu();
            int maxPage = total / pageSize + (total % pageSize > 0 ? 1 : 0);
            if (index <= 0 || index > maxPage) {
                index = 1;
            }
            List<Menu> listSushi = menuDAO.ListMenuPagging(index, pageSize);
            //end of get list menu by pageIndex and pageSize
            
            //begin of set value from servlet to jsp page
            request.setAttribute("listMenu", listSushi);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("pageIndex", index);
            request.setAttribute("activeMenu", "activeMenu");
            //end of set value from servlet to jsp page
            request.getRequestDispatcher("MenuAndListPrice.jsp").forward(request, response);
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
