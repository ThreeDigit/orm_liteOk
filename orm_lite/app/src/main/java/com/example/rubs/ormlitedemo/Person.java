package com.example.rubs.ormlitedemo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "personInformation")
 class Person {

    @DatabaseField(generatedId = true, columnName = "id")
    public int id;

    @DatabaseField(columnName = "name")
    public String name;

    @DatabaseField(columnName = "email")
    public String email;

    Person() {

    }

    public Person(final String name, final String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
