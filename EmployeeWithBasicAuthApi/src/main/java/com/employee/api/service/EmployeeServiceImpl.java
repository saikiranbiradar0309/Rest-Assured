package com.employee.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.api.dto.EmployeeRequestDto;
import com.employee.api.dto.EmployeeResponseDto;
import com.employee.api.entity.Employee;
import com.employee.api.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository repo;

	private EmployeeResponseDto mapToResponse(Employee e) {
		EmployeeResponseDto dto = new EmployeeResponseDto();
		dto.setId(e.getId());
		dto.setFirstName(e.getFirstName());
		dto.setLastName(e.getLastName());
		dto.setAddress(e.getAddress());
		dto.setMobileNumber(e.getMobileNumber());
		dto.setCountry(e.getCountry());
		dto.setEmail(e.getEmail());
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeResponseDto> getAll() {
		return repo.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public EmployeeResponseDto getById(Long id) {
		return repo.findById(id).map(this::mapToResponse)
				.orElseThrow(() -> new RuntimeException("Employee not found: " + id));
	}

	@Override
	public EmployeeResponseDto create(EmployeeRequestDto dto) {
		Employee e = new Employee();
		e.setFirstName(dto.getFirstName());
		e.setLastName(dto.getLastName());
		e.setAddress(dto.getAddress());
		e.setMobileNumber(dto.getMobileNumber());
		e.setCountry(dto.getCountry());
		e.setEmail(dto.getEmail());
		return mapToResponse(repo.save(e));
	}

	@Override
	public EmployeeResponseDto update(Long id, EmployeeRequestDto dto) {
		Employee e = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found: " + id));
		e.setFirstName(dto.getFirstName());
		e.setLastName(dto.getLastName());
		e.setAddress(dto.getAddress());
		e.setMobileNumber(dto.getMobileNumber());
		e.setCountry(dto.getCountry());
		e.setEmail(dto.getEmail());
		return mapToResponse(repo.save(e));
	}

	@Override
	public void delete(Long id) {
		if (!repo.existsById(id)) {
			throw new RuntimeException("Not found: " + id);
		}
		repo.deleteById(id);
	}
}
