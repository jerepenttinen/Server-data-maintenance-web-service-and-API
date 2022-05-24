package com.example.servermaintenance.courseschema;

import lombok.Data;

@Data
public class CourseSchemaPart {
    private String name;
    private boolean locked;
    private String validatorRegex;
    private String validatorMessage;
    private boolean autogenerated;
    private String generationStatement;
    private int controlWidth;
    private int order;
    private boolean required;
    private boolean open;
}
