package com.company;

import java.util.Arrays;

public class  SparseMatrix implements Matrix  {
    private SLinkedList<SparseMatrixEntry> Linky;
    private int size;
    private double def_val;
    private boolean trans;
    private int scal;

//     public SparseMatrix(SparseMatrix other){
//         SparseMatrix fresh = new SparseMatrix(other.size, other.def_val);
//         Linky = new SLinkedList<>(other.Linky);
//     }
    public SparseMatrix(int size, double def_val){
        scal=1;
        Linky = new SLinkedList<SparseMatrixEntry>();
        this.size=size;
        this.def_val=def_val;
        trans=false;
    }
    public int getSize() {
        return size;
    }

    @Override
    public double get(int i, int j) {
        if(trans){ int temp=i; i=j; j=temp;}
        if ((i > size-1) || (i < 0)  || (j > size - 1) || (j < 0)){ //checking bounds
            System.err.println("out of bounds" ); return 0;
        }
        if (Linky.gotoBeginning()) {  //iterating Linked list
            do {
                if (Linky.getCursor().getI() == i && Linky.getCursor().getJ() == j)
                    return Linky.getCursor().getValue() * scal;
            }
            while (Linky.gotoNext());
        }
        return def_val*scal;
    }

    @Override
    public void put(int i, int j, double x) {
        if (trans) { int temp=i; i=j; j=temp;} //trans effect
        if ( x == get(i,j)) { return;}  // get already checking bounds
        if(!Linky.isEmpty()) {   //so not to increase LL size with every put, we check and replace instead of just appending
            do {
                if (Linky.getCursor().getI() == i && Linky.getCursor().getJ() == j) {
                    if (x == def_val) Linky.remove(); return;  // if value is just def value we simply delete entry
                    else { Linky.replace(new SparseMatrixEntry(x, i, j));return;  } //inserting new node
                }
            }
            while (Linky.gotoNext());
        }


        Linky.gotoBeginning();
        if (Linky.isEmpty()) { Linky.insert (new SparseMatrixEntry(x/scal, i ,j)); return;}
        do   {if (Linky.getCursor().getI() == i && Linky.getCursor().getJ() == j)
         { Linky.getCursor().setValue(x/scal);  return; }
        }
        while (Linky.gotoNext()) ;

        Linky.insert (new SparseMatrixEntry(x/scal, i ,j));
    }

    @Override
    public void transpose() {
        trans=!trans;  //trans flag
    }

    @Override
    public void multByConstant(int C)  {
        if ( C==0)  { System.err.println("no mul by zero" ); return ; } //constant cannot be zero
        else scal*=C;
    }

    @Override
    public String toString() {
        double temp [][] = new double [size][size];  // creating template sparse matrix def value filled
        for (double[] row: temp)  Arrays.fill (row, def_val);
        if(Linky.gotoBeginning()) {  //iterating over non-def values
            do {
                if (trans) { temp [Linky.getCursor().getJ()] [Linky.getCursor().getI()] = Linky.getCursor().getValue();} //checking trans flag
                 else temp [Linky.getCursor().getI()] [Linky.getCursor().getJ()] = Linky.getCursor().getValue();
            }
            while (Linky.gotoNext());
        }
        StringBuilder bild = new StringBuilder();
        for (double[] row: temp) {
            bild.append("\n");
            for (double el : row) { bild.append(el*scal); bild.append(" "); } //times scalar
        }
        return bild.toString();
    }

   
    SparseMatrix add (SparseMatrix other) throws Exception {
        if (this.size != other.size) throw new Exception("dimensional error");
        SparseMatrix fresh = SparseMatrix();
        int i, j, k, l;
        Linky.gotoBeginning()
        do {
            i = Linky.getCursor().getI();     j = Linky.getCursor().getJ();
            fresh.put( i, j, (get(i, j) + other.get(i, j));
            }
            while (Linky.gotoNext());
        other.Linky.gotoBeginning()
        do {
            i = other.Linky.getCursor().getI();     j = other.Linky.getCursor().getJ();
            fresh.put( i, j, (other.get(i, j) + get(i, j));
            }
            while (other.Linky.gotoNext());
         fresh.def_val += def_val;   fresh.scal *= scal;
         return fresh;
      }
                  
                      
      SparseMatrix sub (SparseMatrix other) throws Exception {
          other.scal *= -1;
          SparseMatrix temp= add(other);
          other.scal *= -1;
          return temp;
      }
            
            
            
//        if (!Linky.isEmpty()) {   //so not to increase LL size with every put, we check and replace instead of just appending
//            do {
//                i = fresh.Linky.getCursor().getI();     j = fresh.Linky.getCursor().getJ();
//                if (trans) {  int temp = i; i = j; j = temp; }
//
//                fresh.put( i, j, (temp + Linky.getCursor().getValue()), true);
//            }
//             }
//        do {
//            if (!Linky.isEmpty() && (!fresh.Linky.isEmpty())) {   //so not to increase LL size with every put, we check and replace instead of just appending
//                do {
//                    i = fresh.Linky.getCursor().getI();
//                    j = fresh.Linky.getCursor().getJ();
//                    if (trans) { int temp = i;   i = j;  j = temp; }
//                    if (other.trans) { int temp = i; i = j;  j = temp; }
//                    if (Linky.getCursor().getI() == i && Linky.getCursor().getJ() == j) {
//                        double temp = fresh.Linky.getCursor().getValue();
//                        fresh.Linky.getCursor().setValue(temp + Linky.getCursor().getValue());
//                        found = true;
//                        break;
//                    }
//                }
//                while (Linky.gotoNext());
//            }
//            if (!found) {
//                fresh.Linky.getCursor().setValue(fresh.Linky.getCursor().getValue() + def_val);
//            }
//        }
//         while(fresh.Linky.gotoNext());
//         fresh.def_val += def_val;   fresh.scal *= scal;
//         return fresh;
//         }

}
