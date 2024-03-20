package com.Banking.Banking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    @NotNull
    @JsonProperty("type")
    private String type;

    @NotNull
    @JsonProperty("balance")
    private int balance;

    @NotNull
    @JsonProperty("cl_fk")
    private int clientId;
}
