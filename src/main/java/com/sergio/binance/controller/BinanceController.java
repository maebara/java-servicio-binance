package com.sergio.binance.controller;

import com.sergio.binance.dto.GetTopChangeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class BinanceController {

    @MessageMapping("/user/5Min")
    @SendTo("/topic/user/5Min")
    public ResponseEntity<GetTopChangeDTO> getUser5Min(GetTopChangeDTO dto){
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @MessageMapping("/user/10Min")
    @SendTo("/topic/user/10Min")
    public ResponseEntity<GetTopChangeDTO> getUser10Min(GetTopChangeDTO dto){
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @MessageMapping("/user/30Min")
    @SendTo("/topic/user/30Min")
    public ResponseEntity<GetTopChangeDTO> getUser30Min(GetTopChangeDTO dto){
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @MessageMapping("/user/1Hr")
    @SendTo("/topic/user/1Hr")
    public ResponseEntity<GetTopChangeDTO> getUser1Hr(GetTopChangeDTO dto){
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @MessageMapping("/user/3Hr")
    @SendTo("/topic/user/3Hr")
    public ResponseEntity<GetTopChangeDTO> getUser3Hr(GetTopChangeDTO dto){
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @MessageMapping("/user/5Hr")
    @SendTo("/topic/user/5Hr")
    public ResponseEntity<GetTopChangeDTO> getUser5Hr(GetTopChangeDTO dto){
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @MessageMapping("/user/12Hr")
    @SendTo("/topic/user/12Hr")
    public ResponseEntity<GetTopChangeDTO> getUser12Hr(GetTopChangeDTO dto){
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @MessageMapping("/user/24Hr")
    @SendTo("/topic/user/24Hr")
    public ResponseEntity<GetTopChangeDTO> getUser24Hr(GetTopChangeDTO dto){
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
