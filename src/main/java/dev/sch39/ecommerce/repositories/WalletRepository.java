package dev.sch39.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sch39.ecommerce.entities.WalletEntity;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Long> {

}
