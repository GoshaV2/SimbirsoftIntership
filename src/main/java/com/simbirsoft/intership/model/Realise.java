package com.simbirsoft.intership.model;

import com.simbirsoft.intership.model.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "realises")
@Entity
public class Realise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    @JoinColumn(name = "date_start", nullable = false)
    private Date dateStart;
    @JoinColumn(name = "date_end", nullable = false)
    private Date dateEnd;
}
