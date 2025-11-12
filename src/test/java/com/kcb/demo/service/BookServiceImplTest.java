package com.kcb.demo.service;


import com.kcb.demo.domain.Book;
import com.kcb.demo.dto.BookRequest;
import com.kcb.demo.repository.BookRepository;
import com.kcb.demo.exception.NotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @Mock
    BookRepository repo;

    @InjectMocks
    BookServiceImpl service;

    @BeforeEach
    void setup(){ MockitoAnnotations.openMocks(this); }

    @Test
    void creates_book() {
        Book b = new Book(); b.setId(1L); b.setTitle("T"); b.setAuthor("A"); b.setYear(2020);
        when(repo.save(any())).thenReturn(b);
        var res = service.create(new BookRequest("T","A",2020));
        assertThat(res.id()).isEqualTo(1L);
    }

    @Test
    void find_by_id_not_found() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.findById(99L)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void update_book() {
        Book b = new Book(); b.setId(1L); b.setTitle("Old"); b.setAuthor("Old"); b.setYear(1990);
        when(repo.findById(1L)).thenReturn(Optional.of(b));
        var res = service.update(1L, new BookRequest("New","Auth",2001));
        assertThat(res.title()).isEqualTo("New");
    }
}
