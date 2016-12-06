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
@WebServlet("/teacher/list")
public class TeacherListServlet extends AbstractServlet {

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      ArrayList<Teacher> list = teacherDao.getList();
      for (Teacher teacher : list) {
        out.printf("%s, %s, %s, %s, %s, %s\n",
            teacher.getName(),
            teacher.getCareer(),
            teacher.getLangauge(),
            ((teacher.isBook()) ? "yes" : "no"),
            teacher.getEmail(),
            teacher.getTel());
      }
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  
}
