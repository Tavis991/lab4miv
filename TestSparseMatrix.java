package com.company;

import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class TestSparseMatrix {
    public static void main(String args[]) throws Exception {
        SLinkedList<Integer> lst = new SLinkedList<>();
        InputStreamReader reader = new InputStreamReader(System.in);
        StreamTokenizer tokens = new StreamTokenizer(reader);
        int curr;
        double x;
        int i,j;
        SparseMatrix mat = new SparseMatrix(5,2.5);
        SparseMatrix mat2 = new SparseMatrix(5,6.5);

        System.out.println("welcome to sparse matrix printer");
        curr = tokens.nextToken(); //our current state
        try {
            while ((curr != StreamTokenizer.TT_NUMBER) && (!tokens.sval.equals("Q"))) { //checking for proper input and end signal
                switch (tokens.sval) { //switch cases

                    case ("size"):
                        System.out.println(mat.getSize());

                        break;
                    case ("put"):
                        if (tokens.nextToken() != StreamTokenizer.TT_NUMBER) {
                            throw new Exception("illegal use of put");
                        }
                        i = (int) tokens.nval;
                        if (tokens.nextToken() != StreamTokenizer.TT_NUMBER) {
                            throw new Exception("illegal use of put");
                        }
                        j = (int) tokens.nval;
                        if (tokens.nextToken() != StreamTokenizer.TT_NUMBER) {
                            throw new Exception("illegal use of put");

                        }
                        x = tokens.nval;
                        mat.put(i, j, x);
                        break;
                    case ("put2"):
                        if (tokens.nextToken() != StreamTokenizer.TT_NUMBER) {
                            throw new Exception("illegal use of put");
                        }
                        i = (int) tokens.nval;
                        if (tokens.nextToken() != StreamTokenizer.TT_NUMBER) {
                            throw new Exception("illegal use of put");
                        }
                        j = (int) tokens.nval;
                        if (tokens.nextToken() != StreamTokenizer.TT_NUMBER) {
                            throw new Exception("illegal use of put");

                        }
                        x = tokens.nval;
                        mat2.put(i, j, x);
                        break;
                    case ("print"):
                        System.out.println(mat.toString());
                        break;
                    case ("print2"):
                        System.out.println(mat2.toString());
                        break;
                    case ("get"):
                        if (tokens.nextToken() != StreamTokenizer.TT_NUMBER) {
                            throw new Exception("illegal use of put");
                        }
                        i = (int) tokens.nval;
                        if (tokens.nextToken() != StreamTokenizer.TT_NUMBER) {
                            throw new Exception("illegal use of put");
                        }
                        j = (int) tokens.nval;
                        System.out.println(mat.get(i, j));
                        break;
                    case ("mul"):
                        if (tokens.nextToken() != StreamTokenizer.TT_NUMBER) {
                            throw new Exception("illegal use of put");
                        }
                        i = (int) tokens.nval;
                        mat.multByConstant(i);
                        break;
                    case ("add"):
                        System.out.println(mat.add(mat2).toString());
                        break;

                    case ("del"):

                        break;
                    default:
                        throw new Exception();
                }
                curr = tokens.nextToken(); //token++

            }
            if (curr == StreamTokenizer.TT_NUMBER) {
                throw new Exception("illegal input");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
