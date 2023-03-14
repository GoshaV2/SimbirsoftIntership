package com.simbirsoft.intership.model;

import com.simbirsoft.intership.model.Project;
import jakarta.persistence.*;

import java.sql.Date;

@Entity(name = "realises")
public class Realise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "project_id",nullable = false)
    private Project project;
    @JoinColumn(name = "date_start",nullable = false)
    private Date dateStart;
    @JoinColumn(name = "date_end",nullable = false)
    private Date dateEnd;
}
