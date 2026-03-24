package com.example.demo.repository;

import com.example.demo.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    // Busca os artigos ordenados pela coluna "ordem"
    List<ArticleEntity> findAllByOrderByOrderIndexAsc();
}