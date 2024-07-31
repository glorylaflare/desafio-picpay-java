package com.picpay.picpay_simplificado.dtos;

import com.picpay.picpay_simplificado.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName,
                      String lastName,
                      String document,
                      BigDecimal balance,
                      String email,
                      String password,
                      UserType userType) {
}
