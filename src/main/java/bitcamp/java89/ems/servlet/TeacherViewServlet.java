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
@WebServlet("/teacher/view")
public class TeacherViewServlet extends AbstractServlet {

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      String name = request.getParameter("name");
      ArrayList<Teacher> list = teacherDao.getListByName(name);
      for (Teacher teacher : list) {
        out.println("--------------------------");
        out.printf("이름: %s\n", teacher.getName());
        out.printf("직위: %s\n", teacher.getCareer());
        out.printf("직위: %s\n", teacher.getLangauge());
        out.printf("직위: %s\n", teacher.isBook() ? "y": "n");
        out.printf("전화: %s\n", teacher.getTel());
        out.printf("이메일: %s\n", teacher.getEmail());
      }
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  
}
