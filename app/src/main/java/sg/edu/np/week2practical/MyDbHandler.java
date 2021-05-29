package sg.edu.np.week2practical;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_USER = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FOLLOWED = "followed";

        public MyDbHandler (Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);

        }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER  + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_DESCRIPTION + " INTEGER, " + COLUMN_FOLLOWED + " INTEGER) ";
            db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            onCreate(db);
    }

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.getFollowed());
        values.put(COLUMN_ID, user.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USER, null , values);
        db.close();
    }

    public User getUsers(String user_name){
            String query = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_NAME + "= \"" + user_name + "\"";

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            User user = new User();

            if (cursor.moveToFirst()){
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName((cursor.getString(1)));
                user.setDescription(cursor.getString(2));
                user.setFollowed(Boolean.parseBoolean(String.valueOf(cursor.getInt(3))));
                cursor.close();
            }

            else{
                user = null;
            }
            db.close();
            return user;

    }

    public boolean deleteUser(String user_name){
            boolean result = false;
        String query = "SELECT * FROM " + TABLE_USER + " WHERE "
                + COLUMN_NAME + " = \""
                + user_name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()){
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setName((cursor.getString(1)));
            user.setDescription(cursor.getString(2));
            user.setFollowed(Boolean.parseBoolean(String.valueOf(cursor.getInt(3))));
            cursor.close();
            result = true;
        }
        db.close();
        return result;

    }
}
