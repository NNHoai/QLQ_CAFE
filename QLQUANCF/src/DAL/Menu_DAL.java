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

import DTO.Menu;

public class Menu_DAL {
	private static final Connection connection ;
    private Statement statement ;
    private PreparedStatement preStatement;
    private ResultSet resultSet ;
    static{
        connection = connectionDB.getInstance().getConnection();
    }
public Menu_DAL() {
        
    }
    
    private Menu setInfor(ResultSet resultSet){
    	Menu menu = new Menu();
        try {
            Integer id = resultSet.getInt("id_td");
            String name = resultSet.getString("ten_td");
            
            menu = new Menu(id, name);
        } catch (SQLException ex) {
            Logger.getLogger(Menu_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menu;
        
    }
    
    public List<Menu> getAll() {
        
        final List<Menu> menus = new ArrayList<>();
        final String query = "select * from thucdon";
            
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
            	Menu menu = setInfor(resultSet);
                
            	menus.add(menu);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Menu_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return menus;
    }

    public boolean update(Menu menu) {
        boolean result = false;
        
        String query = "update thucdon set ten_td = ? where id_td = ?;";
        
        if(menu != null){
            try {
                preStatement = connection.prepareCall(query);
                preStatement.setString(1, menu.getTen_td());
                preStatement.setInt(2, menu.getId_td());
                
                result = (preStatement.executeUpdate() == 0) ? false : true;
            } catch (SQLException ex) {
                Logger.getLogger(Menu_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    preStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Menu_DAL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return result;
    }

    public boolean remove(Integer id) {
        boolean result = false;
        
        String query = "delete from thucdon where id_td = ?;";
        try {
            preStatement = connection.prepareCall(query);
            preStatement.setInt(1, id);
            
            result = (preStatement.executeUpdate() == 0) ? false : true;
        } catch (SQLException ex) {
            Logger.getLogger(Menu_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Menu_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    public void add(Menu menu) {
        
        String query = "insert into thucdon "
                        + "( id_td, ten_td)\n" 
                        + "value(?,?) ;";
        try {
            preStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preStatement.setInt(1, menu.getId_td());
            preStatement.setString(2, menu.getTen_td());
            preStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Menu_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        return menu;
    }
}
