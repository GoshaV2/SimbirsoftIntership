package com.simbirsoft.intership.model;

import com.simbirsoft.intership.model.enumaration.Permission;
import com.simbirsoft.intership.model.enumaration.Role;
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
    @JoinColumn(name = "project_id")
    private Project project;
    //todo delete this comment and delete fields from db
    /*@Enumerated(EnumType.STRING)
    @JoinColumn(name = "permission")
    private Permission permission;*/
    @ElementCollection(targetClass = Permission.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "permissions", joinColumns = @JoinColumn(name = "project_permission_id"))
    @Enumerated(EnumType.STRING)
    private Set<Permission> permissions;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
