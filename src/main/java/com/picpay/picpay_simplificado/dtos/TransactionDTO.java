package com.picpay.picpay_simplificado.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value,
                             Long senderId,
                             Long receiverId) {
}
