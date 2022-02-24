package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.IngredientLine;

public interface IngredientLineRepo extends JpaRepository<IngredientLine, Integer> {

}
