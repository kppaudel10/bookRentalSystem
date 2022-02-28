package com.bookrent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brs_author", uniqueConstraints = {
        @UniqueConstraint(name = "unique_author_email", columnNames = "email"),
        @UniqueConstraint(name = "unique_author_contact", columnNames = "mobile_number")
})
public class Author {
    @Id
    @GeneratedValue(generator = "author_sequence" ,strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "author_sequence" ,sequenceName = "author_sequence")
    private Integer id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 10)
    private String mobile_number;

    @ManyToMany(mappedBy = "authorSet")
    Set<Book> bookSet ;
}
