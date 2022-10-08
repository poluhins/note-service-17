package com.example.demo.db.repository;

import com.example.demo.db.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    @Query(
            value = "SELECT n " +
                    "FROM Note n " +
                    "WHERE n.author.id = :author_id"
    )
    Collection<Note> getAllByAuthorId(@Param("author_id") int authorId);

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
