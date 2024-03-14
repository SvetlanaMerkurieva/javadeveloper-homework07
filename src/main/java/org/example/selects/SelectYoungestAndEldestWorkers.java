package org.example.selects;

import org.example.config.DatabaseH2;
import org.example.elements.MaxSalaryWorker;
import org.example.elements.SelectQuery;
import org.example.elements.YoungestAndEldestWorkers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SelectYoungestAndEldestWorkers {
    Connection conn = DatabaseH2.getInstance().getConnection();
    private static final String SELECT_MIN_BIRTHDAY = SelectQuery.SELECT_MIN_BIRTHDAY.get();
    private static final String SELECT_MAX_BIRTHDAY = SelectQuery.SELECT_MAX_BIRTHDAY.get();
    private static final String SELECT_YOUNGEST_AND_ELDEST_WORKERS = SelectQuery.SELECT_YOUNGEST_AND_ELDEST_WORKERS.get();
    private PreparedStatement minBirthdaySt;
    private PreparedStatement maxBirthdaySt;
    private PreparedStatement youngestAndEldestWorkersSt;

    public SelectYoungestAndEldestWorkers () {
        try {
            this.minBirthdaySt = conn.prepareStatement(SELECT_MIN_BIRTHDAY);
            this.maxBirthdaySt = conn.prepareStatement(SELECT_MAX_BIRTHDAY);
            this.youngestAndEldestWorkersSt = conn.prepareStatement(SELECT_YOUNGEST_AND_ELDEST_WORKERS);
        } catch (SQLException e) {
            System.out.println("Can not create statement. Reason: " + e.getMessage());
        }
    }
    public LocalDate findMinBirthday() {
        LocalDate minBirthday = LocalDate.of(2024,1,1);
        try (ResultSet resultSet = minBirthdaySt.executeQuery()) {
            while (resultSet.next()) {
                minBirthday  = LocalDate.parse(resultSet.getString("birthday"));
            }
        } catch (SQLException e) {
            System.out.println("Select max salary exception. Reason: " + e.getMessage());
        }
        return minBirthday;
    }
    public LocalDate findMaxBirthday() {
        LocalDate maxBirthday = LocalDate.of(1900,1,1);
        try (ResultSet resultSet = maxBirthdaySt.executeQuery()) {
            while (resultSet.next()) {
                maxBirthday  = LocalDate.parse(resultSet.getString("birthday"));
            }
        } catch (SQLException e) {
            System.out.println("Select max salary exception. Reason: " + e.getMessage());
        }
        return maxBirthday;
    }
    public List<YoungestAndEldestWorkers> findYoungestAndEldestWorkers(LocalDate maxBirthday, LocalDate minBirthday) {
        List<YoungestAndEldestWorkers> youngOldWorkers = new ArrayList<>();
        try {
            youngestAndEldestWorkersSt.setString(1, String.valueOf(minBirthday));
            youngestAndEldestWorkersSt.setString(2, String.valueOf(maxBirthday));
            try (ResultSet resultSet = youngestAndEldestWorkersSt.executeQuery()) {
                while ((resultSet.next())) {
                    YoungestAndEldestWorkers youngOldWorker = new YoungestAndEldestWorkers(resultSet.getString("type"), resultSet.getString("name"), LocalDate.parse(resultSet.getString("birthday")));
                    youngOldWorkers.add(youngOldWorker);
                }
            } catch (SQLException e) {
                System.out.println("Select max salary workers exception. Reason: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Select max salary workers exception. Reason: " + e.getMessage());
        }
        return youngOldWorkers;
    }
    public static void main(String[] args) {
        SelectYoungestAndEldestWorkers selectYoungestAndEldestWorkers = new SelectYoungestAndEldestWorkers();
        LocalDate minBirthday = selectYoungestAndEldestWorkers.findMinBirthday();
        System.out.println("Min birthday :");
        System.out.println(minBirthday);
        LocalDate maxBirthday = selectYoungestAndEldestWorkers.findMaxBirthday();
        System.out.println("Max birthday :");
        System.out.println(maxBirthday);
        List<YoungestAndEldestWorkers> youngestAndEldestWorkers = selectYoungestAndEldestWorkers.findYoungestAndEldestWorkers(maxBirthday, minBirthday);
        System.out.println("Eldest and youngest workers:");
        System.out.println(youngestAndEldestWorkers);
    }
}
