package com.example.Backend.domain.recruitments.repositorties.applications;

import com.example.Backend.domain.recruitments.entities.applications.Option;
import com.example.Backend.domain.recruitments.entities.applications.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findByQuestion(Question question);
}
