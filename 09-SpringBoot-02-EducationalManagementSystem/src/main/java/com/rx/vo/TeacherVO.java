package com.rx.vo;

import com.rx.entity.Teacher;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherVO extends Teacher {
    private String collegeName;
}
