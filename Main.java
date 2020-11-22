package com.company;

public class Main {

    public static void main(String[] args) {
	SparseMatrix mat = new SparseMatrix(3,3);
        try {
            mat.put(2,2, 6);
            mat.put (1,1, -0.5);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(mat);
    }
}
