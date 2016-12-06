package bitcamp.java89.ems.vo;

import java.io.Serializable;

public class Teacher implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public String name; //인스턴스 변수
  protected String career;
  protected String langauge;
  protected boolean book;
  protected String email;
  protected String tel;

  public Teacher() {
    super();
  }
  public Teacher(String name, String career, String tel) {
    this.name = name;
    this.career = career;
    this.tel = tel;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getCareer() {
    return career;
  }
  public void setCareer(String career) {
    this.career = career;
  }
  public String getLangauge() {
    return langauge;
  }
  public void setLangauge(String langauge) {
    this.langauge = langauge;
  }
  public boolean isBook() {
    return book;
  }
  public void setBook(boolean book) {
    this.book = book;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  @Override
  public String toString() {
    return "Teacher [name=" + name + ", career=" + career + ", langauge=" + langauge + ", book=" + book + ", email="
        + email + ", tel=" + tel + "]";
  }
  

}
