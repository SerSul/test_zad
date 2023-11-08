package com.example.test_zad;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.Map;
import java.util.HashMap;


@RestController
public class FrequencyController {

    @GetMapping("/calculateFrequency/{message}")
    public Map<Character, Integer> calculateFrequency(@PathVariable String message) {
        if (message == null) {
            message = "";
        }

        char[] characters = message.toCharArray();

        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : characters) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> sortedFrequencyMap = new LinkedHashMap<>();

        frequencyMap.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .forEach(entry -> sortedFrequencyMap.put(entry.getKey(), entry.getValue()));

        return sortedFrequencyMap;
    }

}

