package org.demo.clothes.api.repository;

import org.demo.clothes.api.model.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<TypeEntity,String> {

    public TypeEntity findByName(String name);
}
