package com.mycompany.app.dao;

import com.mycompany.app.filters.GuitarFilter;
import com.mycompany.app.models.Guitar;

public interface GuitarDAO extends BaseDAO<Guitar> {
	Iterable<Guitar> findByFilter(GuitarFilter filter);

	Double getMinCost();

	Double getMaxCost();

	Iterable<String> getGuitarForms();

	Iterable<String> getGuitarFrets();
}
