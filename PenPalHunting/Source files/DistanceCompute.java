/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication14;


public class DistanceCompute {

    public double getEuclidDis(Student p1, Student p2){
                double count_dis = 0;
                float[] p1_local_array = p1.getStudentArray();
                float[] p2_local_array = p2.getStudentArray();

                if (p1_local_array.length != p2_local_array.length) {
                    throw new IllegalArgumentException("length of array must be equal!");
                }

                for (int i = 0; i < p1_local_array.length; i++) {
                    count_dis += Math.pow(p1_local_array[i] - p2_local_array[i], 2);
                }

                return Math.sqrt(count_dis);

        }


    }


    
