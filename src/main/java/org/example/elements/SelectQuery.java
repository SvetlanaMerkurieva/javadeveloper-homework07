package org.example.elements;

public enum SelectQuery {
    SELECT_MAX_SALARY("SELECT MAX(salary) AS maxSalary FROM worker"),
    SELECT_MAX_SALARY_WORKER("SELECT name, salary FROM worker WHERE salary = ?"),
    SELECT_COUNT_PROJECT("SELECT count(id) AS count FROM project GROUP BY client_id ORDER BY count(id) DESC LIMIT 1"),
    SELECT_CLIENT_ID("SELECT client_id FROM project GROUP BY client_id HAVING count(id) = ?"),
    SELECT_MAX_PROJECTS_CLIENT("SELECT name, count(project.id) AS PROJECT_COUNT FROM client JOIN project ON client.id = project.client_id GROUP BY name HAVING client.id = ?"),
    SELECT_MAX_COUNT_MONTH("SELECT MAX(DATEDIFF (month, start_date, finish_date)) AS count_month FROM project " +
            "GROUP BY id " +
            "ORDER BY MAX(DATEDIFF (month, start_date, finish_date)) DESC " +
            "LIMIT 1"),
    SELECT_LONGEST_PROJECT("SELECT id, DATEDIFF (month, start_date, finish_date) AS MONTH_COUNT " +
            "FROM project " +
            "GROUP BY id " +
            "HAVING DATEDIFF (month, start_date, finish_date) = ?"),
    SELECT_MIN_BIRTHDAY("SELECT MIN(birthday) AS birthday FROM worker"),
    SELECT_MAX_BIRTHDAY("SELECT MAX(birthday) AS birthday FROM worker"),
    SELECT_YOUNGEST_AND_ELDEST_WORKERS("SELECT 'ELDEST' AS TYPE, name, birthday FROM worker WHERE birthday = ? " +
    "UNION " +
    "SELECT 'YOUNGEST', name, birthday FROM worker WHERE birthday = ?"),
    SELECT_PROJECTS_PRICES("SELECT project_id, SUM(worker.salary) * DATEDIFF ('MONTH', project.start_date, project.finish_date) AS PRICE " +
            "FROM project_worker " +
            "JOIN worker ON worker.id = project_worker.worker_id " +
            "JOIN project ON project.id = project_worker.project_id " +
            "GROUP BY project_id");



    private String value;
    public String get() {
        return value;
    }
    SelectQuery (String value) {
        this.value = value;
    }
}
