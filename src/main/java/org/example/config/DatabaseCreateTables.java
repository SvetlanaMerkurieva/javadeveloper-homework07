package org.example.config;

import org.example.elements.CreateTablesQuery;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCreateTables {
    public static void main(String[] args) {
        Connection conn = DatabaseH2.getInstance().getConnection();
        List<String> createTablesQueries = new ArrayList<>();
        createTablesQueries.add(CreateTablesQuery.CREATE_TABLE_WORKER.get());
        createTablesQueries.add(CreateTablesQuery.CREATE_TABLE_CLIENT.get());
        createTablesQueries.add(CreateTablesQuery.CREATE_TABLE_PROJECT.get());
        createTablesQueries.add(CreateTablesQuery.CONSTRAINT_PROJECT_CLIENT.get());
        createTablesQueries.add(CreateTablesQuery.CREATE_TABLE_PROJECT_WORKER.get());

        try (Statement st = conn.createStatement()) {
            for (String createTable: createTablesQueries) {
                st.executeUpdate(createTable);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Exception. Reason: %s", e.getMessage()));
            throw new RuntimeException("Can not run query.");
        }
    }
}
