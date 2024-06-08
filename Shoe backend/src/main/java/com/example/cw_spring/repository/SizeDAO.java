package com.example.cw_spring.repository;

import com.example.cw_spring.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SizeDAO extends JpaRepository<SizeEntity, String> {

    @Query(value = "SELECT size_id FROM size ORDER BY size_id DESC LIMIT 1", nativeQuery = true)
    String findLastId();

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM size WHERE size = :size AND item_code = :item_code", nativeQuery = true)
    Long countBySizeIdAndItemCode(@Param("size") String size, @Param("item_code") String item_code);

    @Modifying
    @Query(value = "DELETE FROM size WHERE item_code = :item_code AND size = :size", nativeQuery = true)
    int deleteByItemCodeAndSizeId(@Param("item_code") String item_code, @Param("size") String size);

    @Query(value = "SELECT * FROM size WHERE item_code = :item_code AND size = :size", nativeQuery = true)
    SizeEntity getDataWithSizeAndItemId(@Param("item_code") String item_code, @Param("size") String size);
}
