/* 작업내용: 직렬화 적용 
*/
package bitcamp.java89.ems.controller;

import java.io.PrintStream;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.annotation.RequestParam;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;
@Component
public class TeacherController {
  @Autowired TeacherDao teacherDao;
  

// teacher/add?name=1&career=12&langauge=13&book=true&email=14&tel=15
  @RequestMapping(value = "teacher/add")
  public void add(
      @RequestParam("name")String name,
      @RequestParam("career") String career,
      @RequestParam("langauge") String langauge,
      @RequestParam("book") boolean book,
      @RequestParam("email") String email,
      @RequestParam("tel") String tel,
      PrintStream out) 
    throws Exception {
      if (teacherDao.existEmail(email)) {
        out.println("같은 이메일이 존재합니다. 등록을 취소합니다.");
        return;
      }
      Teacher teacher = new Teacher();
      teacher.setName(name);
      teacher.setCareer(career);
      teacher.setLangauge(langauge);
      teacher.setBook(book);
      teacher.setEmail(email);
      teacher.setTel(tel);
      
      teacherDao.insert(teacher);
      out.println("등록하였습니다.");
    }
  
  @RequestMapping(value = "teacher/delete")
  public void delete( @RequestParam("name")String name,
      @RequestParam("career") String career,
      @RequestParam("langauge") String langauge,
      @RequestParam("book") boolean book,
      @RequestParam("email") String email,
      @RequestParam("tel") String tel,
      PrintStream out) 
    throws Exception {
    if (!teacherDao.existName(name)) {
      out.println("해당 데이터가 없습니다.");
      return;
    }
    teacherDao.delete(name);
    out.print("삭제하였습니다.\n");
  }
  
  @RequestMapping(value = "teacher/list")
  public void list(PrintStream out) 
    throws Exception {
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
  } 
  
  @RequestMapping(value = "teacher/update")
  public void update( @RequestParam("name")String name,
      @RequestParam("career") String career,
      @RequestParam("langauge") String langauge,
      @RequestParam("book") boolean book,
      @RequestParam("email") String email,
      @RequestParam("tel") String tel,
      PrintStream out) 
    throws Exception {
    if (!teacherDao.existName(name)) {
      out.print("이름 찾지 못했습니다.\n");
      return;
    }
    Teacher teacher = new Teacher();
      teacher.setName(name);
      teacher.setCareer(career);
      teacher.setLangauge(langauge);
      teacher.setBook(book);
      teacher.setEmail(email);
      teacher.setTel(tel);
      teacherDao.update(teacher);
      out.printf("업데이트 완료하였습니다.\n");
      return;
  }
  
  @RequestMapping(value = "teacher/view")
  public void view(@RequestParam(value ="name") String name, PrintStream out) 
    throws Exception {
    ArrayList<Teacher> list = teacherDao.getListByName(name);
    for (Teacher teacher : list) {
      out.println("-----------------------------");
      out.printf("이름: %s\n경력: %s\n언어: %s\n출간여부: %s\n이메일: %s\n전화번호: %s\n",
          teacher.getName(),
          teacher.getCareer(),
          teacher.getLangauge(),
          ((teacher.isBook()) ? "yes" : "no"),
          teacher.getEmail(),
          teacher.getTel());
    }
  } 
}
