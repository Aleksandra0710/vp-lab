package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Artist {
    Long id;
    String firstName;
    String lastName;
    String bio;
}