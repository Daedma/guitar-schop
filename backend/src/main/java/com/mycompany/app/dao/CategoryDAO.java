package com.mycompany.app.dao;

import com.mycompany.app.models.Category;

public interface CategoryDAO extends BaseDAO<Category> {
	String addGood(String category, String goodId);
}
