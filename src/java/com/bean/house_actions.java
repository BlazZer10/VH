package com.bean;

import DB.Connect;
import static java.lang.Integer.parseInt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name="actions")
@RequestScoped
public class house_actions {
//get the database Instance 
    Connect con = new Connect();
    private PreparedStatement pstmt;
    private ResultSet rs;
    private int id;
    private String state = "enabled";

    public void add(String type, String country, String city, String street, String price, String capacity, int id, String photo) {
        try {
//query to insert houses into the database
            String sql = "INSERT INTO Houses (`type`,`country`, `city`, `street`, `price`,`capacity`, `created_by`, `status`,`photos`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.getConnection().prepareStatement(sql);
            pst.setString(1, type);
            pst.setString(2, country);
            pst.setString(3, city);
            pst.setString(4, street);
            pst.setString(5, price);
            pst.setString(6, capacity);
            pst.setInt(7, id);
            pst.setBoolean(8, true);
            pst.setString(9, photo);
            pst.execute();

            System.out.println("House Was Registered Successfully!");
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("erros "+e);
        }
    }


    public void rent() {
        try {
            LogIn login = new LogIn();
            int user_id = login.getUserID();
            //gets id from jtable view
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) fc.getCurrentInstance().getExternalContext().getRequest();
            String s_id = request.getParameter("id");
            int id = Integer.parseInt(s_id);
                //if house is not rented, update status from 0 to 1  
                String sql2 = "UPDATE houses set `Status` = 0 WHERE id =" + id;
                PreparedStatement pst2 = con.getConnection().prepareStatement(sql2);
                pst2.execute();
                //add new record to user_house by adding house id and user id
                String sql3 = "insert into user_house (`user_id`, `house_id`) VALUES (?, ?)";
                PreparedStatement pst3 = con.getConnection().prepareStatement(sql3);
                pst3.setInt(1, user_id);
                pst3.setInt(2, id);
                pst3.execute();
                System.out.println("House Rented!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }      
    }
    
    public String rentCheck(){
        ResultSet result;
        try {
            LogIn login = new LogIn();
            int user_id = login.getUserID();
            
            Statement pst = con.getConnection().createStatement();
            
            String sql = "SELECT * FROM user_house WHERE house_id = "+this.id;
            System.out.println(sql);
            
            result = pst.executeQuery(sql);
            
            int renter_id = 0 ;
            
            ResultSet rs1;
            if (result.next() == true) {
                System.out.println("This house is already rented.");
                this.state = "disabled";
                renter_id = result.getInt("user_id");
                System.out.println("renter "+renter_id);
            } 
            else if(renter_id == user_id) {
                String sql1 = null;
                PreparedStatement pst1 = con.getConnection().prepareStatement(sql1);
                rs1 = pst1.executeQuery();
                if (rs1.next() == true) {
                    System.out.println("This house is rented by someone else");
                    this.state = "disabled";
                }
            }

        } catch (Exception ex) {
            System.out.println("Error is " + ex);
        }
        return state;
    }
    public String rentBtn(int id){
        this.id = id;
        rentCheck();
        return this.state;
    }


    public void release(int user_id) {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) fc.getCurrentInstance().getExternalContext().getRequest();
            String id = request.getParameter("id");

            //select house id from table and check if the house is rented by this user
            //if yes, delete that record from user_table with user_id and house_id and update status from house from 0 to 1
            
            String sql = "select * from user_house where user_id = " + user_id + " and house_id = " + id;
            ResultSet rs2;
            PreparedStatement pst = con.getConnection().prepareStatement(sql);
            rs2 = pst.executeQuery();

            if (rs2.next() == true) {

                String sql1 = "delete from user_house where user_id = " + user_id + " and house_id = " + id;
                PreparedStatement pst1 = con.getConnection().prepareStatement(sql1);
                pst1.execute();

                String sql2 = "UPDATE houses set `Status`=1 WHERE id =" + id;
                PreparedStatement pst2 = con.getConnection().prepareStatement(sql2);
                pst2.execute();
                System.out.println("House Released!");
            } else {
                System.out.println("House not rented by you");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(String type, String country, String city, String street, String price, String capacity, int id, int user_id) {
        try {

            //select houses you own, and if they are not currently rented, you may update them
            
            
            String sql = "Select * from houses where created_by = '" + user_id + "' and id = '" + id + "' and status = '" + 1 + "'";
            PreparedStatement pst = con.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next() == true) {
                String sql1 = "update houses set `type` = ? , `country` = ?, `city` = ?, `street` = ?, `price` = ?,`capacity` = ? where id =" + id;
                PreparedStatement pst1 = con.getConnection().prepareStatement(sql1);
                pst1.setString(1, type);
                pst1.setString(2, country);
                pst1.setString(3, city);
                pst1.setString(4, street);
                pst1.setString(5, price);
                pst1.setString(6, capacity);
                pst1.execute();
                System.out.println("House Successfully Updated!");
            } else {
                System.out.println("This house is not yours, or is currently rented, so you cannot update it.");
            }

        } catch (Exception ex) {
            System.out.println("Error "+ ex);
        }
    }

}