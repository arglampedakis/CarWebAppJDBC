/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Car;
import util.DBUtils;

/**
 *
 * @author glamb
 */
public class CarDao {

    public Car fetchCarById(int carid) {
        Car c = null;
        DBUtils dbu = new DBUtils();
        Connection con = dbu.getConPool();
        try {
            // plain jdbc
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Car WHERE id=?");
            ps.setInt(1, carid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                c = new Car();
                c.setId(rs.getInt("id"));
                c.setPlate(rs.getString("plate"));
                c.setHorsepower(rs.getInt("horsepower"));
            }

        } catch (SQLException ex) {

        }

        return c;
    }

    //insert car and get back the id of the inserted row
    public int insertCarGetId(String plate, int horsepower) {
        DBUtils dbu = new DBUtils();
        Connection con = dbu.getConPool();
        int id = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Car (plate, horsepower) VALUES(? , ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, plate);
            ps.setInt(2, horsepower);

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            while (generatedKeys.next()) {
                id =generatedKeys.getInt(1);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return id;

    }

    //not currently in use - alternative way to insert . returns true (if rows affected >0 )
    public boolean insertCar(String plate, int horsepower) {
        DBUtils dbu = new DBUtils();
        Connection con = dbu.getConPool();
        int rows = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Car (plate, horsepower) VALUES(? , ?)");
            ps.setString(1, plate);
            ps.setInt(2, horsepower);

            rows = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (rows == 0) {
            //Something went wrong
            return false;
        } else {
            //successfully inserted to DB
            return true;
        }

    }

    //not currently in use. Fetch car by Plate and Horsepower
    public Car fetchCarByPlateAndHorsepower(String plate, int horsepower) {
        Car c = null;
        DBUtils dbu = new DBUtils();
        Connection con = dbu.getConPool();
        try {
            // plain jdbc
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Car WHERE plate=? AND horsepower=?");
            ps.setString(1, plate);
            ps.setInt(2, horsepower);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                c = new Car();
                c.setId(rs.getInt("id"));
                c.setPlate(rs.getString("plate"));
                c.setHorsepower(rs.getInt("horsepower"));
            }

        } catch (SQLException ex) {

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return c;
    }
}
