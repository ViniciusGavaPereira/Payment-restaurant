package br.com.alurafood.pagamentos.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @NotNull
    @Positive
    private Long id;

    private Double price;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 19)
    private String number;

    @NotBlank
    @Size(max = 7)
    private String expire;

    @NotBlank
    @Size(max = 3, min = 3)
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING) // Para informar o sistema que o status é um enum
    private Status status;

    @NotNull
    private Long orderId;

    @NotNull
    private Long paymentMethodId;

    public Long getId() {
        return id;
    }

    public Payment(Long id, Double price, String name, String number, String expire, String code, Status status,
            Long orderId, Long paymentMethodId) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.number = number;
        this.expire = expire;
        this.code = code;
        this.status = status;
        this.orderId = orderId;
        this.paymentMethodId = paymentMethodId;
    }

    public Payment() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

}
