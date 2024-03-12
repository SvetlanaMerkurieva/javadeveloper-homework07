package org.example.inserts;

import org.example.config.DatabaseH2;
import org.example.elements.PopulateQuery;
import org.example.elements.Worker;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InsertWorkers {
    private static final String INSERT_WORKERS = PopulateQuery.POPULATE_TABLE_WORKER.get();
    private PreparedStatement st;
    public InsertWorkers (Connection conn) {
        try {
            this.st = conn.prepareStatement(INSERT_WORKERS);
        } catch (SQLException e) {
            System.out.println("Can not create statement. Reason: " + e.getMessage());
        }
    }

    public int saveWorker(int id, String name, LocalDate birthday, String level, int salary) {

        try {
            this.st.setInt(1, id);
            this.st.setString(2, name);
            this.st.setDate(3, java.sql.Date.valueOf(birthday));
            this.st.setString(4, level);
            this.st.setInt(5, salary);
            return this.st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fail. Reason: " + e.getMessage());
        }
        return -1;
    }
    public void saveAllWorkers (List<Worker> workers) {
        try {
            for (Worker worker: workers) {
                st.setInt(1, worker.getId());
                st.setString(2, worker.getName());
                this.st.setDate(3, java.sql.Date.valueOf(worker.getBirthday()));
                this.st.setString(4, worker.getLevel());
                this.st.setInt(5, worker.getSalary());
                st.addBatch();
            }
            st.executeBatch();
        } catch (SQLException e) {
            System.out.println("Insert all workers exception. Reason: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Connection conn = DatabaseH2.getInstance().getConnection();

        InsertWorkers insertWorkers = new InsertWorkers(conn);

        // використання PreparedStatement
        long startInsert = System.currentTimeMillis();
        insertWorkers.saveWorker(1, "Valentyn Perlovko", LocalDate.of(1985, 2, 12), "Senior", 10000);
        insertWorkers.saveWorker(2, "Ruslan Velychko", LocalDate.of(1993,11,21), "Senior", 9500);
        insertWorkers.saveWorker(3, "Tetyana Mastoluk", LocalDate.of(2000, 3, 15), "Senior", 9500);
        insertWorkers.saveWorker(4, "Iryna Skrypnik", LocalDate.of(1985,5, 30), "Middle", 6500);
        insertWorkers.saveWorker(5, "Yriy Ignor", LocalDate.of(2001, 7, 19), "Middle", 6500);
        insertWorkers.saveWorker(6, "Katerina Sosedko", LocalDate.of(1996, 9, 18), "Middle", 5500);
        insertWorkers.saveWorker(7, "Anton Peremozhets", LocalDate.of(1991,12, 21), "Junior", 3500);
        insertWorkers.saveWorker(8, "Oleksander Potroshko", LocalDate.of(2003, 6, 12), "Junior", 3500);
        insertWorkers.saveWorker(9, "Volodymyr Zverev", LocalDate.of(1998, 8, 12), "Trainee", 1000);
        insertWorkers.saveWorker(10, "Svitlana Merkurieva", LocalDate.of(1981, 11, 14), "Trainee", 900);
        long finishInsert = System.currentTimeMillis();
        long result = finishInsert - startInsert;
        System.out.println("PrepareStatement result: " + result);

        // використання Batch Updates
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker(11, "Valentyn Perlovko", LocalDate.of(1985, 2, 12), "Senior", 10000));
        workers.add(new Worker(12, "Ruslan Velychko", LocalDate.of(1993,11,21), "Senior", 9500));
        workers.add(new Worker(13, "Tetyana Mastoluk", LocalDate.of(2000, 3, 15), "Senior", 9500));
        workers.add(new Worker(14, "Iryna Skrypnik", LocalDate.of(1985,5, 30), "Middle", 6500));
        workers.add(new Worker(15, "Yriy Ignor", LocalDate.of(2001, 7, 19), "Middle", 6500));
        workers.add(new Worker(16, "Katerina Sosedko", LocalDate.of(1996, 9, 18), "Middle", 5500));
        workers.add(new Worker(17, "Anton Peremozhets", LocalDate.of(1991,12, 21), "Junior", 3500));
        workers.add(new Worker(18, "Oleksander Potroshko", LocalDate.of(2003, 6, 12), "Junior", 3500));
        workers.add(new Worker(19, "Volodymyr Zverev", LocalDate.of(1998, 8, 12), "Trainee", 1000));
        workers.add(new Worker(20, "Svitlana Merkurieva", LocalDate.of(1981, 11, 14), "Trainee", 900));
        long startBatchInsert = System.currentTimeMillis();
        insertWorkers.saveAllWorkers(workers);
        long finishBatchInsert = System.currentTimeMillis();
        long resulBatch = finishBatchInsert - startBatchInsert;
        System.out.println(("Batch result: " + resulBatch));
    }



}
