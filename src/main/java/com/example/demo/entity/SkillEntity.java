package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "skill")
public class SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer proficiency;
    private String category;
    private String logo;

    public SkillEntity() {}

    public SkillEntity(String name, Integer proficiency, String category, String logo) {
        this.name = name;
        this.proficiency = proficiency;
        this.category = category;
        this.logo = logo;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getProficiency() { return proficiency; }
    public void setProficiency(Integer proficiency) { this.proficiency = proficiency; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }
}
