package org.example.inserts;

import org.example.config.DatabaseH2;
import org.example.elements.PopulateQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertClients {
    private static final String INSERT_CLIENTS = PopulateQuery.POPULATE_TABLE_CLIENT.get();
    private PreparedStatement st;
    public InsertClients (Connection conn) {
        try {
            this.st = conn.prepareStatement(INSERT_CLIENTS);
        } catch (SQLException e) {
            System.out.println("Can not create statement. Reason: " + e.getMessage());
        }
    }
    public int saveClient(int id, String name) {

        try {
            this.st.setInt(1, id);
            this.st.setString(2, name);
            return this.st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fail. Reason: " + e.getMessage());
        }
        return -1;
    }
    public static void main(String[] args) {
        Connection conn = DatabaseH2.getInstance().getConnection();
        InsertClients insertClients = new InsertClients(conn);
        insertClients.saveClient(1, "ProjectingGroup");
        insertClients.saveClient(2, "AcloudTree");
        insertClients.saveClient(3, "MegaProject");
        insertClients.saveClient(4, "CrudTask");
        insertClients.saveClient(5, "VeryBigSoft");
    }
}
