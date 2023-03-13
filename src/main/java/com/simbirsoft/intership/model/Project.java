package com.simbirsoft.intership.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @ManyToMany
    @JoinTable(name = "project_member",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private List<User> members;
}
