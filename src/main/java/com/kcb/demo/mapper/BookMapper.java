package com.kcb.demo.mapper;


import com.kcb.demo.domain.Book;
import com.kcb.demo.dto.BookRequest;
import com.kcb.demo.dto.BookResponse;

public final class BookMapper {
    private BookMapper() {}
    public static Book toEntity(BookRequest r) {
        Book b = new Book();
        b.setTitle(r.title());
        b.setAuthor(r.author());
        b.setYear(r.year());
        return b;
    }
    public static void update(Book b, BookRequest r) {
        b.setTitle(r.title());
        b.setAuthor(r.author());
        b.setYear(r.year());
    }
    public static BookResponse toResponse(Book b) {
        return new BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getYear());
    }
}
