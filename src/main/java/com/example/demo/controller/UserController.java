package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.UserModel;
import com.example.demo.service.UserService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("user-service")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;
    @PostMapping("/upload-csv-file")
    @ResponseStatus(HttpStatus.OK)
    public List<UserModel> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        List<UserModel> users = new ArrayList<>();
        // validate file
        if (file.isEmpty()) {
            System.out.println("Paste error here!!");
        } else {

            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                CsvToBean<UserModel> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(UserModel.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                users = csvToBean.parse();
                userService.createUsers(users);

            } catch (Exception ex) {
                System.out.println("Error is --> " + ex);
            }
        }

        return users;
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }
}
