package com.example.demo.repository;

import com.example.demo.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    // Esse método faz o banco trazer a lista já ordenada pela coluna "ordem"
    List<ProjectEntity> findAllByOrderByOrderIndexAsc();
}