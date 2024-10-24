package dev.sch39.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sch39.ecommerce.entities.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
  @Query(value = "select * from categories where name=?1or description=?1", nativeQuery = true)
  List<CategoryEntity> searchByText(String search);

  @Query(value = "select * from categories where slug=?1 and is_deleted=false", nativeQuery = true)
  Optional<CategoryEntity> findBySlug(String slug);

  @Query(value = "update categories set is_deleted=true where slug=?1 and is_deleted=false", nativeQuery = true)
  void deleteBySlug(String slug);

  @Query(value = "select * from categories where is_deleted=false", nativeQuery = true)
  Page<CategoryEntity> findAllNotDeleted(Pageable pageable);

  @Query(value = "select * from categories where is_deleted=true", nativeQuery = true)
  Page<CategoryEntity> findAllDeleted(Pageable pageable);
}
