package com.biblio.microservicedocument.dao;

import com.biblio.microservicedocument.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface DocumentDAO extends JpaRepository<Document, Long>, QuerydslPredicateExecutor<Document> {

    List<Document> findAll();

    Page<Document> findAll(Pageable pageable);

}
