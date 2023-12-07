package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.entity.UserOrder;
import java.util.List;

public interface UserOrderDao {

    /**
     * Find a user order in the database by id
     *
     * @param id - the order id
     * @return the order
     */
    UserOrder findUserOrderById(Long id);

    /**
     * Save a new order in the database
     *
     * @param userOrder - the order
     */
    UserOrder saveUserOrder(UserOrder userOrder);

    /**
     * Find user orders in the database by user id
     *
     * @param userId - the user id
     * @return the list of orders
     */
    List<UserOrder> findUserOrderByUserId(Long userId);

    /**
     * Find user orders in the database by status
     *
     * @param status - the status
     * @return the list of orders
     */
    List<UserOrder> findUserOrderByOrderInformationStatus(String status);

    /**
     * Find all user orders in the database
     *
     * @return the list of orders
     */
    List<UserOrder> findAllUserOrders();

    /**
     * Find user orders in the database by user id where OrderInformation is null
     *
     * @param userId - the user id
     * @return the list of user orders
     */
    List<UserOrder> findByUserWithoutOrderInformation(Long userId);

    /**
     * Find user orders in the database by user and product id where OrderInformation is null
     *
     * @param userId    - the user id
     * @param productId - the product id
     * @return the list of user orders
     */
    List<UserOrder> findByUserAndProductWithoutOrderInformation(Long userId, Long productId);

    /**
     * Update a user order in the database by order and user information id
     *
     * @param orderId            - the order id
     * @param orderInformationId - the order information id
     */
    void updateOrderInformation(Long orderId, Long orderInformationId);

    /**
     * Remove a user order in the database
     *
     * @param userOrder - the order id
     */

    void removeUserOrderById(UserOrder userOrder);
}