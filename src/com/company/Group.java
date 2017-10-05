package com.company;

import java.io.Serializable;
import java.util.*;

public class Group implements Serializable{
  private final int groupNum;
  private final String groupName;

  public Group(int groupNum, String gName) {
    this.groupNum = groupNum;
    this.students = new HashSet<>();
    this.groupName = gName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Group)) return false;

    Group group = (Group) o;

    if (getGroupNum() != group.getGroupNum()) return false;
    return getGroupName().equals(group.getGroupName());
  }

  @Override
  public int hashCode() {
    int result = getGroupNum();
    result = 31 * result + getGroupName().hashCode();
    return result;
  }

  public String getGroupName() {

    return groupName;
  }

  public int getGroupNum() { return groupNum; }

  public Set<Student> getStudents() {
    return students;
  }

  public void setStudents(Set<Student> students) {
    this.students = students;
  }

  private Set<Student> students = new HashSet<>();

  public void addStudent(Student student){
    students.add(student);
  }
  public void removeStudent(Student student){
    students.remove(student);
  }
}
