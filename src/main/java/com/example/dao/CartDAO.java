package com.example.dao;

import com.example.model.*;
import jakarta.servlet.ServletContext;
import com.example.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;

public class CartDAO {
    private final ServletContext context;

    public CartDAO(ServletContext context) {
        this.context = context;
    }

    public boolean addToCart(String userId, String productId, int quantity) {
        String findBasketSql = "SELECT NB_BASKET FROM TB_BASKET WHERE NO_USER = ?";
        String insertBasketSql = "INSERT INTO TB_BASKET(NB_BASKET, NO_USER, QT_BASKET_AMOUNT) VALUES (SEQ_TB_BASKET.NEXTVAL, ?, 0)";
        String selectProductSql = "SELECT QT_SALE_PRICE, QT_STOCK FROM TB_PRODUCT WHERE NO_PRODUCT = ?";
        String updateItemSql = "UPDATE TB_BASKET_ITEM SET QT_BASKET_ITEM = QT_BASKET_ITEM + ?, QT_BASKET_ITEM_AMOUNT = QT_BASKET_ITEM_AMOUNT + ? WHERE NB_BASKET = ? AND NO_PRODUCT = ?";
        String insertItemSql = """
        INSERT INTO TB_BASKET_ITEM (
            NB_BASKET_ITEM, NB_BASKET, NO_PRODUCT, NO_USER,
            QT_BASKET_ITEM_PRICE, QT_BASKET_ITEM, QT_BASKET_ITEM_AMOUNT,
            CN_BASKET_ITEM_ORDER
        ) VALUES (SEQ_TB_BASKET_ITEM.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)
    """;
        String getMaxOrderSql = "SELECT NVL(MAX(CN_BASKET_ITEM_ORDER), 0) FROM TB_BASKET_ITEM WHERE NB_BASKET = ?";
        String getItemQtySql = "SELECT QT_BASKET_ITEM FROM TB_BASKET_ITEM WHERE NB_BASKET = ? AND NO_PRODUCT = ?";

        try (Connection conn = DBUtil.getConnection(context)) {
            conn.setAutoCommit(false);

            // 1. 장바구니 ID 조회 or 생성
            int basketId = -1;
            try (PreparedStatement ps = conn.prepareStatement(findBasketSql)) {
                ps.setString(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) basketId = rs.getInt("NB_BASKET");
                }
            }

            if (basketId == -1) {
                try (PreparedStatement ps = conn.prepareStatement(insertBasketSql, new String[]{"NB_BASKET"})) {
                    ps.setString(1, userId);
                    ps.executeUpdate();
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) basketId = rs.getInt(1);
                    }
                }
            }

            // 2. 상품 가격 & 재고 조회
            int price = 0;
            int stock = 0;
            try (PreparedStatement ps = conn.prepareStatement(selectProductSql)) {
                ps.setString(1, productId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        price = rs.getInt("QT_SALE_PRICE");
                        stock = rs.getInt("QT_STOCK");
                    }
                }
            }

            // 3. 현재 장바구니에 담긴 수량 조회
            int currentQty = 0;
            try (PreparedStatement ps = conn.prepareStatement(getItemQtySql)) {
                ps.setInt(1, basketId);
                ps.setString(2, productId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) currentQty = rs.getInt("QT_BASKET_ITEM");
                }
            }

            // 4. 재고 초과 시 중단
            if (currentQty + quantity > stock) {
                conn.rollback();
                return false;
            }

            // 5. 장바구니 품목 순서(CN_BASKET_ITEM_ORDER)
            int order = 1;
            try (PreparedStatement ps = conn.prepareStatement(getMaxOrderSql)) {
                ps.setInt(1, basketId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) order = rs.getInt(1) + 1;
                }
            }

            // 6. 이미 장바구니에 있으면 UPDATE
            int updated = 0;
            try (PreparedStatement ps = conn.prepareStatement(updateItemSql)) {
                ps.setInt(1, quantity);
                ps.setInt(2, price * quantity);
                ps.setInt(3, basketId);
                ps.setString(4, productId);
                updated = ps.executeUpdate();
            }

            // 7. 없으면 INSERT
            if (updated == 0) {
                try (PreparedStatement ps = conn.prepareStatement(insertItemSql)) {
                    ps.setInt(1, basketId);
                    ps.setString(2, productId);
                    ps.setString(3, userId);
                    ps.setInt(4, price);
                    ps.setInt(5, quantity);
                    ps.setInt(6, price * quantity);
                    ps.setInt(7, order);
                    ps.executeUpdate();
                }
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Basket getBasketByUserId(String userId) {
        String sql = "SELECT * FROM TB_BASKET WHERE NO_USER = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Basket basket = new Basket();
                basket.setNbBasket(rs.getInt("NB_BASKET"));
                basket.setIdUser(rs.getString("NO_USER"));
                basket.setQtBasketAmount(rs.getInt("QT_BASKET_AMOUNT"));
                basket.setDaFirstDate(rs.getDate("DA_FIRST_DATE"));
                return basket;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BasketItem> getBasketItemsWithProduct(int basketId) {
        String sql = """
        SELECT 
            bi.NB_BASKET_ITEM,
            bi.NB_BASKET,
            bi.CN_BASKET_ITEM_ORDER,
            bi.NO_PRODUCT,
            bi.NO_USER,
            bi.QT_BASKET_ITEM_PRICE,
            bi.QT_BASKET_ITEM,
            bi.QT_BASKET_ITEM_AMOUNT,
            bi.NO_REGISTER,
            bi.DA_FIRST_DATE,
            
            p.NM_PRODUCT,
            p.QT_SALE_PRICE,
            p.QT_CUSTOMER_PRICE,
            p.ID_FILE,
            p.QT_STOCK,
            p.QT_DELIVERY_FEE
        FROM TB_BASKET_ITEM bi
        JOIN TB_PRODUCT p ON bi.NO_PRODUCT = p.NO_PRODUCT
        WHERE bi.NB_BASKET = ?
        ORDER BY bi.CN_BASKET_ITEM_ORDER
    """;

        List<BasketItem> itemList = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, basketId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BasketItem item = new BasketItem();
                item.setNbBasketItem(rs.getInt("NB_BASKET_ITEM"));
                item.setNbBasket(rs.getInt("NB_BASKET"));
                item.setCnBasketItemOrder(rs.getInt("CN_BASKET_ITEM_ORDER"));
                item.setNoProduct(rs.getString("NO_PRODUCT"));
                item.setIdUser(rs.getString("NO_USER"));
                item.setQtBasketItemPrice(rs.getInt("QT_BASKET_ITEM_PRICE"));
                item.setQtBasketItemQty(rs.getInt("QT_BASKET_ITEM"));
                item.setQtBasketItemAmount(rs.getInt("QT_BASKET_ITEM_AMOUNT"));
                item.setNoRegister(rs.getString("NO_REGISTER"));
                item.setDaFirstDate(rs.getDate("DA_FIRST_DATE"));

                // ⬇️ 연관된 상품 정보 생성
                Product product = new Product();
                product.setNoProduct(rs.getInt("NO_PRODUCT"));
                product.setNmProduct(rs.getString("NM_PRODUCT"));
                product.setQtSalePrice(rs.getInt("QT_SALE_PRICE"));
                product.setQtCustomer(rs.getInt("QT_CUSTOMER_PRICE"));
                product.setIdFile(rs.getString("ID_FILE"));
                product.setQtStock(rs.getInt("QT_STOCK"));
                product.setQtDeliveryFee(rs.getInt("QT_DELIVERY_FEE"));

                item.setProduct(product);

                itemList.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemList;
    }

    public boolean deleteBasketItem(int nbBasketItem) {
        String sql = "DELETE FROM TB_BASKET_ITEM WHERE NB_BASKET_ITEM = ?";

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, nbBasketItem);
            int result = ps.executeUpdate();
            return result == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBasketItemQty(int nbBasketItem, int newQty) {
        String getProductSql = "SELECT P.QT_STOCK FROM TB_BASKET_ITEM B JOIN TB_PRODUCT P ON B.NO_PRODUCT = P.NO_PRODUCT WHERE B.NB_BASKET_ITEM = ?";
        String updateSql = """
        UPDATE TB_BASKET_ITEM 
        SET QT_BASKET_ITEM = ?, 
            QT_BASKET_ITEM_AMOUNT = QT_BASKET_ITEM_PRICE * ?
        WHERE NB_BASKET_ITEM = ?
    """;

        try (Connection conn = DBUtil.getConnection(context)) {

            // 1. 재고 조회
            int stock = 0;
            try (PreparedStatement ps = conn.prepareStatement(getProductSql)) {
                ps.setInt(1, nbBasketItem);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        stock = rs.getInt("QT_STOCK");
                    }
                }
            }

            // 2. 재고 초과 시 수정 거부
            if (newQty > stock) {
                return false;
            }

            // 3. 수량 업데이트
            try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
                ps.setInt(1, newQty);
                ps.setInt(2, newQty);
                ps.setInt(3, nbBasketItem);
                int result = ps.executeUpdate();
                return result == 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllItemsByBasketId(int basketId) {
        String sql = "DELETE FROM TB_BASKET_ITEM WHERE NB_BASKET = ?";

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, basketId);
            int result = ps.executeUpdate();
            return result >= 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Product getProductFromBasketItemId(int basketItemId) {
        String sql = "SELECT NO_PRODUCT FROM TB_BASKET_ITEM WHERE NB_BASKET_ITEM = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, basketItemId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String productId = rs.getString("NO_PRODUCT");
                ProductDAO productDAO = new ProductDAO(context);
                return productDAO.getProductById(Integer.parseInt(productId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertOrderWithItems(Order order, List<OrderItem> items) {
        String insertOrderSql = "INSERT INTO TB_ORDER (ID_ORDER, ID_USER, QT_ORDER_AMOUNT, NM_ORDER_PERSON, NM_RECEIVER, NO_DELIVERY_ZIPNO, NM_DELIVERY_ADDRESS, NM_RECEIVER_TELNO, NM_DELIVERY_SPACE, CD_ORDER_TYPE, ST_ORDER, ST_PAYMENT, DA_ORDER, NO_REGISTER, DA_FIRST_DATE) " +
                "VALUES ('ORD-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') || LPAD(SEQ_TB_ORDER.NEXTVAL, 4, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE)";

        String insertItemSql = "INSERT INTO TB_ORDER_ITEM (ID_ORDER_ITEM, ID_ORDER, CN_ORDER_ITEM, NO_PRODUCT, ID_USER, QT_UNIT_PRICE, QT_ORDER_ITEM, QT_ORDER_ITEM_AMOUNT, ST_PAYMENT, NO_REGISTER, DA_FIRST_DATE) " +
                "VALUES ('ODI-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') || LPAD(SEQ_TB_ORDER_ITEM.NEXTVAL, 4, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

        try (Connection conn = DBUtil.getConnection(context)) {
            conn.setAutoCommit(false);

            String orderId = null;

            try (PreparedStatement ps = conn.prepareStatement(insertOrderSql, new String[] {"ID_ORDER"})) {
                ps.setString(1, order.getIdUser());
                ps.setInt(2, order.getQtOrderAmount());
                ps.setString(3, order.getNmOrderPerson());
                ps.setString(4, order.getNmReceiver());
                ps.setString(5, order.getNoDeliveryZipno());
                ps.setString(6, order.getNmDeliverySpace());
                ps.setString(7, order.getNmReceiverTelno());
                ps.setString(8, order.getNmDeliverySpace());
                ps.setString(9, order.getCdOrderType());
                ps.setString(10, order.getStOrder());
                ps.setString(11, order.getStPayment());
                ps.setString(12, order.getIdUser()); // no_register

                ps.executeUpdate();

                // 생성된 주문 ID 가져오기
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        orderId = rs.getString(1);
                    }
                }
            }

            if (orderId == null) {
                conn.rollback();
                return false;
            }

            // 주문 품목 insert
            int itemOrder = 1;
            try (PreparedStatement ps = conn.prepareStatement(insertItemSql)) {
                for (OrderItem item : items) {
                    ps.setString(1, orderId);
                    ps.setInt(2, itemOrder++);
                    ps.setString(3, item.getNoProduct());
                    ps.setString(4, item.getIdUser());
                    ps.setInt(5, item.getQtUnitPrice());
                    ps.setInt(6, item.getQtOrderItem());
                    ps.setInt(7, item.getQtOrderItemAmount());
                    ps.setString(8, order.getStPayment());
                    ps.setString(9, order.getIdUser()); // no_register

                    ps.addBatch();
                }
                ps.executeBatch();
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Order> getOrdersByUser(String userId) {
        String sql = "SELECT * FROM TB_ORDER WHERE ID_USER = ? ORDER BY DA_ORDER DESC";
        List<Order> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = extractOrder(rs);
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM TB_ORDER ORDER BY DA_ORDER DESC";
        List<Order> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = extractOrder(rs);
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    private Order extractOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setIdOrder(rs.getString("ID_ORDER"));
        order.setIdUser(rs.getString("ID_USER"));
        order.setQtOrderAmount(rs.getInt("QT_ORDER_AMOUNT"));
        order.setNmOrderPerson(rs.getString("NM_ORDER_PERSON"));
        order.setNmReceiver(rs.getString("NM_RECEIVER"));
        order.setNmReceiverTelno(rs.getString("NM_RECEIVER_TELNO"));
        order.setNoDeliveryZipno(rs.getString("NO_DELIVERY_ZIPNO"));
        order.setNmDeliveryAddress(rs.getString("NM_DELIVERY_ADDRESS"));
        order.setNmDeliverySpace(rs.getString("NM_DELIVERY_SPACE"));
        order.setCdOrderDate(rs.getDate("DA_ORDER"));
        order.setStOrder(rs.getString("ST_ORDER"));
        order.setStPayment(rs.getString("ST_PAYMENT"));
        return order;
    }



}
