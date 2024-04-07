package com.mycompany.app.dao;

import com.mycompany.app.filters.StringsFilter;
import com.mycompany.app.models.Strings;

public interface StringsDAO extends BaseDAO<Strings> {
	Iterable<Strings> findByFilter(StringsFilter filter);
}
