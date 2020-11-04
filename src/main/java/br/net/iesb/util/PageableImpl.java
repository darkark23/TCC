package br.net.iesb.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableImpl implements Pageable {

    private int pageNumber;
    private int pageSize;
    private String termo;
    private Sort sort;

    @Override
    public int getPageNumber() {
        return pageSize;
    }

    @Override
    public int getPageSize() {
        return pageNumber;
    }

    @Override
    public long getOffset() {
        return 0;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public String getTermo() {
        return termo;
    }

    public void setTermo(String termo) {
        this.termo = termo;
    }
}
