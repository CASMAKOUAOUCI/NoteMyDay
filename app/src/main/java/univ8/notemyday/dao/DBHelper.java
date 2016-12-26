package univ8.notemyday.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by abdelmadjidchaibi on 20/12/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String TAG = "DBHelper";

    // columns of the Days table
    public static final String TABLE_DAYS = "days";
    public static final String COLUMN_DAY_ID = "DATEDAYKEY";

    // columns of the CriteriaDay table
    public static final String TABLE_CRITERIADAY = "CriteriaDay";
    public static final String COLUMN_CRITERIADAY_ID = "CriteriaDay_ID";
    public static final String COLUMN_CRITERIADAY_NAME = "CriteriaDay_name";
    public static final String COLUMN_CRITERIADAY_DESCRIPTION = "CriteriaDay_description";
    public static final String COLUMN_CRITERIADAY_RATING = "rating";
    public static final String COLUMN_CRITERIADAY_DAY_ID = "dateDay";

    private static final String DATABASE_NAME = "days.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statement of the creteriaDay table creation
    private static final String SQL_CREATE_TABLE_CRITERIADAY = "CREATE TABLE " + TABLE_CRITERIADAY + "("+ COLUMN_CRITERIADAY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_CRITERIADAY_NAME + " TEXT NOT NULL, "+ COLUMN_CRITERIADAY_DESCRIPTION + " TEXT NOT NULL, "+ COLUMN_CRITERIADAY_RATING + " REAL NOT NULL, "+ COLUMN_CRITERIADAY_DAY_ID + " TEXT NOT NULL "+");";

    // SQL statement of the days table creation
    private static final String SQL_CREATE_TABLE_DAYS = "CREATE TABLE "+ TABLE_DAYS + "("+ COLUMN_DAY_ID + " TEXT PRIMARY KEY "+");";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_TABLE_DAYS);
        database.execSQL(SQL_CREATE_TABLE_CRITERIADAY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version " + oldVersion + " to "+ newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CRITERIADAY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAYS);

        // recreate the tables
        onCreate(db);
    }

    public DBHelper(Context context, String name, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
