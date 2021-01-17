package com.example.excel.Exc;


import au.com.bytecode.opencsv.CSVReader;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.*;

@Component
public class MapBuilder {

    // Метод для формирования отчета.
    public List<Object> getReport(String[] paths, String[] keys) throws Exception {
        List<Object> result = new ArrayList<>();
        result.add(deleteNullValuesInteger(FirstResult(paths, keys)));
        result.add(FirstResult(paths, keys));
        result.add(deleteNullValuesArrayList(ThirdResult(paths, keys)));
        System.out.println("\n" + "Итоговый сформированный отчет: " + result);
        return result;
    }
    // Метод воврощает HasMap с суммированными значениями.
    public Map<String, Integer> FirstResult(String[] paths, String[] keys) throws Exception {
        Map<String, Integer> resultCopy = new HashMap<>();
        Map<String, ArrayList<Integer>> result = ThirdResult(paths, keys);
        result.forEach((key, valueMap) -> {
            if (!valueMap.isEmpty()) {
                int sum = 0;
                for (Integer valueList : valueMap) {
                    sum += valueList;
                }
                resultCopy.put(key, sum);
            } else {
                resultCopy.put(key, null);
            }
        });
        System.out.println("\n" + "Массив с суммированными значениями: " + resultCopy);
        return resultCopy;
    }

    // Метод возврощвет HasMap с неотсортированными значениями.
    public Map<String, ArrayList<Integer>> ThirdResult(String[] paths, String[] keys) throws Exception {
        Map<String, ArrayList<Integer>> result = setMap(keys);
        for (String path : paths) {
            CSVReader reader = new CSVReader(new FileReader(path), ',', ' ', 1);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String str = nextLine[0].substring(0, 4).toLowerCase() + nextLine[0].substring(4, 6).toUpperCase();
                Integer count = Integer.parseInt(nextLine[1]);
                result.forEach((key, value) -> {
                    if (key.equals(str)) {
                        value.add(count);
                    }
                });
            }
        }
        System.out.println("\n" + "Массив с неотсортированными значениями: " + result);
        return sortArrayList(result);
    }

    // Метод для формирования HasMap с эталонными значениями.
    private Map<String, ArrayList<Integer>> setMap(String[] keys) {
        Map<String, ArrayList<Integer>> result = new HashMap<>();
        for (String key : keys) {
            result.put(key, new ArrayList<Integer>());
        }
        System.out.println("\n" + "Массив с эталонными значениями: " + result);
        return result;
    }

    // Метод для сортировки значений.
    private Map<String, ArrayList<Integer>> sortArrayList(Map<String, ArrayList<Integer>> result) {
        result.forEach((key, value) -> {
            Collections.sort(result.get((key)));
        });
        System.out.println("\n" + "Массив с отсортированными значениями: " + result);
        return result;
    }

    // Метод для удаления значений null.
    private Map<String, ArrayList<Integer>> deleteNullValuesArrayList(Map<String, ArrayList<Integer>> result) {
        Map<String, ArrayList<Integer>> resultCopy = new HashMap<>();
        result.forEach((key, value) -> {
            if (!result.get(key).isEmpty()) {
                resultCopy.put(key, value);
            }
        });
        System.out.println("\n" + "Массив без значений null: " + resultCopy);
        return resultCopy;
    }

    // Метод для удаления значений null.
    private Map<String, Integer> deleteNullValuesInteger(Map<String, Integer> result) {
        Map<String, Integer> resultCopy = new HashMap<>();
        result.forEach((key, value) -> {
            if (result.get(key) != null) {
                resultCopy.put(key, value);
            }
        });
        System.out.println("\n" + "Массив без значений null: " + resultCopy);
        return resultCopy;
    }
}