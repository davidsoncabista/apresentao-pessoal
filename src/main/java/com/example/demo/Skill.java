package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Skill {
    @NotBlank
    private String name;

    @NotNull
    private Integer proficiency;

    private String category;
    private String logo;

    public Skill() {}

    public Skill(String name, Integer proficiency, String category, String logo) {
        this.name = name;
        this.proficiency = proficiency;
        this.category = category;
        this.logo = logo;
    }

    public String getName() { return name; }
    public Integer getProficiency() { return proficiency; }
    public String getCategory() { return category; }
    public String getLogo() { return logo; }

    public void setName(String name) { this.name = name; }
    public void setProficiency(Integer proficiency) { this.proficiency = proficiency; }
    public void setCategory(String category) { this.category = category; }
    public void setLogo(String logo) { this.logo = logo; }
}
