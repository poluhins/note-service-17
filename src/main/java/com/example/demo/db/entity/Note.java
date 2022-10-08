package com.example.demo.db.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Table(name = "notes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notes_seq")
    @SequenceGenerator(name = "notes_seq", sequenceName = "notes_sequence", allocationSize = 1)
    int id;
    String text;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    User author;
    long createdDateTime;
    Long editedDateTime;

}
