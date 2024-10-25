package dev.sch39.ecommerce.database.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.sch39.ecommerce.database.factory.Factory;
import dev.sch39.ecommerce.entities.WalletEntity;
import dev.sch39.ecommerce.repositories.WalletRepository;

@Component
public class WalletFactoryImpl implements Factory {
  @Autowired
  private WalletRepository walletRepository;

  @Override
  public void run() {
    WalletEntity wallet1 = new WalletEntity();
    wallet1.setBalance(500_000.0);
    wallet1.setUserId(1L);
    walletRepository.save(wallet1);

    WalletEntity wallet2 = new WalletEntity();
    wallet2.setBalance(100_000.0);
    wallet2.setUserId(2L);
    walletRepository.save(wallet2);
  }

}
