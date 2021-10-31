/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication14;


import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private int id;
    private Student center;
    private List<Student> members = new ArrayList<Student>();

    public Cluster(int id, Student center) {
        this.id = id;
        this.center = center;
    }

    public Cluster(int id, Student center, List<Student> members) {
        this.id = id;
        this.center = center;
        this.members = members;
    }

    public void addStudent(Student newStudent) {
        if (!members.contains(newStudent)){
            members.add(newStudent);
        }else{
            System.out.println("student {"+newStudent.toString()+"} already exist！");
        }
    }

    public int getId() {
        return id;
    }

    public Student getCenter() {
        return center;
    }

    public void setCenter(Student center) {
        this.center = center;
    }

    public List<Student> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        String toString = "Cluster \n" + "Cluster_id=" + this.id + ", center:{" + this.center.toString()+"}";
        for (Student student : members) {
            toString+="\n"+student.toString();
        }
        return toString+"\n";
    }
}

