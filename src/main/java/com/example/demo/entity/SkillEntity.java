package com.example.demo.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "skill")
public class SkillEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nameEn;
    private Integer proficiency;
    private String category;
    private String categoryEn;
    private String logo;

    public SkillEntity() {}

    public SkillEntity(String name, String nameEn, Integer proficiency, String category, String categoryEn, String logo) {
        this.name = name;
        this.nameEn = nameEn;
        this.proficiency = proficiency;
        this.category = category;
        this.categoryEn = categoryEn;
        this.logo = logo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNameEn() { return nameEn; }
    public void setNameEn(String nameEn) { this.nameEn = nameEn; }
    public Integer getProficiency() { return proficiency; }
    public void setProficiency(Integer proficiency) { this.proficiency = proficiency; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getCategoryEn() { return categoryEn; }
    public void setCategoryEn(String categoryEn) { this.categoryEn = categoryEn; }
    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }
}