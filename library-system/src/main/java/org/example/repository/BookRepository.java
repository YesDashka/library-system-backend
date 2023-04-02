package org.example.repository;

import org.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Transactional
    @Query("update Book set copiesAvailable = ?2 where id = ?1")
    void updateCopiesAvailable(long id, int copiesAvailable);

}
