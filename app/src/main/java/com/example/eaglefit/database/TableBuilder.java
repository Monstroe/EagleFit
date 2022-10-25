package com.example.eaglefit.database;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class TableBuilder {

    private String tableName;
                        //Name   Type
    private LinkedHashMap<String, String> columns;

    public TableBuilder(String tableName) {
        this.tableName = tableName;
        this.columns = new LinkedHashMap<String, String>();
    }

    public TableBuilder(String tableName, String[] columnNames, String[] columnTypes) {
        this.tableName = tableName;
        this.columns = new LinkedHashMap<String, String>();

        addColumns(columnNames, columnTypes);
    }

    public TableBuilder(String tableName, String[][] columnNamesAndTypes) {
        this.tableName = tableName;
        this.columns = new LinkedHashMap<String, String>();

        addColumns(columnNamesAndTypes);
    }

    public void addColumn(String columnName, String columnType) {
        columns.put(columnName, columnType);
    }

    public void addColumns(String[] columnNames, String[] columnTypes)  {
        if(columnNames.length == columnTypes.length) {
            for(int i = 0; i < columnNames.length; i++) {
                columns.put(columnNames[i], columnTypes[i]);
            }
        }
        else {
            Log.e("TableBuilder", "ERROR: Arrays 'columnNames' and 'columnTypes' must be the same length");
        }
    }

    public void addColumns(String[][] columnNamesAndTypes) {
        for(int i = 0; i < columnNamesAndTypes.length; i++) {
            columns.put(columnNamesAndTypes[i][0], columnNamesAndTypes[i][1]);
        }
    }

    public String getDeclarationString() { //TODO
        String declaration = "CREATE TABLE " + tableName + " (";
        String[][] columnsArr = getColumns();

        for(int i = 0; i < columnsArr.length; i++) {
            declaration += columnsArr[i][0] + " " + columnsArr[i][1] + ", ";
        }

        declaration = declaration.substring(0, declaration.length() - 3);
        declaration += ")";
        return declaration;
    }

    public String[][] getColumns() {
        String[][] columnsArr = new String[columns.size()][2];
        int i = 0;

        for (Map.Entry mapElement : columns.entrySet()) {
            String key = (String)mapElement.getKey();
            String value = (String)mapElement.getValue();
            columnsArr[i][0] = key;
            columnsArr[i][1] = value;
            i++;
        }

        return columnsArr;
    }

}
