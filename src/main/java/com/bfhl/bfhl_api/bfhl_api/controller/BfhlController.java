package com.bfhl.bfhl_api.bfhl_api.controller;

import com.bfhl.bfhl_api.bfhl_api.model.DataRequest;
import com.bfhl.bfhl_api.bfhl_api.model.DataResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*")
public class BfhlController {

    private static final String FULL_NAME = "john_doe";
    private static final String DOB = "17091999";
    private static final String EMAIL = "john@xyz.com";
    private static final String ROLL_NUMBER = "ABCD123";

    @PostMapping
    public DataResponse processData(@RequestBody DataRequest request) {
        DataResponse response = new DataResponse();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialChars = new ArrayList<>();
        StringBuilder alphaConcat = new StringBuilder();
        int sum = 0;

        for (String item : request.getData()) {
            if (item.matches("\\d+")) {
                int num = Integer.parseInt(item);
                sum += num;
                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (item.matches("[a-zA-Z]+")) {
                alphabets.add(item.toUpperCase());
                alphaConcat.append(item);
            } else {
                specialChars.add(item);
            }
        }

        // Reverse and alternate caps
        StringBuilder concatStr = new StringBuilder();
        String reversed = alphaConcat.reverse().toString();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            concatStr.append(i % 2 == 0 ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }

        response.setIs_success(true);
        response.setUser_id(FULL_NAME + "_" + DOB);
        response.setEmail(EMAIL);
        response.setRoll_number(ROLL_NUMBER);
        response.setOdd_numbers(oddNumbers);
        response.setEven_numbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecial_characters(specialChars);
        response.setSum(String.valueOf(sum));
        response.setConcat_string(concatStr.toString());

        return response;
    }
}