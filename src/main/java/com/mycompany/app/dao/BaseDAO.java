package com.mycompany.app.dao;

public interface BaseDAO<T> {
	T save(T entity);

	T findById(String id);

	Iterable<T> findAll();

	void deleteById(String id);

	void update(T entity);
}
