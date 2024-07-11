package br.com.alurafood.pagamentos.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

@Controller
@RestController()
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public Page<PaymentDto> list(@PageableDefault(size = 10) Pageable pageable) {
        return paymentService.findAll(pageable);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> findById(@PathVariable @NotNull Long Id) {
        PaymentDto dto = paymentService.findById(Id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PaymentDto> cadastrar(@RequestBody @Valid PaymentDto dto, UriComponentsBuilder uriBuilder) {
        PaymentDto pagamento = paymentService.createPayment(dto);
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();

        return ResponseEntity.created(endereco).body(pagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> atualizar(@PathVariable @NotNull Long id,
            @RequestBody @Valid PaymentDto dto) {
        PaymentDto atualizado = paymentService.updatePayment(id, dto);
        return ResponseEntity.ok(atualizado);
    }

}
