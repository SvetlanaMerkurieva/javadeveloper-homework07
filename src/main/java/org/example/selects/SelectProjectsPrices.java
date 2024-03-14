package org.example.selects;

import org.example.config.DatabaseH2;
import org.example.elements.MaxSalaryWorker;
import org.example.elements.ProjectsPrices;
import org.example.elements.SelectQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectProjectsPrices {
    Connection conn = DatabaseH2.getInstance().getConnection();
    private static final String SELECT_PROJECTS_PRICES = SelectQuery.SELECT_PROJECTS_PRICES.get();
    private PreparedStatement projectPricesSt;
    public SelectProjectsPrices () {
        try {
            this.projectPricesSt = conn.prepareStatement(SELECT_PROJECTS_PRICES);
        } catch (SQLException e) {
            System.out.println("Can not create statement. Reason: " + e.getMessage());
        }
    }
    public List<ProjectsPrices> findProjectsPrices() {
        List<ProjectsPrices> projPrice = new ArrayList<>();
            try (ResultSet resultSet = projectPricesSt.executeQuery()) {
                while ((resultSet.next())) {
                    ProjectsPrices projectsPrices = new ProjectsPrices(resultSet.getInt("project_id"), resultSet.getInt("price"));
                    projPrice.add(projectsPrices);
                }
            } catch (SQLException e) {
                System.out.println("Select max salary workers exception. Reason: " + e.getMessage());
            }

        return projPrice;
    }
    public static void main(String[] args) {
        SelectProjectsPrices selectProjectsPrices = new SelectProjectsPrices();
        List<ProjectsPrices> projectsPrices = selectProjectsPrices.findProjectsPrices();
        System.out.println("Projects prices:");
        System.out.println(projectsPrices);
    }
}
