package dev.sch39.ecommerce.entities;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@SQLDelete(sql = "update users set is_deleted=true where id=?")
public class UserEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50, unique = true, nullable = false)
  private String username;

  @Column(length = 20, nullable = false)
  private String firstname;

  @Column(length = 20)
  private String lastname;

  @Column(name = "is_deleted")
  private boolean deleted = false;
}
