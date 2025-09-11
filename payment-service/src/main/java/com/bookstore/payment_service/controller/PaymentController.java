package com.bookstore.payment_service.controller;

import com.bookstore.payment_service.entity.Payment;
import com.bookstore.payment_service.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Create a new payment
    @PostMapping
    public Payment makePayment(@RequestBody Payment payment) {
        return paymentService.makePayment(payment);
    }

    // Get payment by ID
    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return paymentService.getPayment(id);
    }

    // Get all payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        Payment existing = paymentService.getPayment(id);
        if (existing != null) {
            existing.setAmount(payment.getAmount());
            existing.setMethod(payment.getMethod());
            existing.setStatus(payment.getStatus());
            return paymentService.makePayment(existing); // save updated payment
        }
        return null;
    }

    // Refund a payment
    @PutMapping("/{id}/refund")
    public Payment refundPayment(@PathVariable Long id) {
        return paymentService.refundPayment(id);
    }
}
