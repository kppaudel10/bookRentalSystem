package com.bookrent.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "brs_book")
public class Book {
    @Id
    @GeneratedValue(generator = "book_sequence",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "book_sequence",sequenceName = "book_sequence")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private Date published_date;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private Integer numberOfPage;

    @Column(nullable = false)
    private Integer stockCount;

    @Column(nullable = false)
    private String bookCode;

    @Column(name = "file_path")
    private String coverPhotoPath;
    @ManyToMany
    @JoinTable(
            name = "brs_book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
   private List<Author> authorSet;

    @OneToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id" ,foreignKey = @ForeignKey(name = "FK_Book_Category"))
   private Category category;

}
