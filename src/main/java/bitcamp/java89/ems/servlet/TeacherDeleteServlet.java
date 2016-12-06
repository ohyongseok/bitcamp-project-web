package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;
@WebServlet("/teacher/delete")
public class TeacherDeleteServlet extends AbstractServlet {

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      if (!teacherDao.existName(request.getParameter("name"))) {
        out.println("해당 데이터가 없습니다.");
        return;
      }
      teacherDao.delete(request.getParameter("name"));
      out.print("삭제하였습니다.\n");
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  
}
