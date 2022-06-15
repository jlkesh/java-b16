package uz.jl.domains;

import jakarta.persistence.*;

@Entity(name = "University")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

}
