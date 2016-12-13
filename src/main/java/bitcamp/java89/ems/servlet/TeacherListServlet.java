package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;
@WebServlet("/teacher/list")
public class TeacherListServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      ArrayList<Teacher> list = teacherDao.getList();
      
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>강사 관리-목록</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>강사 정보</h1>");
      out.println("<a href='form.html'>추가</a><br>");
      out.println("<table border ='1'>");
      out.println("<tr>");
      out.println("<th>아이디</th><th>경력</th><th>언어</th><th>책발간여부</th><th>이메일</th><th>전화</th>");
      out.println("</tr>");
      
      for (Teacher teacher : list) {
        
        out.println("<tr>");
        out.printf("<td><a href=view?name=%1$s>%s</a></td><td>%s</td>"
            + "<td>%s</td><td>%s</td><td>%s</td><td>%s</td>\n",
            teacher.getName(),
            teacher.getCareer(),
            teacher.getLangauge(),
            ((teacher.isBook()) ? "발간OK" : "발간NO"),
            teacher.getEmail(),
            teacher.getTel());
        out.println("</tr>");
        
      }
      out.println("</table>");
      out.println("</body>");
      out.println("</html>");
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  
}
