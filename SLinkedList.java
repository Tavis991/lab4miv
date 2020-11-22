package com.company;

public class SLinkedList<T> implements List<T> {
    private SNode<T> head;
    private SNode<T> curr;

    public SLinkedList(){
        head=null;
        curr=null;
    }

    public SLinkedList(SLinkedList other){
        SNode<T> temp;
        curr=null;
        if(!other.gotoBeginning()) return;
        SparseMatrixEntry element = new SparseMatrixEntry((SparseMatrixEntry) other.head.getElement());
        temp=new SNode<T>((T)element, null);
        head=temp;
        gotoBeginning();
        do {
            element = new SparseMatrixEntry((SparseMatrixEntry) other.curr.getElement());
            temp = new SNode<>((T) element, null);
            curr.setNext(temp);
        }
        while(gotoNext() && other.gotoNext());
    }
    @Override
    public void insert(T element)  {
        if(element== null)  { System.err.println("null exception" ); return ; }
        SNode<T> temp;
        if(isEmpty()){
            temp= new SNode<>(element, null);
            head=temp;
        }
        else{
            temp=new SNode<>(element, curr.getNext());
            curr.setNext(temp);
        }
        curr=temp;
    }

    @Override
    public void remove() {
        if(isEmpty()) { System.err.println("out of bounds" ); return ; }
        SNode<T> temp= curr;
        gotoPrior();
        curr.setNext(temp.getNext());
        gotoNext();
        if (getCursor() == null) gotoBeginning();
    }

    @Override
    public void replace(T newElement){
        if(isEmpty()) { System.err.println("out of bounds" ); return ; }
        if(newElement==null) { System.err.println("null exception" ); return ; }
        curr.setElement(newElement);
    }

    @Override
    public void clear() {
        head=null;
        curr=null;
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder temp= new StringBuilder();
        SNode<T> curr1= curr;
        gotoBeginning();
        temp.append("[");
        temp.append(getCursor().toString());
        while(gotoNext()){
            temp.append(", ");
            temp.append(getCursor().toString());
        }
        temp.append("]");
        curr=curr1;
        return temp.toString();
    }

    @Override
    public boolean gotoBeginning() {
        if(!isEmpty()){
            curr=head;
            return true;
        }
        return false;
    }

    @Override
    public boolean gotoEnd() {
        if(isEmpty()) return false;
        while(curr.getNext()!=null){
            curr=curr.getNext();
        }
        return true;
    }

    @Override
    public boolean gotoNext() {
        if(isEmpty() || curr.getNext()==null) return false;
        curr=curr.getNext();
        return true;
    }

    @Override
    public boolean gotoPrior() {
        if(isEmpty() || curr==head) return false;
        SNode<T> temp= curr;
        gotoBeginning();
        while(curr.getNext() != temp){
            curr=curr.getNext();
        }
        return true;
    }

    @Override
    public T getCursor() {
        return curr.getElement();
    }
}
