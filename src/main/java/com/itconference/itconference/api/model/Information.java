package com.itconference.itconference.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Information {
    @JsonProperty("message")
    private String message;
    @JsonProperty("code")
    private String code;
}
