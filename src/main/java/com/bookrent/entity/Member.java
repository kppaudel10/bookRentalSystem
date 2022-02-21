//package com.bookrent.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "brs_member",uniqueConstraints = {
//        @UniqueConstraint(name = "Unique_Member_email", columnNames = "email"),
//                @UniqueConstraint(name = "Unique_Member_mobile", columnNames = "mobile")
//})
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@Builder
//public class Member implements Serializable {
//    @Id
//    @GeneratedValue(generator = "member_sequence" ,strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "member_sequence" ,sequenceName = "member_sequence")
//    private Integer id;
//    private String  full_name;
//
//    @Column(name = "email_address" ,length = 80)
//    private String email;
//
//    @Column(name = "contact_number" ,length = 10)
//    private String mobile_no;
//    private String address;
//
//    private String filePath;
//}
