package bitcamp.java89.ems.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.vo.Teacher;

public interface TeacherDao {
  ArrayList<Teacher> getList() throws Exception;
  ArrayList<Teacher> getListByName(String name) throws Exception;
  void insert(Teacher teacher) throws Exception;
  void update(Teacher teacher) throws Exception;
  void delete(String name) throws Exception;
  boolean existEmail(String email) throws Exception;
  boolean existName(String name) throws Exception;
}
