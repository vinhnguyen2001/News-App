package com.sourcedemo.services.impl;

import com.sourcedemo.daos.ICategoryDAO;
import com.sourcedemo.models.CategoryModel;
import com.sourcedemo.services.ICategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryService implements ICategoryService {
    @Inject
    private ICategoryDAO categoryDao;

    @Override
    public List<CategoryModel> findAll() {
        return categoryDao.findAll();
    }
}
