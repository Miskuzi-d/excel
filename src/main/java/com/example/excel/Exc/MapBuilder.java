package com.example.excel.Exc;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.sun.tools.javac.util.StringUtils.toLowerCase;
import static com.sun.tools.javac.util.StringUtils.toUpperCase;

@Component()
public class MapBuilder {

    private Map<String, Integer> firstResult = new HashMap<>();
    private Map<String, Integer> secondResult = new HashMap<>();
    private Map<String, ArrayList<Integer>> thirdTask = new HashMap<>();

    // Получение массива без повторяющихся ключей.
    public Map<String, Integer> FirstResult(String[] paths) {
        firstResult = new HashMap<>();  //Создаю новый массив, чтобы не суммировал результаты от повторного реквеста
        for (String path : paths) {
            Map<String, Integer> map = new HashMap<>();
            try {
                CSVReader reader = new CSVReader(new FileReader(path), ',', ' ', 1);
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    String str = toLowerCase(nextLine[0].substring(0, 4)).concat(toUpperCase(nextLine[0].substring(4, 6)));
                    if (map.isEmpty() || !map.containsKey(str)) {
                        map.put(str, Integer.parseInt(nextLine[1]));
                    } else {
                        map.put(str, map.get(str) + Integer.parseInt(nextLine[1]));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\n" +"Сформированный массив без поатора значений: " + map);
            sumValues(map);
        }
        System.out.println("\n" +"Итоговый массив \"firstResult\": " + firstResult);
        return firstResult;
    }

    // Суммирование значений
    public void sumValues(Map<String, Integer> map) {
        map.forEach((key, value) -> {
            firstResult.merge(key, value, (a, b) -> b + firstResult.get(key));
        });
        System.out.println("Массив \"firstResult\" суммирования: " + firstResult);
    }

    // Получение массива с отсортированными значениями.
    public Map<String, ArrayList<Integer>> ThirdResult(String[] paths) {
        thirdTask = new HashMap<>();
        for (String path : paths) {
            Map<String, Integer> map = new HashMap<>();
            try {
                CSVReader reader = new CSVReader(new FileReader(path), ',', ' ', 1);
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    String str = toLowerCase(nextLine[0].substring(0, 4)).concat(toUpperCase(nextLine[0].substring(4, 6)));
                    int value = Integer.parseInt(nextLine[1]);
                    if (thirdTask.isEmpty() || !thirdTask.containsKey(str)) {
                        thirdTask.put(str, new ArrayList<>());
                        thirdTask.get(str).add(value);
                    } else {
                        thirdTask.get(str).add(value);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n" +"Массив \"thirdTask\" до сортировки: " + thirdTask);
        thirdTask.forEach((key, value) -> {
            Collections.sort(thirdTask.get(key));
        });
        System.out.println("Массив \"thirdTask\" после сортировки : " + thirdTask);
        return thirdTask;
    }

    // Зря я начал делать второе задание последним (до меня слишком поздно дошло, что я все усложнил)
    public Map<String, Integer> SecondResult(String[] paths) {
        secondResult = new HashMap<>();
        FirstResult(paths).forEach((key, value) ->{
            secondResult.computeIfAbsent(key, v -> value);
        });
        secondResult.put("mark23", null);
        secondResult.put("markFX", null);
        System.out.println("Массив \"secondResult\" : " + secondResult);
        return secondResult;
    }
}