package net.learntechnology.empmaint.services;

import net.learntechnology.empmaint.mapper.GenericMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract  class GenericCrudService<T, PK>  {

	//need to be public I believe for Spring AOP CGLIB
	public GenericMapper mapper;

	public GenericCrudService(GenericMapper mapper) {
		this.mapper = mapper;
	}

	//no arg constructor also needed by spring AOP cglib stuff
	public GenericCrudService() {
	}

	public List<T> fetchAll() {
		return mapper.fetchAll();
	}

	public T fetch(PK id) {
		return (T)mapper.fetch(id);
	}

	@Transactional
	public void update(T obj) {
		mapper.update(obj);
	}

	@Transactional
	public void insert(T obj) {
		mapper.insert(obj);
	}

	@Transactional
	public void delete(PK id) {
		mapper.delete(id);
	}
}
