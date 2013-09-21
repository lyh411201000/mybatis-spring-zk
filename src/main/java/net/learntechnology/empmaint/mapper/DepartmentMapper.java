package net.learntechnology.empmaint.mapper;

import net.learntechnology.empmaint.domain.Department;


/*
You need this 'dummy mapper' to map the MyBATIS xml class.
Note, also, in real life you'll rarely ever just "only"
have the simple CRUD operations you see in the GenericMapper.
You typically end up with other mapper operations that go
beyond the basic crud ones found in our GenericMapper.
 */
public interface DepartmentMapper extends GenericMapper<Department, Integer> {
}
