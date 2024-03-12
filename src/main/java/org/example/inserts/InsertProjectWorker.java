package org.example.inserts;

import org.example.config.DatabaseH2;
import org.example.elements.PopulateQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertProjectWorker {
    private static final String INSERT_PROJECT_WORKER = PopulateQuery.POPULATE_TABLE_PROJECT_WORKER.get();
    private PreparedStatement st;
    public InsertProjectWorker (Connection conn) {
        try {
            this.st = conn.prepareStatement(INSERT_PROJECT_WORKER);
        } catch (SQLException e) {
            System.out.println("Can not create statement. Reason: " + e.getMessage());
        }
    }
    public int saveProjectWorker(int projectId, int workerId) {

        try {
            this.st.setInt(1, projectId);
            this.st.setInt(2, workerId);
            return this.st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fail. Reason: " + e.getMessage());
        }
        return -1;
    }
    public static void main(String[] args) {
        Connection conn = DatabaseH2.getInstance().getConnection();
        InsertProjectWorker insertProjectWorker = new InsertProjectWorker(conn);
        insertProjectWorker.saveProjectWorker(1, 3 );
        insertProjectWorker.saveProjectWorker(1, 4 );
        insertProjectWorker.saveProjectWorker(1, 7 );
        insertProjectWorker.saveProjectWorker(2, 1);
        insertProjectWorker.saveProjectWorker(2, 5);
        insertProjectWorker.saveProjectWorker(3, 2);
        insertProjectWorker.saveProjectWorker(3, 6);
        insertProjectWorker.saveProjectWorker(3, 8);
        insertProjectWorker.saveProjectWorker(3, 10);
        insertProjectWorker.saveProjectWorker(4, 1);
        insertProjectWorker.saveProjectWorker(4, 2);
        insertProjectWorker.saveProjectWorker(4, 6);
        insertProjectWorker.saveProjectWorker(4, 7);
        insertProjectWorker.saveProjectWorker(4, 9);
        insertProjectWorker.saveProjectWorker(4, 10);
        insertProjectWorker.saveProjectWorker(5, 1);
        insertProjectWorker.saveProjectWorker(5, 4);
        insertProjectWorker.saveProjectWorker(6, 3);
        insertProjectWorker.saveProjectWorker(6, 5);
        insertProjectWorker.saveProjectWorker(6, 6);
        insertProjectWorker.saveProjectWorker(6, 9);
        insertProjectWorker.saveProjectWorker(6, 10);
        insertProjectWorker.saveProjectWorker(7, 2);
        insertProjectWorker.saveProjectWorker(7, 4);
        insertProjectWorker.saveProjectWorker(7, 8);
        insertProjectWorker.saveProjectWorker(7, 9);
        insertProjectWorker.saveProjectWorker(8, 1);
        insertProjectWorker.saveProjectWorker(8, 6);
        insertProjectWorker.saveProjectWorker(8, 7);
        insertProjectWorker.saveProjectWorker(8, 10);
        insertProjectWorker.saveProjectWorker(9, 2);
        insertProjectWorker.saveProjectWorker(9, 5);
        insertProjectWorker.saveProjectWorker(9, 8);
        insertProjectWorker.saveProjectWorker(10, 1);
        insertProjectWorker.saveProjectWorker(10, 2);
        insertProjectWorker.saveProjectWorker(10, 4);
    }
}
