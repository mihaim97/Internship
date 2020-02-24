package com.mihai.project.library.dao.jdbctemplate;

import com.mihai.project.library.dao.UserDAO;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("UserDaoJDBCTemplateImplementation")
@Qualifier("UserDaoJdbcTemplate")
public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public User addUser(User user) {
        User userExisting = queryUser(user.getUsername());
        if(userExisting == null){
            saveNewUser(user);
            return user;
        }
       return null;
    }

    @Override
    public User queryUser(String username) {
        User userExist = null;
        try{
            userExist = jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_USER, new Object[]{username}, (res, num)->
                    new User(res.getString(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5)));
        }catch(EmptyResultDataAccessException exc){ }
        return userExist;
    }

    @Override
    public List<User> queryAllUsers() {
        List<User> listOfUsers = null;
            listOfUsers = jdbcTemplate.query(MyQuery.QUERY_ALL_USERS, (res, num)->
                    new User(res.getString(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5)));
        return listOfUsers;
    }

    @Override
    public boolean emailAlreadyExist(String email) {
        User userExist = null;
        try{
            userExist = jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_USER_BY_EMAIL, new Object[]{email}, (res, num)-> new User());
        }catch(EmptyResultDataAccessException exc){ }
        if(userExist == null)
            return false;
        return true;
    }

    @Override
    public boolean emailAlreadyExistOnDifferentUser(String currentUsername, String email) {
        User userExistWithCurrentEmail = null;
        try{
            userExistWithCurrentEmail = jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_USER_BY_EMAIL_EXCEPT_CURRENT_USER, new Object[]{email, currentUsername}, (res, num)-> new User());
        }catch(EmptyResultDataAccessException exc){ }
        if(userExistWithCurrentEmail == null)
            return false;
        return true;
    }

    @Override
    public boolean usernameAlreadyExistOnDifferentUser(String currentUsername, String newUsername) {
        User userExistWithCurrentUsername = null;
        try{
            userExistWithCurrentUsername = jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_USER_BY_USERNAME_EXCEPT_CURRENT_USER, new Object[]{newUsername, currentUsername}, (res, num)-> new User());
        }catch(EmptyResultDataAccessException exc){ }
        if(userExistWithCurrentUsername == null)
            return false;
        return true;
    }

    @Override
    public boolean deleteUser(String username) {
        return jdbcTemplate.update(MyQuery.DELETE_USER, new Object[]{username}) == 1;
    }

    @Override
    public User updateUser(User user, String username) {
        if(queryUser(username) == null)
            return null;
        else
            return updateUserUsingData(user, username);
    }

    private void saveNewUser(User user){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.USER);
        insert.execute(populateUserValues(user));
    }

    private Map<String, Object> populateUserValues(User user){
        Map<String, Object> values = new HashMap<>();
        values.put(MyTable.USER_ID, user.getUsername());
        values.put(MyTable.USER_PASSWORD, DigestUtils.md5Hex(user.getPassword()));
        values.put(MyTable.USER_EMAIL, user.getEmail());
        values.put(MyTable.USER_ENABLE, user.getEnable());
        values.put(MyTable.USER_ROLE, user.getRole());
        return  values;
    }

    private User updateUserUsingData(User user, String username){
        jdbcTemplate.update(MyQuery.UPDATE_USER, user.getUsername(), DigestUtils.md5Hex(user.getPassword()), user.getEmail(), user.getRole(), username);
        return queryUser(user.getUsername());
    }
}
