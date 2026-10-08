package com.employee.api.service;

import java.util.List;

import com.employee.api.dto.EmployeeRequestDto;
import com.employee.api.dto.EmployeeResponseDto;

public interface EmployeeService {

	List<EmployeeResponseDto> getAll();

	EmployeeResponseDto getById(Long id);

	EmployeeResponseDto create(EmployeeRequestDto dto);

	EmployeeResponseDto update(Long id, EmployeeRequestDto dto);

	void delete(Long id);
}
