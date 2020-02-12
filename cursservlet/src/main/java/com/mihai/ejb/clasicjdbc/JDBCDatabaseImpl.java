package com.mihai.ejb.clasicjdbc;

import com.mihai.ejb.Database;
import com.mihai.hibernate.entity.Product;
import com.mihai.hibernate.entity.ProductType;
import com.mihai.qualifier.JDBCDatabase;
import com.mihai.util.DBProperties;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.spi.SQLExceptionConversionDelegate;
import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ApplicationScoped
@JDBCDatabase
public class JDBCDatabaseImpl implements Database {

    private int dbId;

    private Connection connection;

    public JDBCDatabaseImpl(){
        this.dbId = new Random().nextInt(400);
    }

    @Override
    public void getMsg(){
        System.out.println("Mesajjj!!! from ejb " + Integer.toString(dbId));
    }


    private void connect() {
        try{
            Class.forName(DBProperties.driver);
            this.connection = DriverManager.getConnection(DBProperties.connectionString, DBProperties.user, DBProperties.password);
        }catch(ClassNotFoundException exc){
            exc.printStackTrace();
        }catch(SQLException exc){
            exc.printStackTrace();
        }
    }

    private void disconnect(){
        if(this.connection != null)
            try{
                this.connection.close();
            }catch(SQLException exc){
                exc.printStackTrace();
            }

    }

    @Override
    public List<Product> queryProducts() {
        List<Product> listOfProducts = new ArrayList<>();
        this.connect();
        // Procedura stocata, ca sa nu repet aceleasi validari in cod de doua ori.......

        try(CallableStatement ps = this.connection.prepareCall("call retrieveProducts()")){
            ps.executeQuery();
            ResultSet result = ps.getResultSet();

            while(result.next()){
                listOfProducts.add(new Product(result.getInt("id"),result.getString("pName"),
                                    new ProductType(result.getString("pType"))));
            }
        }catch(SQLException exc){
            exc.printStackTrace();
        }

        this.disconnect();
        return listOfProducts;
    }

    @Override
    public void saveProduct() {

    }

    @Override
    public boolean findUserByCredentials(String username, String password) {
        this.connect();
        try(CallableStatement call = this.connection.prepareCall("call checkuser (?,?)")) {
            call.setString(1, username);
            call.registerOutParameter(2, Types.VARCHAR);
            call.executeQuery();
            System.out.println(call.getString(2));
            if(BCrypt.checkpw(password, call.getString(2))) {
                this.connection.close();
                return true;
            }
        }catch(SQLException exc){
            exc.printStackTrace();
        }
        this.disconnect();
        return false;
    }

    @Override
    public void addProduct() {

    }

    @Override
    public void registerAnOrder(String user, List<String> products) {
        this.connect();

        try(CallableStatement call = this.connection.prepareCall("call createorder (?,?)")){
            call.setString(1, user);
            call.registerOutParameter(2, Types.INTEGER);
            call.executeQuery();
            if(call.getInt(2) != -1)
                this.registerOrderProducts(call.getInt(2), products);
        }catch(SQLException exc){
            exc.printStackTrace();
        }
        this.disconnect();
    }

    private void registerOrderProducts(int orderId, List<String> products){

        try(CallableStatement call = this.connection.prepareCall("call addorderinfo (?,?)")){
            products.stream().forEach(p->{
                try{
                    call.setInt(1, orderId);
                    call.setString(2, p);
                    call.execute();
                }catch(SQLException exc){
                    exc.printStackTrace();
                }
            });
        }catch(SQLException exc){
            exc.printStackTrace();
        }
    }

    @Override
    public void queryProduct(int id) {
    }
}
