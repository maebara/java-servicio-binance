package com.sergio.binance.dto;

import lombok.Data;

import java.util.TreeSet;

@Data
public class GetTopChangeWithTimeDTO {
    private TreeSet<GetTopChangeDTO> dto;
    private int id;
}
