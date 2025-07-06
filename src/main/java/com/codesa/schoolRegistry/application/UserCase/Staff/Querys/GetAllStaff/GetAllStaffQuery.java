package com.codesa.schoolRegistry.application.UserCase.Staff.Querys.GetAllStaff;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllStaffQuery {
    private int page;
    private int size;
}
