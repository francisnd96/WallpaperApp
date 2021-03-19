package com.francisnd.natureapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME = "FavDB.db";
    private static final int DB_VER = 1;
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<DataHandler> getFavourites(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"title", "thumbnail", "image"};
        String sqlTable = "Favourite";

        queryBuilder.setTables(sqlTable);
        Cursor c = queryBuilder.query(sqLiteDatabase,sqlSelect,null,null,null,null,null);

        final List<DataHandler> result = new ArrayList<>();
        if(c != null && c.getCount() >0 && c.moveToFirst()){
            do{
                result.add(new DataHandler(c.getString(c.getColumnIndex("title")),
                        c.getString(c.getColumnIndex("thumbnail")),
                        c.getString(c.getColumnIndex("thumbnail"))
                ));
            } while (c.moveToNext());
        }
        return result;
    }

    public void addToFavourites(DataHandler dataHandler){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = String.format("Insert into Favourite(title,thumbnail,image) values('%s','%s','%s');",dataHandler.getTitle(),dataHandler.getThumbnail(),dataHandler.getImage());
        sqLiteDatabase.execSQL(query);
    }

    public void cleanFavourites(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = String.format("Delete from Favourite");
        sqLiteDatabase.execSQL(query);
    }

    public void removeFromFavourites(DataHandler dataHandler){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = String.format("Delete from Favourite where title='%s';",dataHandler.getTitle());
        sqLiteDatabase.execSQL(query);
    }
}
