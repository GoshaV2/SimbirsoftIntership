package com.simbirsoft.intership.model;

import com.simbirsoft.intership.model.enumaration.Permission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_permissions")
@Entity
public class ProjectPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    @ElementCollection(targetClass = Permission.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "permissions", joinColumns = @JoinColumn(name = "project_permission_id", nullable = false))
    @Enumerated(EnumType.STRING)
    private Set<Permission> permissions;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
