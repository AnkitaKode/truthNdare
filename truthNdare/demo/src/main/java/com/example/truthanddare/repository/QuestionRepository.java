package com.example.truthanddare.repository;

import com.example.truthanddare.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM questions WHERE type = ?1 AND category = ?2 ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Question findRandomByTypeAndCategory(String type, String category);
}
