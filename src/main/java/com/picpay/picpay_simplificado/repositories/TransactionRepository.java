package com.picpay.picpay_simplificado.repositories;

import com.picpay.picpay_simplificado.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
