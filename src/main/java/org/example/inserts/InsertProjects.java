package org.example.inserts;

import org.example.config.DatabaseH2;
import org.example.elements.PopulateQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class InsertProjects {
    private static final String INSERT_PROJECTS = PopulateQuery.POPULATE_TABLE_PROJECT.get();
    private PreparedStatement st;
    public InsertProjects (Connection conn) {
        try {
            this.st = conn.prepareStatement(INSERT_PROJECTS);
        } catch (SQLException e) {
            System.out.println("Can not create statement. Reason: " + e.getMessage());
        }
    }
    public int saveProject(int id, int clientId, LocalDate startDate, LocalDate finishDate) {

        try {
            this.st.setInt(1, id);
            this.st.setInt(2, clientId);
            this.st.setDate(3, java.sql.Date.valueOf(startDate));
            this.st.setDate(4, java.sql.Date.valueOf(finishDate));
            return this.st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fail. Reason: " + e.getMessage());
        }
        return -1;
    }
    public static void main(String[] args) {
        Connection conn = DatabaseH2.getInstance().getConnection();
        InsertProjects insertProjects = new InsertProjects(conn);
        insertProjects.saveProject(1, 1, LocalDate.of(2024, 2,10), LocalDate.of(2024, 6,20));
        insertProjects.saveProject(2,2, LocalDate.of(2024, 3,1), LocalDate.of(2025, 3,1));
        insertProjects.saveProject(3, 3, LocalDate.of(2024, 2,20), LocalDate.of(2024, 3,20));
        insertProjects.saveProject(4,3, LocalDate.of(2024, 3,25), LocalDate.of(2026, 3,25) );
        insertProjects.saveProject(5, 3, LocalDate.of(2024, 4,1), LocalDate.of(2026, 6,1));
        insertProjects.saveProject(6, 4, LocalDate.of(2024, 6,1), LocalDate.of(2025, 8,1));
        insertProjects.saveProject(7,4, LocalDate.of(2024, 6,1), LocalDate.of(2027, 8,1));
        insertProjects.saveProject(8, 5, LocalDate.of(2024, 9,1), LocalDate.of(2024, 12,31));
        insertProjects.saveProject(9, 5, LocalDate.of(2025, 1,3), LocalDate.of(2027, 3,3));
        insertProjects.saveProject(10, 5, LocalDate.of(2024, 10,10), LocalDate.of(2025, 10,20));
    }
}
