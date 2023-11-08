package com.example.test_zad;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.Map;
import java.util.HashMap;





@RestController
public class FrequencyController {

    @Operation(summary = "Подсчет частоты символов в строке", description = "Вычисляет частоту символов в данном сообщении.")
    @PostMapping("/calculateFrequency")
    public ResponseEntity<?> calculateFrequency(
            @Parameter(description = "Сообщение для анализа") @RequestBody(required = false) String message) {
        if (message == null || message.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Вы не ввели сообщение.");
            return ResponseEntity.badRequest().body(response);
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

        return ResponseEntity.ok(sortedFrequencyMap);
    }
}
