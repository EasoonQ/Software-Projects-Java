/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication14;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class KMeansRun {
    private int kNum;
    private int iterNum = 10;

    private int iterMaxTimes = 100000;
    private int iterRunTimes = 0;
    private float disDiff = (float) 0.01;

    private List<float[]> original_data =null;
    private static List<Student> studentList = null;
    private DistanceCompute disC = new DistanceCompute();
    private int len = 0;

    public KMeansRun(int k, List<float[]> original_data) {
        this.kNum = k;
        this.original_data = original_data;
        this.len = original_data.get(0).length;
        check();
        init();
    }

   
    private void check() {
        if (kNum <= 0){
            throw new IllegalArgumentException("k must be the number > 0");
        }
        if (original_data == null){
            throw new IllegalArgumentException("program can't get real data");
        }
    }

  
    private void init() {
        studentList = new ArrayList<>();
        for (int i = 0, j = original_data.size(); i < j; i++){
            studentList.add(new Student(i, original_data.get(i)));
        }
    }

    private Set<Cluster> chooseCenterCluster() {
        Set<Cluster> clusterSet = new HashSet<Cluster>();
        Random random = new Random();
        for (int id = 0; id < kNum; ) {
            Student student = studentList.get(random.nextInt(studentList.size()));
            boolean flag =true;
            for (Cluster cluster : clusterSet) {
                if (cluster.getCenter().equals(student)) {
                    flag = false;
                }
            }
            if (flag) {
                Cluster cluster =new Cluster(id, student);
                clusterSet.add(cluster);
                id++;
            }
        }
        return clusterSet;
    }

    public void cluster(Set<Cluster> clusterSet){
        for (Student student : studentList) {
            float min_dis = Integer.MAX_VALUE;
            for (Cluster cluster : clusterSet) {
                float tmp_dis = (float) Math.min(disC.getEuclidDis(student, cluster.getCenter()), min_dis);
                if (tmp_dis != min_dis) {
                    min_dis = tmp_dis;
                    student.setClusterId(cluster.getId());
                    student.setDist(min_dis);
                }
            }
        }
        for (Cluster cluster : clusterSet) {
            cluster.getMembers().clear();
            for (Student student : studentList) {
                if (student.getClusterid()==cluster.getId()) {
                    cluster.addStudent(student);
                }
            }
        }
    }

   
    public boolean calculateCenter(Set<Cluster> clusterSet) {
        boolean ifNeedIter = false;
        for (Cluster cluster : clusterSet) {
            List<Student> student_list = cluster.getMembers();
            float[] sumAll =new float[len];
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < student_list.size(); j++) {
                    sumAll[i] += student_list.get(j).getStudentArray()[i];
                }
            }
            for (int i = 0; i < sumAll.length; i++) {
                sumAll[i] = (float) sumAll[i]/student_list.size();
            }
            if(disC.getEuclidDis(cluster.getCenter(), new Student(sumAll)) > disDiff){
                ifNeedIter = true;
            }
            cluster.setCenter(new Student(sumAll));
        }
        return ifNeedIter;
    }

   
    public Set<Cluster> run() {
        Set<Cluster> clusterSet= chooseCenterCluster();
        boolean ifNeedIter = true;
        while (ifNeedIter) {
            cluster(clusterSet);
            ifNeedIter = calculateCenter(clusterSet);
            iterRunTimes ++ ;
        }
        return clusterSet;
    }

    public int getIterTimes() {

        return iterRunTimes;
    }
}


