package com.mihai.project.library.controller;

import com.mihai.project.library.dto.rent.BookRentDTO;
import com.mihai.project.library.service.rent.BookRentService;
import com.mihai.project.library.service.stock.CopyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rent-book")
public class BookRentController {

    @Autowired
    private BookRentService bookRentService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerRentBook(@RequestBody BookRentDTO bookRentDTO){
        return null;
    }


}
