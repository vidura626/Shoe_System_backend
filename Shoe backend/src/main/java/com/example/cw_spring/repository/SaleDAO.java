package com.example.cw_spring.repository;

import com.example.cw_spring.ProjectionInterface.MostSolidItemProjection;
import com.example.cw_spring.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleDAO extends JpaRepository<SaleEntity, String> {
    @Query(value = "SELECT item_code FROM inventory_entity", nativeQuery = true)
    List<String> getItemIds();

    @Query(value = "SELECT SUM(total_price) FROM sale_entity WHERE purchase_date LIKE :date%", nativeQuery = true)
    Double getTotalSales(@Param("date") String date);

    @Query(value = "SELECT\n" +
            "SUM((sid.quantity * (sid.price - sid.size))) AS total_price\n" +
            "FROM\n" +
            "   size s\n" +
            "JOIN\n" +
            "   sale_inventory_entity sid ON s.size = sid.size AND s.item_code = sid.item_code\n" +
            " JOIN\n" +
            "   sale_entity se ON se.order_id = sid.order_id\n" +
            "WHERE\n" +
            "   se.purchase_date LIKE :date%", nativeQuery = true)
    Double getTotalProfit(@Param("date") String date);

    @Query(value = "SELECT\n" +
            "    sid.item_code,\n" +
            "    sid.size,\n" +
            "    SUM(sid.quantity) AS total_quantity_sold,\n" +
            "    i.item_pic,\n" +
            "    i.item_desc\n" +
            "FROM\n" +
            "    sale_inventory_details sid\n" +
            "JOIN\n" +
            "    sale s ON sid.order_id = s.order_id\n" +
            "JOIN\n" +
            "    inventory_entity i ON sid.item_code = i.item_code\n" +
            "WHERE\n" +
            "    DATE(s.purchase_date) = :date\n" +
            "GROUP BY\n" +
            "    sid.item_code, sid.size, i.item_pic -- or i.picture\n" +
            "ORDER BY\n" +
            "    total_quantity_sold DESC\n" +
            "LIMIT 1;", nativeQuery = true)
    MostSolidItemProjection getMostSolidItem(@Param("date") String date);
}
