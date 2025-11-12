package com.kcb.demo.controller;

import com.kcb.demo.dto.BookRequest;
import com.kcb.demo.dto.BookResponse;
import com.kcb.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;
    public BookController(BookService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse create(@Valid @RequestBody BookRequest request) { return service.create(request); }

    @GetMapping
    public List<BookResponse> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public BookResponse byId(@PathVariable Long id) { return service.findById(id); }

    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}

