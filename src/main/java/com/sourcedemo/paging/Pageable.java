package com.sourcedemo.paging;

import com.sourcedemo.sort.Sorter;

public interface Pageable {
    Integer getPage(); // get current page.
    Integer getOffset();
    Integer getLimit();
    Sorter getSorter();
}
