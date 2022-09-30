package com.sourcedemo.daos.impl;

import com.sourcedemo.daos.ICategoryDAO;
import com.sourcedemo.mapper.CategoryMapper;
import com.sourcedemo.models.CategoryModel;

import java.util.List;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {
    @Override
    public List<CategoryModel> findAll() {
        String sql = "SELECT * FROM category";
        return query(sql, new CategoryMapper());
    }

    @Override
    public CategoryModel findOne(long id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        List<CategoryModel> category = query(sql, new CategoryMapper(), id);
        return category.isEmpty() ? null : category.get(0);
    }

    @Override
    public CategoryModel findOneByCode(String code) {
        String sql = "SELECT * FROM category WHERE code = ?";
        List<CategoryModel> category = query(sql, new CategoryMapper(), code);
        return category.isEmpty() ? null : category.get(0);
    }

}
