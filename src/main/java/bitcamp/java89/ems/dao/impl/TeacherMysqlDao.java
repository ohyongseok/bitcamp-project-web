package bitcamp.java89.ems.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bitcamp.java89.ems.dao.TeacherDao;
import bitcamp.java89.ems.util.DataSource;
import bitcamp.java89.ems.vo.Teacher;
public class TeacherMysqlDao  implements TeacherDao {
  DataSource ds;
  
  private TeacherMysqlDao() {
    ds = DataSource.getInstance();
  }
  static TeacherMysqlDao instance;
  public static TeacherMysqlDao getInstance() {
    if (instance == null) {
      instance = new TeacherMysqlDao();
    }
    return instance;
  }
  
  public ArrayList<Teacher> getList() throws Exception {
    ArrayList<Teacher> list = new ArrayList<>();
    Connection con = ds.getConnection();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select name, career, lang, book, email, tel from ex_teachers");
      ResultSet rs = stmt.executeQuery();){
      
      while (rs.next()) {
        Teacher teacher = new Teacher();
        teacher.setName(rs.getString("name"));
        teacher.setCareer(rs.getString("career"));
        teacher.setLangauge(rs.getString("lang"));
        teacher.setBook(rs.getString("book").equals("y")? true: false);
        teacher.setEmail(rs.getString("email"));
        teacher.setTel(rs.getString("tel"));
        list.add(teacher);
      }
      rs.close();
    } finally {
      ds.returnConnecion(con);
    }
    return list;
  }
  public ArrayList<Teacher> getListByName(String name) throws Exception {
    ArrayList<Teacher> list = new ArrayList<>();
    Connection con = ds.getConnection();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select name, career, lang, book, email, tel from ex_teachers where name=?");
      ){
      
      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery();
      
      while (rs.next()) {
        Teacher teacher = new Teacher();
        teacher.setName(rs.getString("name"));
        teacher.setCareer(rs.getString("career"));
        teacher.setLangauge(rs.getString("lang"));
        teacher.setBook(rs.getString("book").equals("y")? true: false);
        teacher.setEmail(rs.getString("email"));
        teacher.setTel(rs.getString("tel"));
        list.add(teacher);
      }
      rs.close();
    } finally {
      ds.returnConnecion(con);
    } 
    return list;
  }
  
  public Teacher getDetail(String name) throws Exception {
    Connection con = ds.getConnection();
    Teacher teacher = null;
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select name, career, lang, book, email, tel from ex_teachers where name=?");
      ){
      
      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery();
      
      if (rs.next()) {
        teacher = new Teacher();
        teacher.setName(rs.getString("name"));
        teacher.setCareer(rs.getString("career"));
        teacher.setLangauge(rs.getString("lang"));
        teacher.setBook(rs.getString("book").equals("y")? true: false);
        teacher.setEmail(rs.getString("email"));
        teacher.setTel(rs.getString("tel"));
      }
      rs.close();
    } finally {
      ds.returnConnecion(con);
    } 
    return teacher;
  }

   public void insert(Teacher teacher) throws Exception {
     Connection con = ds.getConnection();
     try (
       PreparedStatement stmt = con.prepareStatement(
           "insert into ex_teachers(name,career,lang,book,email,tel)" + "values(?,?,?,?,?,?)"); ) {
       
       stmt.setString(1, teacher.getName());
       stmt.setString(2, teacher.getCareer());
       stmt.setString(3, teacher.getLangauge());
       stmt.setString(4, teacher.isBook() ? "y" : "n");
       stmt.setString(5, teacher.getEmail());
       stmt.setString(6, teacher.getTel());
       
       stmt.executeUpdate();
     } finally {
       ds.returnConnecion(con);
     } 
  }
   public void update(Teacher teacher) throws Exception { 
     Connection con = ds.getConnection();
     try (
       PreparedStatement stmt = con.prepareStatement(
           " update ex_teachers set" 
           + " career=?,lang=?,book=?,email=?,tel=?" 
           + "where name=?"); ) {
       
       stmt.setString(1, teacher.getCareer());
       stmt.setString(2, teacher.getLangauge());
       stmt.setString(3, teacher.isBook() ? "y" : "n");
       stmt.setString(4, teacher.getEmail());
       stmt.setString(5, teacher.getTel());
       stmt.setString(6, teacher.getName());
       
       stmt.executeUpdate();
     } finally {
       ds.returnConnecion(con);
     }
  }

  public boolean existEmail(String email) throws Exception {
    Connection con = ds.getConnection();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select * from ex_teachers where email=?"); ) {
      
      stmt.setString(1, email);
      ResultSet rs = stmt.executeQuery();
      
      if (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        rs.close();
        return true;
      } else {
        rs.close();
        return false;
      }
    } finally {
      ds.returnConnecion(con);
    }
  }

  public boolean existName(String name) throws Exception {
    Connection con = ds.getConnection();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select * from ex_teachers where name=?"); ) {
      
      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery();
      
      if (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        rs.close();
        return true;
      } else {
        rs.close();
        return false;
      }
    } finally {
      ds.returnConnecion(con);
    }
  }
   public void delete(String name) throws Exception {
     Connection con = ds.getConnection();
     try (
       PreparedStatement stmt = con.prepareStatement("delete from ex_teachers where name=?");){
       
       stmt.setString(1, name);
       
       stmt.executeUpdate();
       
     } finally {
       ds.returnConnecion(con);
     }
   }  
}
