package com.example.excel.Exc;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;

class MapBuilderTest {

    private final String[] paths = {"src/main/resources/xml/source01.csv", "src/main/resources/xml/source02.csv", "src/main/resources/xml/source03.csv"};
    private final MapBuilder mapBuilder = new MapBuilder();

    @Test
    void firstResult() {

        Map<String, Integer> result = mapBuilder.FirstResult(paths);

        Map<String, Integer> expected= new HashMap<>();
        expected.put("markFV", 105);
        expected.put("mark01", 1550);
        expected.put("mark35", 1436);
        expected.put("markFT", 508);
        expected.put("mark17", 72);

        Assert.assertNotNull(result);
        Assert.assertThat(result, is(expected));
    }

    @Test
    void thirdTask() {

        Map<String, ArrayList<Integer>> result = mapBuilder.ThirdResult(paths);

        Map<String, ArrayList<Integer>> expected= new HashMap<>();

        expected.put("markFV", new ArrayList<Integer>());
        expected.get("markFV").add(1);
        expected.get("markFV").add(6);
        expected.get("markFV").add(8);
        expected.get("markFV").add(90);

        expected.put("mark01", new ArrayList<Integer>());
        expected.get("mark01").add(23);
        expected.get("mark01").add(144);
        expected.get("mark01").add(341);
        expected.get("mark01").add(501);
        expected.get("mark01").add(541);

        expected.put("mark35", new ArrayList<Integer>());
        expected.get("mark35").add(11);
        expected.get("mark35").add(14);
        expected.get("mark35").add(25);
        expected.get("mark35").add(30);
        expected.get("mark35").add(567);
        expected.get("mark35").add(789);

        expected.put("markFT", new ArrayList<Integer>());
        expected.get("markFT").add(45);
        expected.get("markFT").add(56);
        expected.get("markFT").add(98);
        expected.get("markFT").add(309);

        expected.put("mark17", new ArrayList<Integer>());
        expected.get("mark17").add(24);
        expected.get("mark17").add(24);
        expected.get("mark17").add(24);

        Assert.assertNotNull(result);
        Assert.assertThat(result, is(expected));
    }

    @Test
    void secondResult() {

        Map<String,  Integer> result = mapBuilder.SecondResult(paths);

        Map<String, Integer> expected= new HashMap<>();

        expected.put("markFV", 105);
        expected.put("mark01", 1550);
        expected.put("mark23", null);
        expected.put("mark35", 1436);
        expected.put("markFX", null);
        expected.put("markFT", 508);
        expected.put("mark17", 72);

        Assert.assertNotNull(result);
        Assert.assertThat(result, is(expected));
    }
}