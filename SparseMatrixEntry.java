package com.company;

import com.company.*;



public class SparseMatrixEntry {
    private double value;
    private int i;
    private int j;

    public SparseMatrixEntry(SparseMatrixEntry other){ this.value = other.value; this.i = other.i; this.j = other.j; }
    public SparseMatrixEntry(double val, int i, int j) { this.value = val; this.i = i; this.j = j; }
    public double getValue() { return value; }
    public void setValue (double value) { this.value = value; }
    public int getI() { return i; }
    public void setI( int i) { this.i = i; }
    public int getJ() { return j; }
    public void setJ (int j) { this.j = j; }
}
