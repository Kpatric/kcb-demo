package com.kcb.demo.service;

import com.kcb.demo.domain.Book;
import com.kcb.demo.dto.BookRequest;
import com.kcb.demo.dto.BookResponse;
import com.kcb.demo.exception.NotFoundException;
import com.kcb.demo.mapper.BookMapper;
import com.kcb.demo.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository repo;
    public BookServiceImpl(BookRepository repo) { this.repo = repo; }

    @Override
    public BookResponse create(BookRequest request) {
        Book saved = repo.save(BookMapper.toEntity(request));
        return BookMapper.toResponse(saved);
    }
    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> findAll() {
        return repo.findAll().stream().map(BookMapper::toResponse).toList();
    }
    @Override
    @Transactional(readOnly = true)
    public BookResponse findById(Long id) {
        Book b = repo.findById(id).orElseThrow(() -> new NotFoundException("Book %d not found".formatted(id)));
        return BookMapper.toResponse(b);
    }
    @Override
    public BookResponse update(Long id, BookRequest request) {
        Book b = repo.findById(id).orElseThrow(() -> new NotFoundException("Book %d not found".formatted(id)));
        BookMapper.update(b, request);
        return BookMapper.toResponse(b);
    }
    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Book %d not found".formatted(id));
        repo.deleteById(id);
    }
}

