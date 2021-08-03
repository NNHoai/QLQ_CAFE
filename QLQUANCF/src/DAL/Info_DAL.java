package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.info;

public class Info_DAL {
	private static final Connection connection ;
    private Statement statement ;
    private PreparedStatement preStatement;
    private ResultSet resultSet ;
    static{
        connection = connectionDB.getInstance().getConnection();
    }
    public Info_DAL() {
        
    }
    
//    private info setInfor(ResultSet resultSet){
//    	info inf = new info();
//    try {
//        String ten = resultSet.getString("tencuahang");
//        String lh = resultSet.getString("lienhe");
//        String dc = resultSet.getString("diachi");
//        
//        inf = new info(ten, lh, dc);
//    } catch (SQLException ex) {
//        Logger.getLogger(Info_DAL.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    return inf;
//    
//}
    
    public info getInfo() {
        
        info inf = new info();
        final String query = "select * from thongtin";
            
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            	Integer stt = resultSet.getInt("stt");
            	String ten = resultSet.getString("tencuahang");
                String lh = resultSet.getString("lienhe");
                String dc = resultSet.getString("diachi");
                inf = new info(stt,ten, lh, dc);     
            }
                            
            
        } catch (SQLException ex) {
            Logger.getLogger(Info_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Info_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return inf;
    }

    public boolean update(info inf) {
        boolean result = false;
        
        String query = "update thongtin set tencuahang = ?, lienhe = ?, diachi = ? where stt = 1;";
        
        if(inf != null){
            try {
                preStatement = connection.prepareCall(query);
                preStatement.setString(1, inf.getTen());
                preStatement.setString(2, inf.getLienhe());
                preStatement.setString(3, inf.getDiachi());
                
                result = (preStatement.executeUpdate() == 0) ? false : true;
            } catch (SQLException ex) {
                Logger.getLogger(Info_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    preStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Info_DAL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return result;
    }
//
//    public boolean remove(Integer stt) {
//        boolean result = false;
//        
//        String query = "delete from thongtin where stt = ?;";
//        try {
//            preStatement = connection.prepareCall(query);
//            preStatement.setInt(1, stt);
//            
//            result = (preStatement.executeUpdate() == 0) ? false : true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Info_DAL.class.getName()).log(Level.SEVERE, null, ex);
//        }finally{
//            try {
//                preStatement.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(Info_DAL.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//        return result;
//    }

    public boolean add(info inf) {
    	boolean result = false;
    	
        String query = "insert into thongtin "
                        + "( stt, tencuahang, lienhe, diachi)\n" 
                        + "value(?,?,?,?) ;";
        try {
            preStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preStatement.setInt(1, inf.getStt());
            preStatement.setString(2, inf.getTen());
            preStatement.setString(3, inf.getLienhe());
            preStatement.setString(4, inf.getDiachi());
            
            result = (preStatement.executeUpdate() == 0) ? false : true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Info_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Info_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
