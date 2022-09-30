package com.sourcedemo.services;

import com.sourcedemo.models.NewModel;
import com.sourcedemo.paging.Pageable;

import java.util.List;

public interface INewService {

    List<NewModel> findByCategoryId(Long categoryId);
    NewModel save(NewModel newModel);
    NewModel update(NewModel updateNew);
    void delete(long[] ids);
    List<NewModel> findAll(Pageable pageable);
    int getTotalItem();
    NewModel findOne(long id);
}
