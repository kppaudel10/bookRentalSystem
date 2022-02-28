package com.bookrent.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "brs_member" ,uniqueConstraints = {
        @UniqueConstraint(name = "unique_member_email",columnNames = "email_address"),
        @UniqueConstraint(name = "unique_member_contact",columnNames = "contact_number")
})
public class Member implements Serializable {
    @Id
    @GeneratedValue(generator = "member_sequence" ,strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "member_sequence" ,sequenceName = "member_sequence")
    private Integer id;

    @Column(nullable = false)
    private String  full_name;

    @Column(name = "email_address" ,length = 80,nullable = false)
    private String email;

    @Column(name = "contact_number" ,length = 10,nullable = false)
    private String mobile_no;
    private String address;

}
