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

import DTO.Food;

public class Food_DAL {
	private static final Connection connection ;
    private Statement statement ;
    private PreparedStatement preStatement;
    private ResultSet resultSet ;
    static{
        connection = connectionDB.getInstance().getConnection();
    }
public Food_DAL() {
        
    }
    
    private Food setInfor(ResultSet resultSet){
    	Food food = new Food();
        try {
        	Integer id = resultSet.getInt("id_ma");
        	Integer id_td = resultSet.getInt("id_td");
            String name = resultSet.getString("tenmon");
            Float status = resultSet.getFloat("dongia");
            
            food = new Food(id, id_td, name,status);
        } catch (SQLException ex) {
            Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return food;
        
    }
    
    public List<Food> getAll() {
        
        final List<Food> foods = new ArrayList<>();
        final String query = "select * from monan";
            
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
            	Food food = setInfor(resultSet);
                
            	foods.add(food);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return foods;
    }
    
    public List<Food> searchFood(String s) {
        String a = "%" + s + "%";
        final List<Food> foods = new ArrayList<>();
        final String sql = "Select * from monan where id_ma like ? or id_td like ? or tenmon like ? or dongia like ? ";
            
        try {

        	preStatement = connection.prepareStatement(sql);
        	preStatement.setString(1, a);
        	preStatement.setString(2, a);
        	preStatement.setString(3, a);
        	preStatement.setString(4, a);
			
			resultSet = preStatement.executeQuery();
            while(resultSet.next()){
            	Food food = setInfor(resultSet);
                
            	foods.add(food);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
            	resultSet.close();
            	preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return foods;
    }
    
    public List<Food> sortByIdTD(Boolean tg) {
        
        final List<Food> foods = new ArrayList<>();
        final String sql ;
         	if(tg)
         		sql =  "Select * from monan order by id_td ASC";
         	else 
         		sql =  "Select * from monan order by id_td DESC";
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
            	Food food = setInfor(resultSet);
                
            	foods.add(food);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return foods;
    }

    public List<Food> sortByIdMA(Boolean tg) {
        
        final List<Food> foods = new ArrayList<>();
        final String sql ;
	        if(tg)
	     		sql =  "Select * from monan order by id_ma ASC";
	     	else 
	     		sql =  "Select * from monan order by id_ma DESC";
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
            	Food food = setInfor(resultSet);
                
            	foods.add(food);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return foods;
    }
    
    public List<Food> sortByTM(Boolean tg) {
        
        final List<Food> foods = new ArrayList<>();
        final String sql ;
	        if(tg)
	     		sql =  "Select * from monan order by tenmon ASC";
	     	else 
	     		sql =  "Select * from monan order by tenmon DESC";
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
            	Food food = setInfor(resultSet);
                
            	foods.add(food);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return foods;
    }

    public List<Food> sortByDG(Boolean tg) {
        
        final List<Food> foods = new ArrayList<>();
        final String sql ;
        	if(tg)
        		sql =  "Select * from monan order by dongia ASC";
        	else 
        		sql =  "Select * from monan order by dongia DESC";
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
            	Food food = setInfor(resultSet);
                
            	foods.add(food);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return foods;
    }

    public boolean update(Food food) {
        boolean result = false;
        
        String query = "update monan set id_td = ?, tenmon = ?, dongia = ? where id_ma = ?;";
        
        if(food != null){
            try {
                preStatement = connection.prepareCall(query);
                preStatement.setInt(1, food.getId_td());
                preStatement.setString(2, food.getTen_ma());
                preStatement.setDouble(3, food.getDongia());
                preStatement.setInt(4, food.getId_ma());
                
                result = (preStatement.executeUpdate() == 0) ? false : true;
            } catch (SQLException ex) {
                Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    preStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return result;
    }

    public boolean remove(Integer id) {
        boolean result = false;
        
        String query = "delete from monan where id_ma = ?;";
        try {
            preStatement = connection.prepareCall(query);
            preStatement.setInt(1, id);
            
            result = (preStatement.executeUpdate() == 0) ? false : true;
        } catch (SQLException ex) {
            Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    public void add(Food food) {
        
        String query = "insert into monan "
                        + "( id_ma, id_td, tenmon, dongia)\n" 
                        + "value(?,?,?,?) ;";
        try {
            preStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preStatement.setInt(1, food.getId_ma());
            preStatement.setInt(2, food.getId_td());
            preStatement.setString(3, food.getTen_ma());
            preStatement.setDouble(4, food.getDongia());
            
            preStatement.executeUpdate();	
            
        } catch (SQLException ex) {
            Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Food_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
