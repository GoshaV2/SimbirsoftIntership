package com.simbirsoft.intership.model;

import jakarta.persistence.*;

@Entity(name = "project_permissions")
public class ProjectPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "permission")
    private Permission permission;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
