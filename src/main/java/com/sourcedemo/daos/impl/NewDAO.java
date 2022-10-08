package com.sourcedemo.daos.impl;

import com.sourcedemo.daos.INewDAO;
import com.sourcedemo.mapper.NewMapper;
import com.sourcedemo.models.NewModel;
import com.sourcedemo.paging.Pageable;
//import org.apache.commons.lang.StringUtils;
import java.util.List;
public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {
    @Override
    public List<NewModel> findByCategoryId(Long categoryId) {
        String sql = "SELECT * FROM news WHERE categoryid = ?";
        return query(sql, new NewMapper(), categoryId);
    }

    @Override
    public Long save(NewModel newModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO news (title, content,");
        sql.append(" thumbnail, shortdescription, categoryid, createddate, createdby)");
        sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
        return insert(sql.toString(), newModel.getTitle(), newModel.getContent(),
                newModel.getThumbnail(), newModel.getShortDescription(), newModel.getCategoryId(),
                newModel.getCreatedDate(), newModel.getCreatedBy());
    }

    @Override
    public NewModel findOne(Long id) {
        String sql = "SELECT * FROM news WHERE id = ?";
        List<NewModel> news = query(sql, new NewMapper(), id);
        return news.isEmpty() ? null : news.get(0);
    }

    @Override
    public void update(NewModel updateNew) {
        StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
        sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
        sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
        update(sql.toString(), updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(),
                updateNew.getContent(), updateNew.getCategoryId(), updateNew.getCreatedDate(),
                updateNew.getCreatedBy(), updateNew.getModifiedDate(),
                updateNew.getModifiedBy(), updateNew.getId());
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM news WHERE id = ?";
        update(sql, id);
    }

    @Override
    public List<NewModel> findAll(Pageable pageable) {

        StringBuilder queryStr = new StringBuilder("SELECT * FROM news");

        if(pageable.getSorter() != null &&
                !INewDAO.empty(pageable.getSorter().getSortBy())&&
                !INewDAO.empty(pageable.getSorter().getSortName())
        )
        {
            queryStr.append(" ORDER BY " + pageable.getSorter().getSortName() + "  " + pageable.getSorter().getSortBy()+ " ");
        }

        if(pageable.getOffset()!= null & pageable.getLimit() != null){
            queryStr.append(" LIMIT "+ pageable.getOffset() + " , " + pageable.getLimit() + "");
        }

        return query(queryStr.toString(),new NewMapper());
    }

//

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM news";
        return count(sql);
    }
}
