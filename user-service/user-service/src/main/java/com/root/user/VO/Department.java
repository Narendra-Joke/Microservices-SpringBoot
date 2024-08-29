package com.root.user.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {
    private Long departmentId;
    private String departmentName;

    private String departmentAddress;
    private String departmentCode;
}
