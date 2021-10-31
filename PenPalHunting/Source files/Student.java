/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication14;


public class Student {
    private float[] answerArray;
    private int id;
    private int clusterId;
    private float dist;

    public Student(int id, float[] localArray) {
        this.id = id;
        this.answerArray = localArray;
    }

    public Student(float[] localArray) {
        this.id = -1;
        this.answerArray = localArray;
    }

    public float[] getStudentArray() {
        return answerArray;
    }

    public int getId() {
        return id;
    }

    public void setClusterId(int clusterId) {

        this.clusterId = clusterId;
    }

    public int getClusterid() {

        return clusterId;
    }

    public void setDist(float dist) {

        this.dist = dist;
    }

    public float getDist() {
        return dist;
    }


    @Override
    public String toString() {
        String result = "Student_id=" + id + "  [";
        for (int i = 0; i < answerArray.length; i++) {
            result += answerArray[i] + " ";
        }
        return result.trim()+"] clusterId: "+clusterId+" dist: "+dist;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Student student = (Student) obj;
        if (student.answerArray.length != answerArray.length) {
            return false;
        }

        for (int i = 0; i < answerArray.length; i++) {
            if (Float.compare(student.answerArray[i], answerArray[i]) != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        float x = answerArray[0];
        float y = answerArray[answerArray.length - 1];
        long temp = x != +0.0d ? Double.doubleToLongBits(x) : 0L;
        int result = (int) (temp ^ (temp >>> 32));
        temp = y != +0.0d ? Double.doubleToLongBits(y) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}


