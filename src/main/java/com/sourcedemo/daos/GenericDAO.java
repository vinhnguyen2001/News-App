package com.sourcedemo.daos;

import com.sourcedemo.mapper.RowMapper;

import java.util.List;

public interface GenericDAO<T> {

   List<T> query(String query, RowMapper<T> rowMapper, Object... parameters);

   Long insert(String query, Object...parameters);

   void update(String query, Object...parameters);
}
