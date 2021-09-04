package com.ironhack.rest.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Course {
    @Id
    @Column(name = "course_code")
    private String code;

    @Column(name = "course_name")
    private String name;
}
