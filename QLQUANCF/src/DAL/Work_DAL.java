package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.Work;

public class Work_DAL {
	private static final Connection connection ;
    private Statement statement ;
    private PreparedStatement preStatement;
    private ResultSet resultSet ;
    static{
        connection = connectionDB.getInstance().getConnection();
    }
public Work_DAL() {
        
    }
    
    private Work setInfor(ResultSet resultSet){
    	Work work = new Work();
        try {
            Integer id = resultSet.getInt("id_cv");
            String name = resultSet.getString("tencv");
            Float status = resultSet.getFloat("luongcb");
            
            work = new Work(id, name,status);
        } catch (SQLException ex) {
            Logger.getLogger(Work_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return work;
        
    }
    
    public List<Work> getAll() {
        
        final List<Work> works = new ArrayList<>();
        final String query = "select * from congviec";
            
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
            	Work work = setInfor(resultSet);
                
            	works.add(work);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Work_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Work_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return works;
    }

    public boolean update(Work work) {
        boolean result = false;
        
        String query = "update ban set tenban = ?, trangthai = ? where id_ban = ?;";
        
        if(work != null){
            try {
                preStatement = connection.prepareCall(query);
                preStatement.setString(1, work.getTen_cv());
                preStatement.setDouble(2, work.getLuong());
                preStatement.setInt(3, work.getId_cv());
                
                result = (preStatement.executeUpdate() == 0) ? false : true;
            } catch (SQLException ex) {
                Logger.getLogger(Work_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    preStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Work_DAL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return result;
    }

    public boolean remove(Integer id) {
        boolean result = false;
        
        String query = "delete from congviec where id_cv = ?;";
        try {
            preStatement = connection.prepareCall(query);
            preStatement.setInt(1, id);
            
            result = (preStatement.executeUpdate() == 0) ? false : true;
        } catch (SQLException ex) {
            Logger.getLogger(Work_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Work_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    public Work add(Work work) {
        
        String query = "insert into congviec "
                        + "( id_cv, tencv, luongcb)\n" 
                        + "value(?,?,?) ;";
        try {
            preStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preStatement.setInt(1, work.getId_cv());
            preStatement.setString(2, work.getTen_cv());
            preStatement.setDouble(3, work.getLuong());
            
            int count = preStatement.executeUpdate();
            if(count == 0){
            	work = null;
            }else{
                    resultSet = preStatement.getGeneratedKeys();
                    
                    resultSet.next();
                    
                    work.setId_cv(resultSet.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Work_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Work_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return work;
    }
}
