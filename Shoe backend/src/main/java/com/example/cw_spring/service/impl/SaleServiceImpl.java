package com.example.cw_spring.service.impl;

import com.example.cw_spring.ProjectionInterface.MostSolidItemProjection;
import com.example.cw_spring.dto.ItemDTO;
import com.example.cw_spring.dto.PlaceOrderRequestDTO;
import com.example.cw_spring.entity.*;
import com.example.cw_spring.enums.Level;
import com.example.cw_spring.repository.*;
import com.example.cw_spring.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {
    private InventoryDAO inventoryDAO;
    private SaleDAO saleDAO;
    private SaleInventoryDAO saleInventoryDAO;
    private UserDAO userDAO;
    private CustomerDAO customerDAO;
    @Override
    public List<String> getItemIds() {
        return inventoryDAO.getItemIds();
    }

    @Override
    public InventoryEntity getItem(String item_code) {
        Optional<InventoryEntity> item = inventoryDAO.findById(item_code);
        if (item.isPresent()) {
            return item.get();
        } else {
            return null;
        }
    }

    @Override
    public boolean placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO) {
        Optional<UserEntity> user = userDAO.findByEmail(placeOrderRequestDTO.getUserEmail());
        Optional<CustomerEntity> customer = customerDAO.findById(placeOrderRequestDTO.getCustomer_code());
        if (user.isPresent()) {
            return false;
        }

        SaleEntity sale = new SaleEntity();
        sale.setOrder_id(UUID.randomUUID().toString());
        sale.setUserEntity(user.get());
        sale.setCashier_name(user.get().getEmployee().getEmployee_name());

        if (customer.isPresent()) {
            sale.setCustomerEntity(customer.get());

            if (placeOrderRequestDTO.getNet_total() > 800) {
                sale.setAdded_points(1);
                customer.get().setTotal_points(customer.get().getTotal_points() + 1);

                if (customer.get().getTotal_points() < 50) {
                    customer.get().setLevel(Level.NEW);
                }

                if (customer.get().getTotal_points() >= 50 && customer.get().getTotal_points() <= 99) {
                    customer.get().setLevel(Level.BRONZE);
                }

                if (customer.get().getTotal_points() >= 100 && customer.get().getTotal_points() <= 199) {
                    customer.get().setLevel(Level.SILVER);
                }

                if (customer.get().getTotal_points() >= 200 && customer.get().getTotal_points() <= 299) {
                    customer.get().setLevel(Level.GOLD);
                }

            }
        } else {
            sale.setCustomerEntity(null);
        }

        sale.setCustomer_name(placeOrderRequestDTO.getCustomer_name());
        sale.setTotal_price(placeOrderRequestDTO.getNet_total());
        sale.setPurchase_date(new Date());
        sale.setPayment_method(placeOrderRequestDTO.getPayment_method());

        SaleEntity savedSale = saleDAO.save(sale);

        List<SaleInventoryEntity> saleInventory = new ArrayList<>();
        for (ItemDTO item : placeOrderRequestDTO.getItems()) {
            InventoryEntity inventory = inventoryDAO.findById(item.getItem_code())
                    .orElseThrow(() -> new IllegalArgumentException("Item not found"));

            SaleInventoryEntity saleInventoryEntity = new SaleInventoryEntity();
            saleInventoryEntity.setOrder_Details_Id(UUID.randomUUID().toString());
            saleInventoryEntity.setInventory_Entity(inventory);
            saleInventoryEntity.setSale_Entity(savedSale);
            saleInventoryEntity.setItem_name(item.getItem_name());
            saleInventoryEntity.setQuantity(item.getQuantity());
            saleInventoryEntity.setSize(item.getItem_size());
            saleInventoryEntity.setUnit_price(item.getUnit_price());
            saleInventoryEntity.setSub_total(item.getTotal_price());

            saleInventory.add(saleInventoryEntity);
        }
        saleInventoryDAO.saveAll(saleInventory);

        for (SaleInventoryEntity saleInventoryEntity : saleInventory) {
            InventoryEntity inventory = saleInventoryEntity.getInventory_Entity();
            int soldQuantity = saleInventoryEntity.getQuantity();
            int sizeToReduce = saleInventoryEntity.getSize();

            for (SizeEntity size : inventory.getSizes()) {
                if (size.getSize() == sizeToReduce) {
                    int currentQuantity = size.getQuantity();
                    size.setQuantity(currentQuantity - soldQuantity);
                    break;
                }
            }
        }

        inventoryDAO.saveAll(saleInventory.stream()
                .map(SaleInventoryEntity::getInventory_Entity)
                .collect(Collectors.toSet()));

        return true;
    }

    @Override
    public Double getTotalSales(String date) {
        return saleDAO.getTotalSales(date);
    }

    @Override
    public Double getTotalProfit(String date) {
        return saleDAO.getTotalProfit(date);
    }

    @Override
    public MostSolidItemProjection getMostSolidItem(String date) {
        return saleDAO.getMostSolidItem(date);
    }
}
