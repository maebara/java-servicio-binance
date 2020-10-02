package com.sergio.binance.dto;

import com.sergio.binance.entity.Cryptocurrency;
import lombok.Data;

@Data
public class GetTopChangeDTO implements Comparable {
    private Cryptocurrency cryptocurrency;
    private double changePercent;

    @Override
    public int compareTo(Object o) {
        GetTopChangeDTO dto = (GetTopChangeDTO) o;
        int comp = Double.compare(dto.changePercent, this.changePercent);
        if(comp == 0){
            return dto.cryptocurrency.getSymbol().compareTo(this.cryptocurrency.getSymbol());
        }
        return comp;
    }
}
