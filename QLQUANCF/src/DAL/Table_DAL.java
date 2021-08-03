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

import DTO.Staff;
import DTO.Table;

public class Table_DAL {
	private static final Connection connection ;
    private Statement statement ;
    private PreparedStatement preStatement;
    private ResultSet resultSet ;
    static{
        connection = connectionDB.getInstance().getConnection();
    }
public Table_DAL() {
        
    }
    
    private Table setInfor(ResultSet resultSet){
    	Table table = new Table();
        try {
            Integer id = resultSet.getInt("id_ban");
            String name = resultSet.getString("tenban");
            String status = resultSet.getString("trangthai");
            
            table = new Table(id, name,status);
        } catch (SQLException ex) {
            Logger.getLogger(Table_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return table;
        
    }
    
    public List<Table> getAll() {
        
        final List<Table> tables = new ArrayList<>();
        final String query = "select * from ban";
            
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
            	Table table = setInfor(resultSet);
                
            	tables.add(table);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Table_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Table_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return tables;
    }

    public boolean update(Table table) {
        boolean result = false;
        
        String query = "update ban set tenban = ?, trangthai = ? where id_ban = ?;";
        
        if(table != null){
            try {
                preStatement = connection.prepareCall(query);
                preStatement.setString(1, table.getTen_ban());
                preStatement.setString(2, table.getStatus());
                preStatement.setInt(3, table.getId_ban());
                
                result = (preStatement.executeUpdate() == 0) ? false : true;
            } catch (SQLException ex) {
                Logger.getLogger(Table_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    preStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Table_DAL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return result;
    }

    public boolean updateStatusBusy(int id_b) {
        boolean result = false;
        
        String query = "update ban set trangthai = 'Bận' where id_ban = ?;";
        
            try {
                preStatement = connection.prepareCall(query);
                preStatement.setInt(1, id_b);
                
                result = (preStatement.executeUpdate() == 0) ? false : true;
            } catch (SQLException ex) {
                Logger.getLogger(Table_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    preStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Table_DAL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    public boolean updateStatusEmpty(int id_b) {
        boolean result = false;
        
        String query = "update ban set trangthai = 'Trống' where id_ban = ?;";
        
            try {
                preStatement = connection.prepareCall(query);
                preStatement.setInt(1, id_b);
                
                result = (preStatement.executeUpdate() == 0) ? false : true;
            } catch (SQLException ex) {
                Logger.getLogger(Table_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    preStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Table_DAL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }

    public boolean remove(Integer id) {
        boolean result = false;
        
        String query = "delete from ban where id_ban = ?;";
        try {
            preStatement = connection.prepareCall(query);
            preStatement.setInt(1, id);
            
            result = (preStatement.executeUpdate() == 0) ? false : true;
        } catch (SQLException ex) {
            Logger.getLogger(Table_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Table_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    public Table add(Table table) {
        
        String query = "insert into ban "
                        + "( id_ban, tenban, trangthai)\n" 
                        + "value(?,?,?) ;";
        try {
            preStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preStatement.setInt(1, table.getId_ban());
            preStatement.setString(2, table.getTen_ban());
            preStatement.setString(3, table.getStatus());
            
            int count = preStatement.executeUpdate();
            if(count == 0){
            	table = null;
            }else{
                    resultSet = preStatement.getGeneratedKeys();
                    
                    resultSet.next();
                    
                    table.setId_ban(resultSet.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Table_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Table_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return table;
    }
}
