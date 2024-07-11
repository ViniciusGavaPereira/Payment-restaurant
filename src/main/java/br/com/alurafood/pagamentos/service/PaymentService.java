package br.com.alurafood.pagamentos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;

import br.com.alurafood.pagamentos.dto.PaymentDto;
import br.com.alurafood.pagamentos.model.Payment;
import br.com.alurafood.pagamentos.model.Status;
import br.com.alurafood.pagamentos.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper; // Facilita a transferência de dados entre a entidade e o DTO

    // Retorna de forma paginada todos os objetos PAYMENT
    public Page<PaymentDto> findAll(Pageable pageable) {
        return paymentRepository
                .findAll(pageable)
                .map(p -> modelMapper.map(p, PaymentDto.class));
    }

    public PaymentDto findById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto createPayment(PaymentDto dto) {
        Payment payment = modelMapper.map(dto, Payment.class);
        payment.setStatus(Status.CREATED);
        paymentRepository.save(payment);

        return modelMapper.map(payment, PaymentDto.class);

    }

    public PaymentDto updatePayment(Long id, PaymentDto dto) {
        Payment payment = modelMapper.map(dto, Payment.class);
        payment.setId(id);
        payment = paymentRepository.save(payment);

        return modelMapper.map(payment, PaymentDto.class);

    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

}
