package com.example.demo.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

    @Column(name = "created_date_time")
    long createdDateTime;

    @Column(name = "edited_date_time")
    Long editedDateTime;

}
