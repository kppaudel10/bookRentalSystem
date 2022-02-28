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
@Table(name = "brs_category")
public class Category {
    @Id
    @GeneratedValue(generator = "category_sequence" ,strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "category_sequence" ,sequenceName = "category_sequence")
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

}
