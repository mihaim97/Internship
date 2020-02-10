package com.mihai.ejb.clasicjdbc;

import com.mihai.ejb.Database;
import com.mihai.hibernate.entity.Product;
import com.mihai.qualifier.JDBCDatabase;
import com.mihai.util.DBProperties;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
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

    @Override
    public List<Product> queryProducts() {
        this.connect();
        // Procedura stocata, ca sa nu repet aceleasi validari in cod de doua ori.......

        try(PreparedStatement ps = this.connection.prepareStatement("select * from product")){
            ResultSet result = ps.executeQuery();

            while(result.next()){
                System.out.println(result.getInt("id"));
                System.out.println(result.getString("pName"));
            }
            this.connection.close();
        }catch(SQLException exc){
            exc.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveProduct() {

    }

    @Override
    public boolean findUserByCredentials(String username, String password) {
        this.connect();
        try(CallableStatement call = this.connection.prepareCall("call checkuser (?,?,?)")) {

            call.setString(1, username);
            call.setString(2, password);
            call.registerOutParameter(3, Types.INTEGER);
            call.executeQuery();
            if(call.getInt(3) == 1)
                return true;
        }catch(SQLException exc){
            exc.printStackTrace();
        }
        return false;
    }

    @Override
    public void queryProduct(int id) {

    }
}
