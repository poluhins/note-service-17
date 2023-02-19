package com.example.demo.repository;

import com.example.demo.domain.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    Collection<Note> getAllByAuthorId(int authorId);

    @Query(
            value = "SELECT count(n) " +
                    "FROM Note n " +
                    "WHERE n.author.id = :author_id"
    )
    int getCountByAuthorId(@Param("author_id") int authorId);

    @Query(
            value = "SELECT CASE " +
                    "WHEN COUNT(n) > 0" +
                    "THEN true " +
                    "ELSE false " +
                    "END " +
                    "FROM Note n " +
                    "WHERE n.id = :note_id " +
                    "AND n.author.id = :author_id"
    )
    boolean isAuthor(@Param("author_id") int authorId, @Param("note_id") int noteId);
}
