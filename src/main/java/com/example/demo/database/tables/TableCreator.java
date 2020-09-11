package com.example.demo.database.tables;

import com.example.demo.models.table.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public abstract class TableCreator implements CommandLineRunner {

    private final String DROP_IF_EXISTING = "DROP TABLE %s IF EXISTS";
    private final String CREATE_TABLE = "CREATE TABLE %s(%s)";
    private final JdbcTemplate _database;

    public TableCreator(@Autowired JdbcTemplate database) {
        _database = database;
    }

    protected void create(Table table) {
        _database.execute(String.format(DROP_IF_EXISTING, table.getName()));
        _database.execute(String.format(CREATE_TABLE, table.getName(), table.getFieldsString()));
    }
}
