package com.sergio.binance.service;

import com.sergio.binance.dto.GetTopChangeDTO;
import com.sergio.binance.entity.Cryptocurrency;
import com.sergio.binance.util.BoundedTreeSet;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SlopeCalculation{

    public TreeSet<GetTopChangeDTO> calculate(Map<String, Cryptocurrency> lista, Map<String, Cryptocurrency> novedades) {
        BoundedTreeSet<GetTopChangeDTO> list = new BoundedTreeSet<GetTopChangeDTO>(10);
        
        novedades.forEach((s, cryptocurrency) -> {
            Cryptocurrency money = lista.get(s);
            double priceX = Double.valueOf( money.getLastPrice());
            double priceY = Double.valueOf(cryptocurrency.getLastPrice());

            double slopePercent = ((priceY - priceX)/Math.abs(priceX)) * 100;
            if(Double.isNaN(slopePercent)){
                slopePercent = 0;
            }

            GetTopChangeDTO dto = new GetTopChangeDTO();
            dto.setChangePercent(slopePercent);
            dto.setCryptocurrency(cryptocurrency);

            list.add(dto);
            money.setVariation(cryptocurrency.getVariation());
            money.setLastPrice(cryptocurrency.getLastPrice());
            money.setPriceChangePercent(cryptocurrency.getPriceChangePercent());
            money.setPriceChange(cryptocurrency.getPriceChange());
        });

        return list.getTreeSet();
    }

}
