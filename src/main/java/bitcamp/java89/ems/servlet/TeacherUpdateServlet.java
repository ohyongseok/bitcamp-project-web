package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;
import bitcamp.java89.ems.vo.Teacher;
@WebServlet("/teacher/update")
public class TeacherUpdateServlet extends AbstractServlet {

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      if (!teacherDao.existName(request.getParameter("name"))) {
        out.print("이름 찾지 못했습니다.\n");
        return;
      }
      Teacher teacher = new Teacher();
        teacher.setName(request.getParameter("name"));
        teacher.setCareer(request.getParameter("career"));
        teacher.setLangauge(request.getParameter("langauge"));
        teacher.setBook(Boolean.getBoolean(request.getParameter("book")));
        teacher.setEmail(request.getParameter("email"));
        teacher.setTel(request.getParameter("tel"));
        teacherDao.update(teacher);
        out.printf("업데이트 완료하였습니다.\n");
        return;
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  
}
