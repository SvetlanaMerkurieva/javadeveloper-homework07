package org.example.selects;

import org.example.config.DatabaseH2;
import org.example.elements.LongestProject;
import org.example.elements.SelectQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectLongestProject {
    Connection conn = DatabaseH2.getInstance().getConnection();
    private static final String SELECT_MAX_COUNT_MONTH = SelectQuery.SELECT_MAX_COUNT_MONTH.get();
    private static final String SELECT_LONGEST_PROJECT = SelectQuery.SELECT_LONGEST_PROJECT.get();
    private PreparedStatement maxCountMonthSt;
    private PreparedStatement longestProjectSt;
    public SelectLongestProject () {
        try {
            this.maxCountMonthSt = conn.prepareStatement(SELECT_MAX_COUNT_MONTH);
            this.longestProjectSt = conn.prepareStatement(SELECT_LONGEST_PROJECT);
        } catch (SQLException e) {
            System.out.println("Can not create statement. Reason: " + e.getMessage());
        }
    }
    public int findMaxCountMonth() {
        int maxCountMonth = 0;
        try (ResultSet resultSet = maxCountMonthSt.executeQuery()) {
            while (resultSet.next()) {
                maxCountMonth = resultSet.getInt("count_month");
            }
        } catch (SQLException e) {
            System.out.println("Select max count month exception. Reason: " + e.getMessage());
        }
        return maxCountMonth;
    }
    public List<LongestProject> findLongestProject(int maxCountMonth) {
        List<LongestProject> longProj = new ArrayList<>();
        try {
            longestProjectSt.setInt(1, maxCountMonth);
            try (ResultSet resultSet = longestProjectSt.executeQuery()) {
                while ((resultSet.next())) {
                    LongestProject longestProject = new LongestProject(resultSet.getInt("id"), resultSet.getInt("month_count"));
                    longProj.add(longestProject);
                }
            } catch (SQLException e) {
                System.out.println("Select longest project exception. Reason: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Select longest project exception. Reason: " + e.getMessage());
        }
        return longProj;
    }
    public static void main(String[] args) {
        SelectLongestProject selectLongestProject = new SelectLongestProject();
        int maxCountMonth = selectLongestProject.findMaxCountMonth();
        System.out.println("Max count month:");
        System.out.println(maxCountMonth);
        List<LongestProject> longestProjects = selectLongestProject.findLongestProject(maxCountMonth);
        System.out.println("Longest projects:");
        System.out.println(longestProjects.toString());

    }
}
