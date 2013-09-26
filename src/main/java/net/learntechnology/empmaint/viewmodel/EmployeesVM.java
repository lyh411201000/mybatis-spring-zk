package net.learntechnology.empmaint.viewmodel;

import net.learntechnology.empmaint.Constants;
import net.learntechnology.empmaint.domain.Employee;
import net.learntechnology.empmaint.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeesVM {
	private final static Logger logger = LoggerFactory.getLogger(EmployeesVM.class);

	private Employee selectedEmployee;

	@WireVariable
	private EmployeeService employeeService;

	@Command
	public void editEmployee() {
		Integer employeeID = selectedEmployee.getId();
		openModalWindow(employeeID);
	}

	@Command
	public void newEmployee() {
		openModalWindow(null);
	}

	private void openModalWindow(Integer employeeID) {
		Map<String,Object> args = new HashMap<String,Object>();
		args.put(Constants.ARG_EMPLOYEE_ID, employeeID);
		Window window = (Window)Executions.createComponents("/pages/employeeForm.zul", null, args);
		window.doModal();
	}

	@NotifyChange("employees")
	@GlobalCommand("employeesModified")
	public void employeesModified() {
		//notify will call getEmployees to refresh grid
	}

	public List<Employee> getEmployees() {
		return employeeService.fetchAll();
	}

	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

}
