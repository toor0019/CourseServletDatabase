package view;

import buisness.FileLogic;
import dto.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.Group;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;

/**
 *
 * @author Shariar
 */
public class FileTable extends HttpServlet {

    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
                out.println("<title>FileView</title>");            
            out.println("</head>");
            out.println("<body>");
            
            FileLogic logic = new FileLogic();
            List<File> students = logic.getAllFiles();
                out.println("<form action=\"FileTable\" method=\"post\">");
            out.println("<table align=\"center\" border=\"1\">");
            out.println("<caption>File</caption>");
            out.println("<tr>");
            out.println("<th>File ID</th>");
             out.println("<th>File Name</th>");
              out.println("<th>Download File</th>");
            out.println("</tr>");
           int i=1;
            for (File student : students) {
                out.printf("<tr><td>%s</td><td>%s</td><td><input type=\"submit\" name=\"down\" value=\"%s\"></td><br></tr>",student.getId(),student.getFile(),student.getId());
                
            }
            
            out.println("</table>");
           out.println("</form>");
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
       PrintWriter out = response.getWriter();
        response.setContentType("application/octect-stream");
       int id = Integer.parseInt(request.getParameter("down"));
       out.println("<h1>"+id+"</h1>");
         FileLogic logic = new FileLogic();
            List<File> students = logic.getAllFiles();
            File file = null;
            for(File toor:students)
            {
            if(toor.getId()==id){
            file=toor;
            }
            }  
        try {
            response.setContentLength((int) file.getFile().length());
        } catch (SQLException ex) {
            Logger.getLogger(FileTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream x = null;
        try {
            x= file.getFile().getBinaryStream();
          
        } catch (SQLException ex) {
            Logger.getLogger(FileTable.class.getName()).log(Level.SEVERE, null, ex);
        }

    
int nextByte = x.read();
while ( nextByte != -1 ) {
out.write(nextByte);
nextByte = x.read();
};

    
    
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
