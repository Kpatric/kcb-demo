package com.kcb.demo.service;

import com.kcb.demo.dto.BookRequest;
import com.kcb.demo.dto.BookResponse;

import java.util.List;

public interface BookService {
    BookResponse create(BookRequest request);
    List<BookResponse> findAll();
    BookResponse findById(Long id);
    BookResponse update(Long id, BookRequest request);
    void delete(Long id);
}

