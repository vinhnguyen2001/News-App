package com.sourcedemo.services;

import com.sourcedemo.models.CategoryModel;

import java.util.List;

public interface ICategoryService {

    List<CategoryModel> findAll();
}
