package net.learntechnology.empmaint.mapper;

import java.util.List;

public interface GenericMapper <T, PK> {
	T fetch(PK id);
	List<T> fetchAll();
	void update(T obj);
	void delete(PK id);
	void insert(T obj);
}
