package org.example.selects;

import org.example.config.DatabaseH2;
import org.example.elements.MaxSalaryWorker;
import org.example.elements.SelectQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectMaxSalaryWorker {
    Connection conn = DatabaseH2.getInstance().getConnection();
    private static final String SELECT_MAX_SALARY = SelectQuery.SELECT_MAX_SALARY.get();
    private static final String SELECT_MAX_SALARY_WORKER = SelectQuery.SELECT_MAX_SALARY_WORKER.get();

    private PreparedStatement maxSalarySt;
    private PreparedStatement maxSalaryWorkerSt;
    public SelectMaxSalaryWorker () {
        try {
            this.maxSalarySt = conn.prepareStatement(SELECT_MAX_SALARY);
            this.maxSalaryWorkerSt = conn.prepareStatement(SELECT_MAX_SALARY_WORKER);
        } catch (SQLException e) {
            System.out.println("Can not create statement. Reason: " + e.getMessage());
        }
    }
    public int findMaxSalary() {
        int maxSalary = 0;
        try (ResultSet resultSet = maxSalarySt.executeQuery()) {
            while (resultSet.next()) {
                maxSalary = resultSet.getInt("maxSalary");
            }
        } catch (SQLException e) {
            System.out.println("Select max salary exception. Reason: " + e.getMessage());
        }
        return maxSalary;
    }
    public List<MaxSalaryWorker> findMaxSalaryWorker(int maxSalary) {
        List<MaxSalaryWorker> maxSalWork = new ArrayList<>();
        try {
            maxSalaryWorkerSt.setInt(1, maxSalary);
            try (ResultSet resultSet = maxSalaryWorkerSt.executeQuery()) {
                while ((resultSet.next())) {
                    MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker(resultSet.getString("name"), resultSet.getInt("salary"));
                    maxSalWork.add(maxSalaryWorker);
                }
            } catch (SQLException e) {
                System.out.println("Select max salary workers exception. Reason: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Select max salary workers exception. Reason: " + e.getMessage());
        }
        return maxSalWork;
    }

    public static void main(String[] args) {
        SelectMaxSalaryWorker selectMaxSalaryWorker = new SelectMaxSalaryWorker();
        int maxSalary = selectMaxSalaryWorker.findMaxSalary();
        List<MaxSalaryWorker> maxSalaryWorkers = selectMaxSalaryWorker.findMaxSalaryWorker(maxSalary);
        System.out.println("Max salary:");
        System.out.println(maxSalary);
        System.out.println("Max salary workers:");
        System.out.println(maxSalaryWorkers.toString());

    }
}
