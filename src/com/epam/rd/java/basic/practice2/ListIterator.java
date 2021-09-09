package com.epam.rd.java.basic.practice2;

import java.util.Iterator;


interface ListIterator extends Iterator<Object> {

    boolean hasPrevious();


    Object previous();


    void set(Object e);

    @Override
    void remove();
}