/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import context.DBContext;
import entity.Digital;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DigitalModel;

@WebServlet(name = "SearchResult", urlPatterns = {"/SearchResult"})
public class SearchResult extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            DigitalModel digitalModel = new DigitalModel();
            request.setAttribute("shortDes", digitalModel.getTop1Digital().getShortDes());
            //begin of set imagePath
            DBContext bContext = new DBContext();
            String imagePath = bContext.getImagePath();
            request.setAttribute("imagePath", imagePath);
            //end of set imagePath
            
            //begin of get txtSearch and pageRow 
            String txtSearch = request.getParameter("txtSearch");
            String page_raw = request.getParameter("txtPage");
            page_raw = (page_raw == null) ? "1" : page_raw;
            int pageIndex = Integer.parseInt(page_raw);
            //end of get txtSearch and pageRow
            
            int pageSize = 3;

            int rowCount = digitalModel.getCountByTxtSearchTitle(txtSearch);
            int maxPage = rowCount / pageSize + (rowCount % pageSize > 0 ? 1 : 0);

            List<Digital> listResultSearch = digitalModel.searchTitleAndPagging(pageIndex, pageSize, txtSearch);
            request.setAttribute("listResultSearch", listResultSearch);

            request.setAttribute("maxPage", maxPage);
            request.setAttribute("pageIndex", pageIndex);

            Digital d = digitalModel.getTop1Digital();
            request.setAttribute("top1", d);

            List<Digital> list5nextDigital = digitalModel.get5NextDigital();
            request.setAttribute("list5next", list5nextDigital);
            request.setAttribute("txtSearch", txtSearch);

            request.getRequestDispatcher("SearchResultPage.jsp").forward(request, response);

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
