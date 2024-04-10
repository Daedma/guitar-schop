package com.mycompany.app.dao;

import com.mycompany.app.filters.GoodFilter;
import com.mycompany.app.models.Good;

public interface GoodDAO extends BaseDAO<Good> {
	Iterable<Good> findByFilter(GoodFilter filter);

	Float getMinCost();

	Float getMaxCost();
}
