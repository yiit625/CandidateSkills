package com.example.demo.controller;

import com.example.demo.dto.UserModel;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("user-service")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file) {

        // validate file
        if (file.isEmpty()) {
            System.out.println("Paste error here!!");
        } else {

            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                CsvToBean<UserModel> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(UserModel.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<UserModel> users = csvToBean.parse();

                System.out.println(users.get(0).getId());
                System.out.println(users.get(0).getName());
                System.out.println(users.get(0).getDescription());
                System.out.println(users.get(0).getDate());


            } catch (Exception ex) {
                System.out.println("Error is --> " + ex);
            }
        }

        return "Successful message";
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userId) {
        System.out.println(userId);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserModel getUserById(@PathVariable String userId) {
        System.out.println(userId);
        return new UserModel();
    }
}
