package com.sergio.binance.controller;

import com.sergio.binance.dto.GetTopChangeWithTimeDTO;
import com.sergio.binance.entity.BinanceResponse;
import com.sergio.binance.service.BinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/")
@Controller
public class RestController {
    @Autowired
    private BinanceService binanceService;

    @GetMapping("/all")
    public ResponseEntity<List<GetTopChangeWithTimeDTO>> getAll(){
        List<GetTopChangeWithTimeDTO> list = binanceService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
