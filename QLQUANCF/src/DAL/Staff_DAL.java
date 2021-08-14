package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.Staff;

public class Staff_DAL {
	private static final Connection connection ;
    private Statement statement ;
    private PreparedStatement preStatement;
    private CallableStatement cstm;
    private ResultSet resultSet ;
    static{
        connection = connectionDB.getInstance().getConnection();
    }
public Staff_DAL() {
        
    }
    
    private Staff setInfor(ResultSet resultSet){
    	Staff staff = new Staff();
        try {
            Integer id_nv = resultSet.getInt("id_nv");
            Integer id_cv = resultSet.getInt("id_cv");
            String name = resultSet.getString("ten_nv");
            String phone = resultSet.getString("lienhe");
            String address = resultSet.getString("diachi");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            Float hesol = resultSet.getFloat("hesoluong");
            Integer casang = resultSet.getInt("casang");
            Integer cachieu = resultSet.getInt("cachieu");
            Integer catoi = resultSet.getInt("catoi");
            
            staff = new Staff(id_nv, id_cv, name, phone, address, username, password, hesol, casang, cachieu, catoi);
        } catch (SQLException ex) {
            Logger.getLogger(Staff_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return staff;
        
    }
    
    public List<Staff> getAll() {
        
        final List<Staff> staffs = new ArrayList<>();
        final String query = "select * from nhanvien";
            
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
            	Staff staff = setInfor(resultSet);
                
            	staffs.add(staff);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Staff_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Staff_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return staffs;
    }
    
 public Float getLuong(Integer tsc, Integer id_nv) {
        
	 	Float luong = 0f;
        final String query = "{call getLuong(?,?,?)}";
            
        try {

        	cstm = connection.prepareCall(query);
        	cstm.setInt(1, tsc);
        	cstm.setInt(2, id_nv);
        	cstm.registerOutParameter(3, java.sql.Types.NVARCHAR);
        	cstm.execute();
        	luong = cstm.getFloat(3);
   
        } catch (SQLException ex) {
            Logger.getLogger(Staff_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                cstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(Staff_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return luong;
    }

    public boolean update(Staff staff) {
        boolean result = false;
        
        String query = "update nhanvien set id_cv = ?, ten_nv = ?, lienhe = ?, diachi = ?, username = ?, password = ?, hesoluong = ?, casang = ?, cachieu = ?, catoi = ? where id_nv = ?;";
        
        if(staff != null){
            try {
                preStatement = connection.prepareCall(query);
                preStatement.setInt(1, staff.getId_cv());
                preStatement.setString(2, staff.getTen_nv());
                preStatement.setString(3, staff.getLienhe());
                preStatement.setString(4, staff.getDiachi());
                preStatement.setString(5, staff.getUsername());
                preStatement.setString(6, staff.getPassword());
                preStatement.setFloat(7, staff.getHesol());
                preStatement.setInt(8, staff.getCasang());
                preStatement.setInt(9, staff.getCachieu());
                preStatement.setInt(10, staff.getCatoi());
                preStatement.setInt(11, staff.getId_nv());
                
                result = (preStatement.executeUpdate() == 0) ? false : true;
            } catch (SQLException ex) {
                Logger.getLogger(Staff_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    preStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Staff_DAL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return result;
    }

    public boolean remove(Integer id) {
        boolean result = false;
        
        String query = "delete from nhanvien where id_nv = ?;";
        try {
            preStatement = connection.prepareCall(query);
            preStatement.setInt(1, id);
            
            result = (preStatement.executeUpdate() == 0) ? false : true;
        } catch (SQLException ex) {
            Logger.getLogger(Staff_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Staff_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    public Staff add(Staff staff) {
        
        String query = "insert into nhanvien "
                        + "( id_cv, ten_nv, lienhe, diachi, username, password, hesoluong, casang, cachieu, catoi)\n" 
                        + "value(?,?,?,?,?,?,?,?,?,?) ;";
        try {
            preStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preStatement.setInt(1, staff.getId_cv());
            preStatement.setString(2, staff.getTen_nv());
            preStatement.setString(3, staff.getLienhe());
            preStatement.setString(4, staff.getDiachi());
            preStatement.setString(5, staff.getUsername());
            preStatement.setString(6, staff.getPassword());
            preStatement.setFloat(7, staff.getHesol());
            preStatement.setInt(8, staff.getCasang());
            preStatement.setInt(9, staff.getCachieu());
            preStatement.setInt(10, staff.getCatoi());
            
            int count = preStatement.executeUpdate();
            if(count == 0){
            	staff = null;
            }else{
                    resultSet = preStatement.getGeneratedKeys();
                    
                    resultSet.next();
                    
                    staff.setId_nv(resultSet.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Staff_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Staff_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return staff;
    }
}
