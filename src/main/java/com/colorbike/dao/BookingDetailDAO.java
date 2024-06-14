/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.colorbike.dao;

import com.colorbike.dto.Booking;
import com.colorbike.dto.BookingDetail;
import com.colorbike.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huypd
 */
public class BookingDetailDAO {

    private static BookingDetailDAO instance;
    private Connection conn = DBUtil.makeConnection();

    // Cấm new trực tiếp DAO
    //Chỉ new DAO qua hàm static getInstance() để quản lí được số object/instance đã new - SINGLETON DESIGN PATTERN
    private BookingDetailDAO() {
    }

    public static BookingDetailDAO getInstance() {

        if (instance == null) {
            instance = new BookingDetailDAO();
        }
        return instance;
    }

    public List<BookingDetail> getListBookingDetails(String bookingId) {
        List<BookingDetail> list = new ArrayList<>();
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM [Booking Detail]\n"
                    + "WHERE BookingID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, bookingId);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new BookingDetail(rs.getInt("BookingDetailID"), rs.getInt("MotorcycleDetailID"), rs.getString("BookingID"), rs.getDouble("TotalPrice")));
            }
        } catch (Exception ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
    
    public static void main(String[] args) {
        System.out.println(BookingDetailDAO.getInstance().getListBookingDetails("BOOK000006"));
    }
}
