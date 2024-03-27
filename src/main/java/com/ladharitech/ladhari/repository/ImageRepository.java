package com.ladharitech.ladhari.repository;

import com.ladharitech.ladhari.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Images, UUID> {
}
