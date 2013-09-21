package net.learntechnology.empmaint.services;

import net.learntechnology.empmaint.domain.Department;
import net.learntechnology.empmaint.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
NOTE: services just provide another layer of abstraction between
your ViewModel and the MyBatis mappers. Nothing wrong with using
the Mappers directly in your ViewModels if you like and getting
rid of this service layer. In a more complex real-world situation
though you usually need do other things besides simple CRUD so
I tend to just start always wrapping my CRUD operations with a
Service class.

Currently this class just does nothing but wrap our Mapper
 */
@Service
public class DepartmentService extends GenericCrudService<Department, Integer> {

	@Autowired(required = true)
	public DepartmentService(DepartmentMapper departmentMapper) {
		super(Department.class, departmentMapper);
	}
	//no arg needed by spring AOP cglib
	public DepartmentService() {
	}

	//You'd have other non-simple crud based operations here
	//or over-ride the base crud operations if need be
}
