package com.yusufdemircan.issuemanagement.entity;

import lombok.*;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "users", indexes = {@Index(name = "idx_username", columnList = "username")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", length = 100, unique = true)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "name_surname", length = 100)
    private String nameSurname;

    @Column(name = "email", length = 50)
    private String email;

    @JoinColumn(name = "assignee_user_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Issue> issues;


}
