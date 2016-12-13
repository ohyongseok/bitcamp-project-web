package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
@WebServlet("/teacher/delete")
public class TeacherDeleteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    response.setHeader("Refresh", "1;url=list");
    String name = request.getParameter("name");
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강사 관리-삭제</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강사 결과</h1>");
    
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      
      if (!teacherDao.existName(name)) {
        throw new Exception("해당 데이터가 없습니다.");
      }
      teacherDao.delete(name);
      out.print("<p>삭제하였습니다.</p>");
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
    out.println("</body>");
    out.println("</html>");
  }

  
}
