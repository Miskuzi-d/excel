package com.example.excel.controller;

import com.example.excel.Exc.MapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private MapBuilder mapBuilder;

    @Value(value = "${masiv.paths}")
    private String[] paths;

    @Value(value = "${keys_set}")
    private String[] keys;

    @GetMapping("/")
    public List<Object> result() throws Exception {
        return mapBuilder.getReport(paths, keys);
    }
}