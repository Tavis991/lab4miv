package com.company;

public interface List <T>{
    void insert(T element) throws Exception;
    void remove() throws Exception;
    void replace(T newElement) ;
    void clear();
    boolean isEmpty();
    boolean gotoBeginning();
    boolean gotoEnd();
    boolean gotoNext();
    boolean gotoPrior();
    T getCursor();
}
