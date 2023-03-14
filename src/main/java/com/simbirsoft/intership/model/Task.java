package com.simbirsoft.intership.model;

import jakarta.persistence.*;

@Entity(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title",nullable = false)
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = "task_status",nullable = false)
    private TaskStatus taskStatus;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @ManyToOne
    @JoinColumn(name = "performer_id")
    private User performer;
    @ManyToOne
    @JoinColumn(name = "realise_id")
    private Realise realise;
}
