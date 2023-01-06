package com.example.hillel_garage_v2.repository.query;

public enum OwnerQuery {

    GET_ALL("""
            SELECT *
            FROM owner
            """),

    GET_BY_ID("""
            SELECT *
            FROM owner
            WHERE id=?
            """),

    SAVE("""
            INSERT INTO owner(
            name, age)
            VALUES (?, ?)
            """),

    UPDATE("""
            UPDATE owner
            SET name=?, age=?
            WHERE id=?
            """),

    DELETE("""
            DELETE FROM owner
            WHERE id=?
            """);

    private final String query;

    OwnerQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
