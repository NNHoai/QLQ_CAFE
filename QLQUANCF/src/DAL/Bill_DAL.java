package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.Bill;

public class Bill_DAL {
	private static final Connection connection ;
    private Statement statement ;
    private PreparedStatement preStatement;
    private ResultSet resultSet ;
    static{
        connection = connectionDB.getInstance().getConnection();
    }
public Bill_DAL() {
        
    }
    
    private Bill setInfor(ResultSet resultSet){
    	Bill bill = new Bill();
        try {
        	Integer id = resultSet.getInt("id_hd");
            Integer id_nv = resultSet.getInt("id_nv");
        	Integer id_b = resultSet.getInt("id_ban");
        	Double tongtien = resultSet.getDouble("tongtien");
            Date date = resultSet.getDate("ngaygio");
            String status = resultSet.getString("trangthai");
            
            bill = new Bill(id,id_nv,id_b,tongtien,date,status);
        } catch (SQLException ex) {
            Logger.getLogger(Bill_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bill;
    }
    
    public List<Bill> getAll() {
        
        final List<Bill> bills = new ArrayList<>();
        final String query = "select * from hoadon";
            
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
            	Bill bill = setInfor(resultSet);
                
            	bills.add(bill);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Bill_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Bill_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return bills;
    }

    public boolean update(int id, Float tt) {
        boolean result = false;
        
        String query = "update hoadon set tongtien = ?, trangthai = ? where id_hd = ?;";
            try {
                preStatement = connection.prepareCall(query);
                preStatement.setDouble(1, tt);
                preStatement.setString(2,"Đã thanh toán");
                preStatement.setInt(3, id);
                
                result = (preStatement.executeUpdate() == 0) ? false : true;
            } catch (SQLException ex) {
                Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    preStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
        
        
    
    public boolean add(int id_nv, int id_b) {
    	boolean result = false;
    	
        String query = "insert into hoadon "
                        + "( id_nv, id_ban, tongtien, ngaygio, trangthai)\n" 
                        + "value(?,?,0,?,?) ;";
        try {
        	Date date = new java.sql.Date(System.currentTimeMillis());
            preStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preStatement.setInt(1,id_nv);
            preStatement.setInt(2, id_b);
            preStatement.setDate(3, date);
            preStatement.setString(4, "Chưa thanh toán");
            
            result = (preStatement.executeUpdate() == 0) ? false : true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Bill_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Bill_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
