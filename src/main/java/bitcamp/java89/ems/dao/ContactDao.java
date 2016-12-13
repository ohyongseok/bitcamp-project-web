package bitcamp.java89.ems.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.vo.Contact;

// ContactController 에서 호출할 매서드 규칙!!!!
public interface ContactDao {
  ArrayList<Contact> getList() throws Exception;
  ArrayList<Contact> getListByName(String name) throws Exception;
  Contact getDetail(String email) throws Exception;
  void insert(Contact contact) throws Exception;
  void update(Contact contact) throws Exception;
  void delete(String email) throws Exception;
  boolean existEmail(String email) throws Exception;
}
