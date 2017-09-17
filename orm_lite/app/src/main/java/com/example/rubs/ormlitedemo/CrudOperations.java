package com.example.rubs.ormlitedemo;

import android.content.Context;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.List;

class CrudOperations {

    /* Here We Perform Create(insert) Read Update Delete Operations */

    private DatabaseHelper database_helper = null;
    private Dao<Person, Integer> personDAO = null;
    private Context context;

    CrudOperations(Context context) {
        this.context = context;
    }

    //initialize and return database helper class variable
    private DatabaseHelper getHelper() {
        if (database_helper == null) {
            database_helper = OpenHelperManager
                    .getHelper(this.context, DatabaseHelper.class);
        }
        return database_helper;
    }

    //get Person Data Access Object
    private Dao<Person, Integer> getPersonDAO()
            throws android.database.SQLException, java.sql.SQLException {

        if (personDAO == null) {
            personDAO = getHelper().getDao(Person.class);
        }
        return personDAO;
    }

    //Insert Data to Db through DAO object
    public int insertData(Person person) {
        int id = 0;
        Dao<Person, Integer> simpleDAO;
        try {
            simpleDAO = getPersonDAO();
            id = simpleDAO.create(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    //Display all Data in Db through DAO object
    public List<Person> getAllData() {
        List<Person> personList = null;
        Dao<Person, Integer> simpleDAO;

        try {
            simpleDAO = getPersonDAO();
            personList = simpleDAO.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    //Delete from db data through DAO object
    public void deleteData(Person person) {
        Dao<Person, Integer> simpleDAO;
        try {
            simpleDAO = getPersonDAO();
            simpleDAO.delete(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //get single record based on ID
    public Person getSingleRecord(int personId) {
        Dao<Person, Integer> simpleDAO;
        List<Person> singleRecord = null;
        Person record = null;

        try {
            simpleDAO = getPersonDAO();
            QueryBuilder<Person, Integer> queryBuilder
                    = simpleDAO.queryBuilder();

            queryBuilder.where().eq("id", personId);
            singleRecord = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (singleRecord != null) {
            record = singleRecord.get(0);
        }
        return record;
    }


    //method for delete all rows
    public void deleteAll() {
        Dao<Person, Integer> simpleDAO = null;

        List<Person> list = null;
        try {
            simpleDAO = getPersonDAO();
            list = simpleDAO.queryForAll();
            simpleDAO.delete(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //close database to avoid Null pointer error or memory leak
    public void closeDb() {
        if (database_helper != null) {
            OpenHelperManager.releaseHelper();
            database_helper = null;
        }
    }
}
