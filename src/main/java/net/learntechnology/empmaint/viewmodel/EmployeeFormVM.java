package net.learntechnology.empmaint.viewmodel;

import net.learntechnology.empmaint.Constants;
import net.learntechnology.empmaint.domain.Department;
import net.learntechnology.empmaint.domain.Employee;
import net.learntechnology.empmaint.services.DepartmentService;
import net.learntechnology.empmaint.services.EmployeeService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import java.util.List;

public class EmployeeFormVM {

	@Wire("#employeeForm")
	protected Window employeeForm;

	private Employee employee;
	List<Department> departments;

	@WireVariable
	private DepartmentService departmentService;
	@WireVariable
	private EmployeeService employeeService;

	//Allows us to wire up UI items with @Wire, such as the #employeeForm
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) org.zkoss.zk.ui.Component view){
		Selectors.wireComponents(view, this, false);
	}

	@Init
	public void init(@ExecutionArgParam(Constants.ARG_EMPLOYEE_ID) Integer employeeId) {
		if (employeeId != null) {
			employee = employeeService.fetch(employeeId);
		} else {
			employee = new Employee();
		}
		departments = departmentService.fetchAll();
	}

	@Command
	public void save() {
		if (employee.getId() != null) {
			employeeService.update(employee);
		} else {
			employeeService.insert(employee);
		}
		BindUtils.postGlobalCommand(null, null, Constants.GLOBAL_EMPLOYEES_MODIFIED, null);
		employeeForm.detach();
	}

	@Command
	public void delete() {
		Messagebox.show("Do you really want to Delete this Employee?", "Delete Employee", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
			new org.zkoss.zk.ui.event.EventListener() {
				public void onEvent(Event evt) {
					switch (((Integer) evt.getData()).intValue()) {
						case Messagebox.YES:
							employeeService.delete(employee.getId());
							BindUtils.postGlobalCommand(null, null, Constants.GLOBAL_EMPLOYEES_MODIFIED, null);
							employeeForm.detach();
							break;
						case Messagebox.NO:
							break;
					}
				}
			}
		);
	}

	public List<Department> getDepartments() {
		return departmentService.fetchAll();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


}
