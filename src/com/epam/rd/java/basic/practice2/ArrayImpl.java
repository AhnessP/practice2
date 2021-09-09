package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    private int modCount;

    private Object[] arr = {};

    @Override
    public int size() {

        return toArray().length;

    }

    @Override
    public Iterator<Object> iterator() {

        return new IteratorImpl();

    }

    @Override
    public void add(Object element) {
        Object[] arr2 = new Object[arr.length + 1];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        arr2[arr2.length - 1] = element;
        arr = arr2;
        modCount++;
    }

    @Override
    public void set(int index, Object element) {

        arr[index] = element;

    }

    @Override
    public Object get(int index) {

        return arr[index];

    }

    @Override
    public int indexOf(Object element) {
        int returnValue = -1;
        for (int i = 0; i < arr.length; ++i) {
            if (element == arr[i]) {
                returnValue = i;
                break;
            }
        }
        return returnValue;
    }

    @Override
    public void remove(int index) {
        Object[] arr2 = new Object[arr.length - 1];
        System.arraycopy(arr, 0, arr2, 0, index);
        System.arraycopy(arr, index + 1, arr2, index, arr2.length - index);
        arr = arr2;
    }

    @Override
    public void clear() {

        arr = new Object[] {};
        modCount = 0;
    }

    @Override
    public Object[] toArray() {

        return this.arr.clone();

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < toArray().length; i++) {
            if (toArray()[i] == null) {
                sb.append("[" + "null" + "]");
                continue;
            }
            sb.append("" + toArray()[i].toString() + "");
            if (i != size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();

    }

    private class IteratorImpl implements Iterator<Object> {

        private int currentIndex;

        private int pointer = -1;

        private boolean nextOrPrevious;

        private boolean flag = true;

        public int getPointer() {
            return pointer;
        }

        public void setPointer(int pointer) {
            this.pointer = pointer;
        }

        public boolean isNextOrPrevious() {
            return nextOrPrevious;
        }

        public void setNextOrPrevious(boolean nextOrPrevious) {
            this.nextOrPrevious = nextOrPrevious;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        @Override
        public boolean hasNext() {

            return (pointer < toArray().length - 1);

        }

        @Override
        public Object next() {
            if (pointer >= size()) {
                throw new NoSuchElementException("Error No Such Element from next");
            }
            flag = false;
            nextOrPrevious = false;
            return toArray()[++pointer];

        }

        @Override
        public void remove() {

            if (pointer < 0) {
                throw new IllegalStateException("remove: Not does not indicate which item");
            }
            ArrayImpl.this.remove(pointer);
            if (pointer < currentIndex) {
                currentIndex--;
            }
            pointer = -1;
        }

    }

    public static void main(String[] args) {

        Array con = new ArrayImpl();

        con.add("A");
        con.add("B");
        con.add("C");
        System.out.println(con);



        con.set(1, "d");
        System.out.println(con);

        con.set(0, "b");
        System.out.println(con);

        con.size();


        con.set(0, "C");


        con.get(1);
        con.add(new Object());

        con.remove(0);
        System.out.println(con + "remove");


        System.out.println("Show con: " + con.toString());


        String str = "rere";
        con.add(str);
        System.out.println("Show con: " + con);


        con.clear();

        Array con1 = new ArrayImpl();
        con1.add("A");
        con1.add("B");
        con1.add("C");


        con1.remove(0);
        con1.remove(con1.size() - 1);


        str += "2316";
        con1.add(str);
        System.out.println("\ncon: " + con);
        System.out.println("con1: " + con1);

        System.out.println("\nShow foreach iterator:");

        for (Object o : con) {
            System.out.print(o + " ");
        }

        System.out.println("Show while iterator: ");
        Iterator<Object> it = con.iterator();
        // while iterator
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }

        System.out.println("\nShow " + con1);

        System.out.println("\nShow remove iterator \n: " + con1);
        Iterator<Object> it1 = con1.iterator();
        while (it1.hasNext()) {
            it1.next();
        }

    }

}