package br.com.alurafood.pagamentos.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;
import br.com.alurafood.pagamentos.dto.PaymentDto;
import br.com.alurafood.pagamentos.service.PaymentService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/all")
    public Page<PaymentDto> list(@PageableDefault(size = 10) Pageable pageable) {
        System.out.println("ENTROUENTROUENTROUENTROUENTROUENTROUENTROU ");
        return paymentService.findAll(pageable);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> findById(@PathVariable @NotNull Long Id) {
        PaymentDto dto = paymentService.findById(Id);

        return ResponseEntity.ok(dto);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PaymentDto> cadastrar(@RequestBody @Valid PaymentDto dto, UriComponentsBuilder uriBuilder) {
        PaymentDto payment = paymentService.createPayment(dto);
        URI address = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();

        return ResponseEntity.created(address).body(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> atualizar(@PathVariable @NotNull Long id,
            @RequestBody @Valid PaymentDto dto) {
        PaymentDto atualizado = paymentService.updatePayment(id, dto);
        return ResponseEntity.ok(atualizado);
    }

}
