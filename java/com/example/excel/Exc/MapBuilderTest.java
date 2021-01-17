package com.example.excel.Exc;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;

@TestComponent
class MapBuilderTest {

    private String[] paths = {"src/main/resources/xml/source01.csv", "src/main/resources/xml/source02.csv", "src/main/resources/xml/source03.csv"};

    private String[] keys = {"markFV", "mark01", "mark23", "mark35", "markFX", "markFT", "mark17"};


    @Test
    void getReport() {
    }

    @Test
    void firstResult() throws Exception {
        MapBuilder mapBuilder = new MapBuilder();

        Map<String, Integer> expected = new HashMap<>();

        expected.put("markFV", 105);
        expected.put("mark01", 1550);
        expected.put("mark23", null);
        expected.put("mark35", 1436);
        expected.put("markFX", null);
        expected.put("markFT", 508);
        expected.put("mark17", 72);

        Map<String, Integer> result = mapBuilder.FirstResult(paths, keys);

        Assert.assertNotNull(result);
        Assert.assertThat(result, is(expected));
    }

    @Test
    void thirdResult() throws Exception {
        MapBuilder mapBuilder = new MapBuilder();

        Map<String, ArrayList<Integer>> expected = new HashMap<>();

        Map<String, ArrayList<Integer>> result = mapBuilder.ThirdResult(paths, keys);

        expected.put("markFV", new ArrayList<>());
        expected.get("markFV").add(1);
        expected.get("markFV").add(6);
        expected.get("markFV").add(8);
        expected.get("markFV").add(90);

        expected.put("mark01", new ArrayList<>());
        expected.get("mark01").add(23);
        expected.get("mark01").add(144);
        expected.get("mark01").add(341);
        expected.get("mark01").add(501);
        expected.get("mark01").add(541);

        expected.put("mark23", new ArrayList<>());

        expected.put("mark35", new ArrayList<>());
        expected.get("mark35").add(11);
        expected.get("mark35").add(14);
        expected.get("mark35").add(25);
        expected.get("mark35").add(30);
        expected.get("mark35").add(567);
        expected.get("mark35").add(789);

        expected.put("markFX", new ArrayList<>());

        expected.put("markFT", new ArrayList<>());
        expected.get("markFT").add(45);
        expected.get("markFT").add(56);
        expected.get("markFT").add(98);
        expected.get("markFT").add(309);

        expected.put("mark17", new ArrayList<>());
        expected.get("mark17").add(24);
        expected.get("mark17").add(24);
        expected.get("mark17").add(24);

        Assert.assertNotNull(result);
        Assert.assertThat(result, is(expected));
    }
}