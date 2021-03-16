package org.demo.clothes.api.repository;

import org.demo.clothes.api.model.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandEntity,String> {

    Optional<BrandEntity> findByName(String name);
}
