package com.shutl.controller;

import com.shutl.model.Quote;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/main")
public class QuoteController {

    @RequestMapping(value = "/quote", method = GET)
    public @ResponseBody Quote quote() {
        return new Quote("SW1A 1AA");
    }
}
