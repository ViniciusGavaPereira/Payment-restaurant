package br.com.alurafood.pagamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alurafood.pagamentos.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
