package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.ContactMysqlDao;
import bitcamp.java89.ems.vo.Contact;
@WebServlet("/contact/delete")
public class ContactDeleteServlet extends AbstractServlet {

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      ContactMysqlDao contactDao = ContactMysqlDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      if (!contactDao.existEmail(request.getParameter("email"))) {
        out.println("해당 데이터가 없습니다.");
        return;
      }
        
      contactDao.delete(request.getParameter("email"));
      out.println("해당 데이터를 삭제 완료하였습니다.");
      
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  
}
