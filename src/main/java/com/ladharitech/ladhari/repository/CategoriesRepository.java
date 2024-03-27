package com.ladharitech.ladhari.repository;

import com.ladharitech.ladhari.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriesRepository extends JpaRepository<Categories, UUID> {
}
