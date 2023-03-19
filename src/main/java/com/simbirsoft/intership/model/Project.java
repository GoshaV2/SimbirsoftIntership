package com.simbirsoft.intership.model;

import com.simbirsoft.intership.model.enumaration.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToOne
    @JoinColumn(name = "owner_id",nullable = false)
    private User owner;
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "project_status",nullable = false)
    private ProjectStatus projectStatus;
}
