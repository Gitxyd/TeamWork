package com.rx.vo;

import com.rx.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectedCourseVO {
    private String userid;
    private String username;
    private Integer studentid;
    private Integer courseid;
    private Boolean over;
}
