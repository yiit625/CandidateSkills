package com.example.demo.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserModel {
    @CsvBindByName(column = "PRIMARY_KEY")
    private String id;
    @CsvBindByName(column = "NAME")
    private String name;
    @CsvBindByName(column = "DESCRIPTION")
    private String description;
    @CsvBindByName(column = "UPDATED_TIMESTAMP")
    private String date;
}
