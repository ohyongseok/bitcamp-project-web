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
@WebServlet("/teacher/update")
public class TeacherUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    request.setCharacterEncoding("UTF-8");
    response.setHeader("Refresh", "1;url=list");
    
    String name = request.getParameter("name");
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    Teacher teacher = new Teacher();
    teacher.setName(request.getParameter("name"));
    teacher.setCareer(request.getParameter("career"));
    teacher.setLangauge(request.getParameter("langauge"));
    teacher.setBook(Boolean.getBoolean(request.getParameter("book")));
    teacher.setEmail(request.getParameter("email"));
    teacher.setTel(request.getParameter("tel"));
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강사 관리-변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>변경 결과</h1>");
    
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      
      if (!teacherDao.existName(name)) {
        throw new Exception("이름 찾지 못했습니다.\n");
      }
        teacherDao.update(teacher);
        out.printf("<p>업데이트 완료하였습니다.</p>");
        return;
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    out.println("</body>");
    out.println("</html>");
  }

  
}
