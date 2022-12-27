package com.example.hillel_garage_v2.repository.query;

public enum OwnerQuery {
    GET_BY_ID("""
            SELECT *
            FROM hw_20.owner
            WHERE id=?
            """),
    SAVE("""
            INSERT INTO hw_20.owner(
            name, age)
            VALUES (?, ?)
            """),

    UPDATE("""
            UPDATE hw_20.owner
            SET name=?, age=?
            WHERE id=?
            """),

    DELETE("""
            DELETE FROM hw_20.owner
            WHERE id=?
            """),

    GET_ALL("""
            SELECT
                owner.id AS owner_id,
                owner.name AS owner_name,
                owner.age AS owner_age,
                car.id AS car_id,
                car.model AS car_model,
                car.color AS car_color,
                car.owner_id AS car_owner_id
            FROM
                hw_20.owner
            LEFT JOIN hw_20.car
                ON owner.id = car.owner_id
            """);

    private final String query;

    OwnerQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
