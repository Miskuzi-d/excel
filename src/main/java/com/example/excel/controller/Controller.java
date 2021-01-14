package com.example.excel.controller;

import com.example.excel.Exc.MapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    private MapBuilder mapBuilder;

    @Value(value = "${masiv.paths}")
    private String[] paths;

    @GetMapping("/firstTask")
    public Map<String, Integer> firstResult(){
           return mapBuilder.FirstResult(paths);
    }

    @GetMapping("/secondTask")
    public Map<String, Integer> secondResult(){
        return mapBuilder.SecondResult(paths);
    }

    @GetMapping("/thirdTask")
    public Map<String, ArrayList<Integer>> thirdTask(){
            return mapBuilder.ThirdResult(paths);
    }
}
