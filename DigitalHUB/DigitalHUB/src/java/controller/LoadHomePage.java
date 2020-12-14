package controller;

import context.DBContext;
import entity.Digital;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DigitalModel;

@WebServlet(name = "LoadHomePage", urlPatterns = {"/LoadHomePage"})
public class LoadHomePage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //begin of set imagePath
            DBContext bContext = new DBContext();
            String imagePath = bContext.getImagePath();
            request.setAttribute("imagePath", imagePath);
            //end of set imagePath
            
            //begin of get and check digital Id on homepage.isp
            String id = request.getParameter("id");
            id = (id == null) ? "1" : id;
            int digitalId = 0;
            try {
                digitalId = Integer.parseInt(id);
            } catch (Exception e) {
                digitalId = 1;
            }
            //end of get and check digital Id on homepage.jsp
            
            DigitalModel digitalModel = new DigitalModel();
            String shortDes = digitalModel.getTop1Digital().getShortDes();

            if (digitalId == 1) {
                Digital d = digitalModel.getTop1Digital();
                request.setAttribute("top1", d);
            } else {
                Digital d = digitalModel.getOne(digitalId);
                if (d != null) {
                    request.setAttribute("top1", d);
                } else {
                    request.setAttribute("top1", digitalModel.getTop1Digital());
                }
            }

            List<Digital> list5nextDigital = digitalModel.get5NextDigital();
            request.setAttribute("list5next", list5nextDigital);
            request.setAttribute("shortDes", shortDes);
            request.getRequestDispatcher("HomePage.jsp").include(request, response);
        } catch (Exception ex) {
            System.out.println("CHet di");
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
