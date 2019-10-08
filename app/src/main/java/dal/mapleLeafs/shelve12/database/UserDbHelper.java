package dal.mapleLeafs.shelve12.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class UserDbHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "User.db";

    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME="user";
        public static final String COLUMN_COINS_COUNT="coins_count";
        public static final String COLUMN_USER_NAME="user_name";
    }

    private static final String INTEGER_TYPE=" INTEGER";
    private static final String TEXT_TYPE=" TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + UserEntry.TABLE_NAME + " (" +
                    UserEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    UserEntry.COLUMN_COINS_COUNT + INTEGER_TYPE + COMMA_SEP +
                    UserEntry.COLUMN_USER_NAME + TEXT_TYPE +" )";

    private static final String SQL_DELETE_USER_TABLE =
            "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;


    public UserDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_USER_TABLE);
        onCreate(db);
    }

    public void onDownGrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }
}
