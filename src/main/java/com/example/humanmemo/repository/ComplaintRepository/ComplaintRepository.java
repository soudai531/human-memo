package com.example.humanmemo.repository.ComplaintRepository;


import com.example.humanmemo.service.ComplaintEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends CrudRepository<ComplaintEntity, Long> {

    @Query("SELECT id, title, reg_date_on FROM complaints LIMIT :limit OFFSET :offset")
    List<ComplaintEntity> selectList(@Param("limit") int limit, @Param("offset") long offset);

}
