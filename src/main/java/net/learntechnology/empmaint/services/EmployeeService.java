package net.learntechnology.empmaint.services;

import net.learntechnology.empmaint.domain.Employee;
import net.learntechnology.empmaint.mapper.EmployeeMapper;
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
public class EmployeeService extends GenericCrudService<Employee, Integer> {

	@Autowired(required = true)
	public EmployeeService(EmployeeMapper employeeMapper) {
		super(employeeMapper);
	}

	//no arg needed by spring AOP cglib
	public EmployeeService() {}

	//You'd have other non-simple crud based operations here
	//or over-ride the base crud operations if need be
}
