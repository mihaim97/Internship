package com.mihai.hibernate.dao;

import com.mihai.hibernate.session.HibernateSession;
import com.mihai.hibernate.entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.inject.Inject;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Inject
    private HibernateSession hibernateSession; // !! EntityManager si atunci o sa pot schimba providerul de JPA

    @Override
    public List<Product> queryProduct() {
        Transaction hibernateTransaction = null;
        List<Product> productsList = null;
        try( Session hibernateSession  = this.hibernateSession.createSession()){
            hibernateTransaction = hibernateSession.getTransaction();
            hibernateTransaction.begin();
            Query<Product> query = hibernateSession.createQuery("from Product");
            productsList = query.getResultList();
            hibernateTransaction.commit();
        }catch(Exception exc){
            if(hibernateTransaction != null) hibernateTransaction.rollback();
            exc.printStackTrace();
        }
        return productsList;
    }

    @Override
    public void saveOrUpdateProduct(Product prod) {
        Transaction hibernateTransaction = null;
        try( Session hibernateSession  = this.hibernateSession.createSession()){
            hibernateTransaction = hibernateSession.getTransaction();
            hibernateTransaction.begin();
            hibernateSession.save(prod);
            hibernateTransaction.commit();
        }catch(Exception exc){
            if(hibernateTransaction != null) hibernateTransaction.rollback();
            exc.printStackTrace();
        }
    }

}
