package com.otta.library.pagination;

import java.util.Collection;


public interface Page<T extends Object> {
    long getCurrentPage();
    long getTotalPages();
    long getElementsInPage();
    String getNext();
    String getPrevious();
    Collection<T> getContent();
}
