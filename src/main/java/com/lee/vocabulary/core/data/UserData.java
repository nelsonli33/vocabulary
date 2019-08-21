package com.lee.vocabulary.core.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserData implements Serializable {
    private static final long serialVersionUID = -6427473391928738691L;
    private String username;
}
