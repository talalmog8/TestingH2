package com.example.demo.database.tables.students;

import com.example.demo.database.tables.TableCreator;
import com.example.demo.models.Student;
import com.example.demo.models.table.Table;
import com.example.demo.models.table.TableField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StudentsTableCreator extends TableCreator {

    public StudentsTableCreator(@Autowired JdbcTemplate database) {
        super(database);
    }

    @Override
    public void run(String... args) {
        create(new Table(Arrays.asList(
                new TableField("id", "SERIAL"),
                new TableField("firstName", "VARCHAR(255)"),
                new TableField("lastName", "VARCHAR(255)")),
                Student.CLASS_NAME));
    }
}
