package org.example.selects;

import org.example.config.DatabaseH2;
import org.example.elements.MaxProjectsClient;
import org.example.elements.SelectQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SelectMaxProjectsClient {
    Connection conn = DatabaseH2.getInstance().getConnection();
    private static final String SELECT_COUNT_PROJECTS = SelectQuery.SELECT_COUNT_PROJECT.get();
    private static final String SELECT_CLIENT_ID = SelectQuery.SELECT_CLIENT_ID.get();
    private static final String SELECT_MAX_PROJECTS_CLIENT = SelectQuery.SELECT_MAX_PROJECTS_CLIENT.get();
    private PreparedStatement maxCountSt;
    private PreparedStatement clientIdSt;
    private PreparedStatement maxProjectClientSt;
    public SelectMaxProjectsClient () {
        try {
            this.maxCountSt = conn.prepareStatement(SELECT_COUNT_PROJECTS);
            this.clientIdSt = conn.prepareStatement(SELECT_CLIENT_ID);
            this.maxProjectClientSt = conn.prepareStatement(SELECT_MAX_PROJECTS_CLIENT);
        } catch (SQLException e) {
            System.out.println("Can not create statement. Reason: " + e.getMessage());
        }
    }
    public int findMaxCount() {
        int maxCount = 0;
        try (ResultSet resultSet = maxCountSt.executeQuery()) {
            while (resultSet.next()) {
                maxCount = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Select max count projects exception. Reason: " + e.getMessage());
        }
        return maxCount;
    }
    public List<Integer> findClientId (int maxCount) {
        List<Integer> clientsId = new ArrayList<>();
        try {
            clientIdSt.setInt(1, maxCount);
            try (ResultSet resultSet = clientIdSt.executeQuery()) {
                while ((resultSet.next())) {
                    int clientId = resultSet.getInt("client_id");
                    clientsId.add(clientId);
                }
            } catch (SQLException e) {
                System.out.println("Select id client exception. Reason: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Select id client exception. Reason: " + e.getMessage());
        }
        return clientsId;
    }
    public List<MaxProjectsClient> findMaxProjectsClient(List<Integer> clientsId) {
        List<MaxProjectsClient> maxProjClient = new ArrayList<>();
        for (int clientId: clientsId) {
            try {
                maxProjectClientSt.setInt(1, clientId);
                try (ResultSet resultSet = maxProjectClientSt.executeQuery()) {
                    while ((resultSet.next())) {
                        MaxProjectsClient maxProjectsClient = new MaxProjectsClient(resultSet.getString("name"), resultSet.getInt("project_count"));
                        maxProjClient.add(maxProjectsClient);
                    }
                } catch (SQLException e) {
                    System.out.println("Select max project client exception. Reason: " + e.getMessage());
                }
            } catch (SQLException e) {
                System.out.println("Select max project client exception. Reason: " + e.getMessage());
            }
        }
        return maxProjClient;
    }
    public static void main(String[] args) {
        SelectMaxProjectsClient selectMaxProjectsClient = new SelectMaxProjectsClient();
        int maxCount = selectMaxProjectsClient.findMaxCount();
        System.out.println("Max count:");
        System.out.println(maxCount);

        List<Integer> clientsId = selectMaxProjectsClient.findClientId(maxCount);
        System.out.println("Clients id:");
        System.out.println(clientsId);

        List<MaxProjectsClient> maxProjectsClients = selectMaxProjectsClient.findMaxProjectsClient(clientsId);
        System.out.println("Max projects clients:");
        System.out.println(maxProjectsClients.toString());
    }

}
