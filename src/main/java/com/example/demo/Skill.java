package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public class Skill implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    
    @NotBlank
    private String name;
    private String nameEn;

    @NotNull
    private Integer proficiency;

    private String category;
    private String categoryEn;
    private String logo;

    public Skill() {}

    public Skill(Long id, String name, String nameEn, Integer proficiency, String category, String categoryEn, String logo) {
        this.id = id;
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