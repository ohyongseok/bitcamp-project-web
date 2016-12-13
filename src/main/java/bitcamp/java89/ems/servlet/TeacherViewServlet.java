package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;
@WebServlet("/teacher/view")
public class TeacherViewServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    String name = request.getParameter("name");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
//      out.println("<meta name='viewport' content='width=device-with, user-scalable=no, maximum-scale=1'>");
    out.println("<title>강사 관리-상세정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강사 정보</h1>");
    out.println("<form action='update' method='post'>");
    
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      Teacher teacher = teacherDao.getDetail(name);
      
      if (teacher == null) {
        throw new Exception ("해당 이름의 강사가 없습니다");
      }
      
      out.println("<table border ='1'>");
      
      out.printf("<tr><th>이름</th><td>"
          + "<input name='name' type='text' value='%s' readonly></td></tr>\n", teacher.getName());
      out.printf("<tr><th>경력</th><td>"
          + "<input name='career' type='text' value='%s'></td></tr>\n", teacher.getCareer());
      out.printf("<tr><th>언어</th><td>"
          + "<input name='langauge' type='text' value='%s'></td></tr>\n", teacher.getLangauge());
      out.printf("<tr><th>책 발간여부</th><td><input name='book' type='radio' value='true' >했음<input name='book' type='radio' value='false' >안했음</td></tr>", teacher.isBook());
      out.printf("<tr><th>이메일</th><td>"
          + "<input name ='email' type='text' value='%s' readonly></td></tr>\n", teacher.getEmail());
      out.printf("<tr><th>전화</th><td>"
          + "<input name='tel' type='text' value='%s'></td></tr>\n", teacher.getTel());
      
      out.println("</table>");
      out.println("<button type='submit'>변경</button>");
      out.printf("<a href='delete?name=%s'>삭제 </a>\n", teacher.getName());
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    out.println("<a href='list'>목록</a>"); 
    out.println("</body>");
    out.println("</html>");
  }

  
}
