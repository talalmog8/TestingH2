package com.example.demo.database;

import com.example.demo.models.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class DemoActions implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DemoActions.class);
    private final JdbcTemplate database;

    public DemoActions(@Autowired JdbcTemplate database) {
        this.database = database;
    }

    @Override
    public void run(String... args) {
        logger.debug("Querying Students");

        Student student = new Student("Tal", "Almog");
        String command = "INSERT INTO Students(firstName, lastName) VALUES ('%s','%s')";
        database.execute(String.format(command, student.getFirstName(), student.getLastName()));

        logger.debug("Querying for students");
        database.query("SELECT id, firstName, lastName FROM Students WHERE firstName = ?", new Object[]{"Tal"},
                (rs, rowNum) -> new Student(rs.getLong("id"), rs.getString("firstName"), rs.getString("lastName"))
        ).forEach(x -> logger.info(x.toString()));
    }
}
