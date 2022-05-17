package com.rx.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageVO {
    private Integer code;
    private Integer role;
    private String message;
    private Boolean success;
    private Object data;
}
