/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import buisness.StudentLogic;
import buisness.FileDetailLogic;
import dto.FileDetail;
import dto.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gurki
 */

public class FileDetailsTable extends HttpServlet {

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
            out.println("<title>FileDetailView</title>");            
            out.println("</head>");
            out.println("<body>");
            
            FileDetailLogic logic = new FileDetailLogic();
            List<FileDetail> students = logic.getAllFileDetail();
               
            out.println("<table align=\"center\" border=\"1\">");
            out.println("<caption>File</caption>");
            
            out.println("<tr>");
            out.println("<th>File ID</th>");
            out.println("<th>File Name</th>");
              out.println("<th>File Type</th>");
               out.println("<th>File Size</th>");
                out.println("<th>File Date</th>");
            out.println("</tr>");
            students.forEach((student) -> {
                out.printf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", student.getFileId(), student.getName(),student.getType(),student.getSize(),student.getDate());
            });
            
            out.println("</table>");
           
            out.println("</body>");
            out.println("</html>");
        }
    }
    
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
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    }
      

    