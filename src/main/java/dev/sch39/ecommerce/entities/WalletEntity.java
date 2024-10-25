package dev.sch39.ecommerce.entities;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "wallets")
@SQLDelete(sql = "update wallets set is_deleted=true where id=?")
public class WalletEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double balance;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private UserEntity user;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "is_deleted")
  private boolean deleted = false;
}
