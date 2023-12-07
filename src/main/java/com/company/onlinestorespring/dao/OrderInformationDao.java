package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.entity.OrderInformation;
import java.util.List;

public interface OrderInformationDao {

    /**
     * Find order information in the database
     *
     * @param id - the order information id
     * @return  the order information
     */
    OrderInformation findOrderInformationById(Long id);

    /**
     * save information about new orders in the database
     *
     * @param orderInformation - the order information to save
     */
    OrderInformation saveOrderInformation(OrderInformation orderInformation);

    /**
     * Find order information in the database by status
     *
     * @param status - the order status
     * @return the order information list
     */
    List<OrderInformation> findOrderInformationByStatus(String status);

    /**
     * Update the status of order information in the database by id
     *
     * @param id     - the order information id
     * @param status - the order information status
     */
    void updateStatusById(Long id, String status);
}