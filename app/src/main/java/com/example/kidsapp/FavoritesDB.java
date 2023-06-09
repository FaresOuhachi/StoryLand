package com.example.kidsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FavoritesDB extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "FavoriteStoriesDB";
    private static String TABLE_NAME = "favoriteTable";

    public static String ID = "id";
    public static String ITEM_TITLE = "itemTitle";

    public static String ITEM_AUTHOR = "itemAuthor";
    public static String ITEM_IMAGE = "itemImage";
    public static String FAVORITE_STATUS = "fStatus";
    // dont forget write this spaces
    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + ID + " INTEGER," + ITEM_TITLE + " TEXT," + ITEM_AUTHOR + " TEXT,"
            + ITEM_IMAGE + " INTEGER," + FAVORITE_STATUS + " TEXT)";

    public FavoritesDB(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("DB VERSION " + db.getVersion());
        ContentValues cv = new ContentValues();
        // enter your value
        for (int x = 1; x < 11; x++) {
            cv.put(ID, String.valueOf(x));
            cv.put(FAVORITE_STATUS, "0");

            db.insert(TABLE_NAME, null, cv);
        }
    }

    // insert data into database
    public void insertIntoTheDatabase(String item_title,String item_author, int item_image, String id, String fav_status) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ITEM_TITLE, item_title);
        cv.put(ITEM_AUTHOR, item_author);
        cv.put(ITEM_IMAGE, item_image);
        cv.put(ID, id);
        cv.put(FAVORITE_STATUS, fav_status);
        db.insert(TABLE_NAME, null, cv);
        Log.d("StoriesDB Status", item_title + ", favstatus - " + fav_status + " - . " + cv);
    }

    // read all data
    public Cursor read_all_data(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + ID + "=" + id + "";
        return db.rawQuery(sql, null, null);
    }

    // remove line from database
    public void remove_fav(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + FAVORITE_STATUS + " ='0' WHERE " + ID + " ='" + id + "'";
        db.execSQL(query);
        db.close();
    }


    // select all favorite list

    public Cursor select_all_favorite_list() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + FAVORITE_STATUS + " ='1'";
        return db.rawQuery(sql, null, null);
    }
}

