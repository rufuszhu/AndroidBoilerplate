package ca.co.rufus.androidboilerplate.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ca.co.rufus.androidboilerplate.data.model.Repository;
import ca.co.rufus.androidboilerplate.data.model.User;

public class DbOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "boilerplate.db";
    public static final int DATABASE_VERSION = 1;

    private static final String CREATE_REPO = ""
            + "CREATE TABLE " + Repository.TABLE + "("
            + Repository.ID + " INTEGER NOT NULL PRIMARY KEY,"
            + Repository.OWNER_ID + " INTEGER NOT NULL REFERENCES " + User.TABLE + "(" + User.ID + "),"
            + Repository.NAME + " TEXT NOT NULL,"
            + Repository.DESCRIPTION + " TEXT NOT NULL,"
            + Repository.WATCHERS + " INTEGER NOT NULL DEFAULT 0,"
            + Repository.STARS + " INTEGER NOT NULL DEFAULT 0,"
            + Repository.FORKS + " INTEGER NOT NULL DEFAULT 0,"
            + Repository.HTML_URL + " INTEGER NOT NULL DEFAULT 0,"
            + Repository.UPDATED_AT + " TEXT"
            + ")";

    private static final String CREATE_USER = ""
            + "CREATE TABLE " + User.TABLE + "("
            + User.ID + " INTEGER NOT NULL PRIMARY KEY,"
            + User.LOGIN + " TEXT NOT NULL,"
            + User.AVATAR_URL + " TEXT NOT NULL"
            + ")";


    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            //Uncomment line below if you want to enable foreign keys
            //db.execSQL("PRAGMA foreign_keys=ON;");
            db.execSQL(CREATE_USER);
            db.execSQL(CREATE_REPO);
            //Add other tables here
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}