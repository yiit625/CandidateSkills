package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.UserModel;
import com.example.demo.exception.FileNotFoundException;
import com.example.demo.service.UserService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("user-service")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/upload-csv-file")
    @ResponseStatus(HttpStatus.OK)
    public List<User> uploadCSVFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) throw new FileNotFoundException();

        Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        CsvToBean<UserModel> csvToBean = new CsvToBeanBuilder(reader)
                .withType(UserModel.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<UserModel> userModels = csvToBean.parse();

        return userService.createUsers(userModels);
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
