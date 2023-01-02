package com.example.hillel_garage_v2.repository.query;

public enum CarQuery {

    GET_ALL("""
            SELECT *
            FROM car
            WHERE owner_id=?
            """),

    GET_BY_ID("""
            SELECT *
            FROM car
            WHERE id=?
            """),
    SAVE("""
            INSERT INTO car(
            model, color, owner_id)
            VALUES (?, ?, ?)
            """),

    UPDATE("""
            UPDATE car
            SET model=?, color=?
            WHERE id=?
            """),

    DELETE("""
            DELETE FROM car
            WHERE id=?
            """);

    private final String query;

    CarQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
