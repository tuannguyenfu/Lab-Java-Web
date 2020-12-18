/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DigitalDAO;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Digital;

/**
 *
 * @author admin
 */
public class SearchController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {

            String message = null;
            // get the value of page size 
            int pageSize = 3;
            DigitalDAO digitalDAO = new DigitalDAO();
            String search = request.getParameter("txtSearch");
            String raw_pageIndex = request.getParameter("pageIndex");
            if (raw_pageIndex == null) {
                raw_pageIndex = "1";
            }

            //get number page to pagging and total page
            int count = digitalDAO.count(search);
            // check value total of page, then paging or send message error
            if (count > 0) {
//                int pageCount = (count % pageSize == 0) ? count / pageSize : count / pageSize + 1;
                int pageCount = count / pageSize;
                if(count % pageSize !=0){
                    pageCount= pageCount+1;
                }
                request.setAttribute("pageCount", pageCount);
               // request.setAttribute("count", count);
                
                int pageIndex = 0;
                try {
                    // check page index
                    pageIndex = Integer.valueOf(raw_pageIndex);
                    
                    if (pageIndex > pageCount || pageIndex <= 0) {
                        message = "The page " + pageIndex + " isn't exist!!";
                    } else {
                        //get page index 
                        request.setAttribute("pageIndex", pageIndex);
                    }
                } catch (NumberFormatException ex) {
                    message = ex.getMessage();
                }
                // get list digital found
                ArrayList<Digital> listSearch = digitalDAO.searchDigitals(pageIndex, pageSize, search);
                request.setAttribute("listSearch", listSearch);
            } else {
                message = "Not Found!!";
            }

            request.setAttribute("message", message);
            //get key word
            request.setAttribute("search", search);

            // get most and five recent digital
            Digital mostRecent = digitalDAO.getTheMostRecent();
            request.setAttribute("mostRecent", mostRecent);
            ArrayList<Digital> digitals = digitalDAO.getFiveRecent();
            request.setAttribute("fiveRecent", digitals);

            request.getRequestDispatcher("SearchPage.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("message", ex.getMessage());
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
