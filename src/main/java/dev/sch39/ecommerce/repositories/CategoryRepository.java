package dev.sch39.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sch39.ecommerce.entities.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
  @Query(value = "select * from categories where name=?1or description=?1", nativeQuery = true)
  List<CategoryEntity> searchByText(String search);
}
