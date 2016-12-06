package bitcamp.java89.ems.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public abstract class AbstractServlet implements Servlet {
  ServletConfig connfig;
  
  @Override
  public void init(ServletConfig config) throws ServletException {
    this.connfig = config;
  }

  @Override
  public ServletConfig getServletConfig() {
    return this.connfig;
  }

  @Override
  public String getServletInfo() {
    return this.getClass().getName();
  }

  @Override
  public void destroy() {
    
  }
  
}
