package com.simbirsoft.intership.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/realises")
public class RealiseController {
    @PostMapping
    public ResponseEntity addRealise(){
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRealise(@PathVariable("id") Long id){
        return  ResponseEntity.noContent().build();
    }
}
