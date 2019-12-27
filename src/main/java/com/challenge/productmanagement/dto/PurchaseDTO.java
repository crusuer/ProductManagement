package com.challenge.productmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseDTO {
    private String buyerEmail;
    private List<Long> productsIds;
}
