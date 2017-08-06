package com.example.win10.mvpexample.task1.database;

public class CitiesTable {

    /*@NonNull
    public static Cities fromCursor(@NonNull Cursor cursor) {
        String name = cursor.getString(cursor.getColumnIndex(Columns.NAME));
        String cityName = cursor.getString(cursor.getColumnIndex(Columns.CITY_NAME));
        return new Cities(iata, name, cityName);
    }

    @NonNull
    public static List<Cities> listFromCursor(@NonNull Cursor cursor) {
        List<Cities> cities = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return cities;
        }
        try {
            do {
                cities.add(fromCursor(cursor));
            } while (cursor.moveToNext());
            return cities;
        } finally {
            cursor.close();
        }
    }*/


    /*interface Columns {
        String PAGE = "page";
        String CITY_NAME = "city_name";
    }

    interface Requests {

        String TABLE_NAME = CitiesTable.class.getSimpleName();

        String CREATION_REQUEST = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                Columns.PAGE + " INTEGER, " +
                Columns.CITY_NAME + " TEXT" + ");";

        String DROP_REQUEST = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }*/
}

