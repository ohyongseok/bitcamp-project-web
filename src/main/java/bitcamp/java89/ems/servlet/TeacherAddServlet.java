package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;
@WebServlet("/teacher/add")
public class TeacherAddServlet extends AbstractServlet {

//  teacher/add?name=1&career=12&langauge=13&book=true&email=14&tel=15
  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      if (teacherDao.existName(request.getParameter("name"))) {
        out.println("같은 이름이 존재합니다. 등록을 취소합니다.");
        return;
      }
      Teacher teacher = new Teacher();
      teacher.setName(request.getParameter("name"));
      teacher.setCareer(request.getParameter("career"));
      teacher.setLangauge(request.getParameter("langauge"));
      teacher.setBook(Boolean.parseBoolean(request.getParameter("book")));
      teacher.setEmail(request.getParameter("email"));
      teacher.setTel(request.getParameter("tel"));
      
      teacherDao.insert(teacher);
      out.println("등록하였습니다.");
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  
}
