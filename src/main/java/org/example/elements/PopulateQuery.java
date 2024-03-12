package org.example.elements;

public enum PopulateQuery {
    POPULATE_TABLE_WORKER("INSERT INTO worker " +
            "(id, name, birthday, level, salary) " +
            "VALUES(?, ?, ?, ?, ?)"),
    POPULATE_TABLE_CLIENT("INSERT INTO client " +
            "(id, name) " +
            "VALUES(?, ?)"),
    POPULATE_TABLE_PROJECT("INSERT INTO project " +
            "(id, client_id, start_date, finish_date) " +
            "VALUES(?, ?, ?, ?)"),
    POPULATE_TABLE_PROJECT_WORKER("INSERT INTO project_worker " +
            "(project_id, worker_id) " +
            "VALUES(?, ?)");

    private String value;
    public String get() {
        return value;
    }
    PopulateQuery (String value) {
        this.value = value;
    }
}
