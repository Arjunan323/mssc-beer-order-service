package com.arjunan.msscbeerorderservice.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerOrderPagedList<T> extends PageImpl<T> {

    public BeerOrderPagedList(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerOrderPagedList(List<T> content) {
        super(content);
    }
}
