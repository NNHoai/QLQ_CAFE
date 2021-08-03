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

import DTO.BillOrder;
import DTO.Bill_Details;

public class BillDetails_DAL {
	private static final Connection connection ;
    private Statement statement ;
    private PreparedStatement preStatement;
    private ResultSet resultSet ;
    private CallableStatement casm;
    static{
        connection = connectionDB.getInstance().getConnection();
    }
public BillDetails_DAL() {
        
    }
    
    private Bill_Details setInfor(ResultSet resultSet){
    	Bill_Details bill_d = new Bill_Details();
        try {
        	Integer id_cthd = resultSet.getInt("id_cthd");
            Integer id_hd = resultSet.getInt("id_hd");
            Integer id_ma = resultSet.getInt("id_ma");
            Integer soluong = resultSet.getInt("soluong");
            
            bill_d = new Bill_Details(id_cthd, id_hd, id_ma, soluong);
        } catch (SQLException ex) {
            Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bill_d;
        
    }
    private BillOrder setBillOrder(ResultSet resultSet){
    	BillOrder bill_d = new BillOrder();
        try {
            String name = resultSet.getString("tenmon");
            Integer sl = resultSet.getInt("soluong");
            Float dg = resultSet.getFloat("dongia");
            Float tt = resultSet.getFloat("thanhtien");
            
            bill_d = new BillOrder(name, sl, dg, tt);
        } catch (SQLException ex) {
            Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bill_d;
        
    }
    
    public List<Bill_Details> getAll() {
        
        final List<Bill_Details> list_Bd = new ArrayList<>();
        final String query = "select * from chitiethoadon";
            
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
            	Bill_Details Bd = setInfor(resultSet);
                
            	list_Bd.add(Bd);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return list_Bd;
    }
    public List<BillOrder> getlsBillDbyIDBill(int idB)
    {
        final List<BillOrder> list_Bd = new ArrayList<>();
        final String query = "call getBillOrder(?)";
        try {
            casm = connection.prepareCall(query);
            casm.setInt(1, idB);
            
            resultSet = casm.executeQuery();
            
            while(resultSet.next()){
            	BillOrder Bd = setBillOrder(resultSet);
                
            	list_Bd.add(Bd);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
            	casm.close();
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return list_Bd;
    }
//    public List<BillOrder> getlsBillDbyIDBill(int idB)
//    {
//        final List<BillOrder> list_Bd = new ArrayList<>();
//        final String query = "call getBillOrder("+ idB +")";
//        try {
//
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(query);
//            
//            while(resultSet.next()){
//            	BillOrder Bd = setBillOrder(resultSet);
//                
//            	list_Bd.add(Bd);
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        finally{
//            try {
//                resultSet.close();
//                statement.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        }
//        return list_Bd;
//    }
//
//    public boolean update(Bill_Details bd) {
//        boolean result = false;
//        
//        String query = "update chitiethoadon set id_hd = ?, id_ma = ?, soluong = ? where id_cthd = ?;";
//        
//        if(bd != null){
//            try {
//                preStatement = connection.prepareCall(query);
//                preStatement.setInt(1, bd.getId_hd());
//                preStatement.setInt(2, bd.getId_ma());
//                preStatement.setInt(3, bd.getSoluong());
//                preStatement.setInt(4, bd.getId_cthd());
//                
//                result = (preStatement.executeUpdate() == 0) ? false : true;
//            } catch (SQLException ex) {
//                Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            finally{
//                try {
//                    preStatement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//        
//        return result;
//    }
    public boolean update(int sl, int id_cthd) {
        boolean result = false;
        
        String query = "update chitiethoadon set soluong = ? where id_cthd = ?;";
        
        if(sl != 0){
            try {
                preStatement = connection.prepareCall(query);
                preStatement.setInt(1, sl);
                preStatement.setInt(2, id_cthd);
                
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
        }
        
        return result;
    }

//    public boolean remove(String id) {
//        boolean result = false;
//        
//        String query = "delete from chitiethoadon where id_cthd = ?;";
//        try {
//            preStatement = connection.prepareCall(query);
//            preStatement.setString(1, id);
//            
//            result = (preStatement.executeUpdate() == 0) ? false : true;
//        } catch (SQLException ex) {
//            Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
//        }finally{
//            try {
//                preStatement.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//        return result;
//    }
    public boolean del(int id_hd, int id_ma, int sl) {
        boolean result = false;
        
        String query = "delete from chitiethoadon where id_hd = ? and id_ma = ? and soluong = ?;";
        try {
            preStatement = connection.prepareCall(query);
            preStatement.setInt(1, id_hd);
            preStatement.setInt(2, id_ma);
            preStatement.setInt(3, sl);
            
            result = (preStatement.executeUpdate() == 0) ? false : true;
        } catch (SQLException ex) {
            Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    public boolean add(int id_hd, int id_ma, int sl) {
    	boolean result = false;
        
        String query = "insert into chitiethoadon "
                        + "( id_hd, id_ma, soluong)\n" 
                        + "value(?,?,?) ;";
        try {
            preStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preStatement.setInt(1, id_hd);
            preStatement.setInt(2, id_ma);
            preStatement.setInt(3, sl);
            
            result = (preStatement.executeUpdate() == 0) ? false : true;
        } catch (SQLException ex) {
            Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(BillDetails_DAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
