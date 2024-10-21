package dev.sch39.ecommerce.entities;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import groovy.transform.EqualsAndHashCode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "products")
@SQLDelete(sql = "update products set is_deleted=true where id=?")
@FilterDef(name = "deleteProductFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deleteProductFilter", condition = "is_deleted = :isDeleted")
public class ProductEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50, nullable = false)
  private String name;

  @Column(length = 50, unique = true, nullable = false)
  private String slug;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "category_id", insertable = false, updatable = false)
  private CategoryEntity category;
  @Column(name = "category_id")
  private Long categoryId;

  @Column(name = "is_deleted")
  private boolean deleted;
}