package com.mihai.ejb.clasicjdbc;

import com.mihai.ejb.Database;
import com.mihai.hibernate.entity.Product;
import com.mihai.qualifier.JDBCDatabase;
import com.mihai.util.DBProperties;
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

    @Override
    public List<Product> queryProducts() {
        List<Product> listOfProducts = new ArrayList<>();
        this.connect();
        // Procedura stocata, ca sa nu repet aceleasi validari in cod de doua ori.......

        try(PreparedStatement ps = this.connection.prepareStatement("select * from product")){
            ResultSet result = ps.executeQuery();

            while(result.next()){
               // listOfProducts.add(new Product());
                System.out.println(result.getInt("id"));
                System.out.println(result.getString("pName"));
                System.out.println(result.getString("pType"));
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
        try(CallableStatement call = this.connection.prepareCall("call checkuser (?,?)")) {
            call.setString(1, username);
            call.registerOutParameter(2, Types.VARCHAR);
            call.executeQuery();
            System.out.println(call.getString(2));
            if(BCrypt.checkpw(password, call.getString(2)))
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
