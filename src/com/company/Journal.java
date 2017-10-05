package com.company;

import java.io.Serializable;
import java.util.*;

public class Journal implements Serializable{
  private Lesson lesson;
  private Set<Student> presentSet = new HashSet<>();
  private Set<Student> absentSet = new HashSet<>();
  private Set<Group> groups = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Journal journal = (Journal) o;

    if (!lesson.equals(journal.lesson)) return false;
    if (!presentSet.equals(journal.presentSet)) return false;
    if (!absentSet.equals(journal.absentSet)) return false;
    return groups.equals(journal.groups);
  }

  @Override
  public int hashCode() {
    int result = lesson.hashCode();
    result = 31 * result + presentSet.hashCode();
    result = 31 * result + absentSet.hashCode();
    result = 31 * result + groups.hashCode();
    return result;
  }
  public Set<Group> getGroups() {
    return groups;
  }

  public void setGroups(Set<Group> groups) {
    this.groups = groups;
  }

  public Set<Student> getAbsentSet() {
    return absentSet;
  }

  public void setAbsentSet(Set<Student> absentSet) {
    this.absentSet = absentSet;
  }

  public Journal(Lesson lesson, Set<Student> presentSet) {
    this.lesson = lesson;
    this.presentSet = presentSet;
    //this.absentSet = new HashSet<>();
  }

  public Lesson getLesson() {
    return lesson;
  }

  public void setLesson(Lesson lesson) {
    this.lesson = lesson;
  }

  public Set<Student> getPresentSet() {
    return presentSet;
  }

  public void addGroup(Group group){
     groups.add(group);
  }

  public void setPresentSet(Set<Student> presentSet) {
    this.presentSet = presentSet;
  }
}
