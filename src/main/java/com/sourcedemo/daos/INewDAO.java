package com.sourcedemo.daos;

import com.sourcedemo.models.NewModel;
import com.sourcedemo.paging.Pageable;

import java.util.List;

public interface INewDAO extends GenericDAO<NewModel> {
    NewModel findOne(Long id);
    List<NewModel> findByCategoryId(Long categoryId);
    Long save(NewModel newModel);
    void update(NewModel updateNew);
    void delete(long id);
    List<NewModel> findAll(Pageable pageable);
    int getTotalItem();


    public static boolean empty(final String s) {
        return s == null || s.trim().isEmpty();
    }
}
