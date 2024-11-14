package com.example.application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DBNAME ="database";
    public static final String TABLE_NAME ="user";
    public static final String USER_ID ="_id";//cursor adapter//primary key
    public static final String USER_NAME ="name";
    public static final String USER_EMAIL ="email";
    public static final String USER_REMARK ="remark";
    private static final String QUALIFICATION_TABLE_NAME ="qualification";
    public static final String Q_ID ="_id";
    public static final String Q_TITLE ="title";
    public static final String Q_YEAR ="year";
    public static final String Q_USER_ID="user_id";//foreign key
    private SQLiteDatabase database;

    public DataBaseHelper(@Nullable Context context) {
        super(context,DBNAME,null,1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableCreate = "create table "+TABLE_NAME+"("+USER_ID+" integer primary key autoincrement,"
                             +USER_NAME+" text,"+USER_EMAIL+" text,"+USER_REMARK+" text)";
        db.execSQL(tableCreate);

        String tableCreate2 = "create table "+QUALIFICATION_TABLE_NAME+"("+
                Q_ID+" integer primary key autoincrement,"+
                Q_TITLE+" text,"+
                Q_YEAR+" text,"+
                Q_USER_ID+" integer,"+
                " FOREIGN KEY ("+Q_USER_ID+")"+
                " REFERENCES "+TABLE_NAME+"("+USER_ID+")"+
                " ON DELETE CASCADE"+
                ")";
        db.execSQL(tableCreate2);
        /* create table qualification id integer primary key auto increment,title,text,user_id integer,
           foreign key (user_id) references users(_id) on delete cascade */
    }//on Create

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable1 = "drop table if exists "+TABLE_NAME;
        db.execSQL(dropTable1);
        String dropTable2 = "drop table if exists "+QUALIFICATION_TABLE_NAME;
        db.execSQL(dropTable2);
        onCreate(db);
    }//end of onUpgrade

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;");//foreign key
    }//onOpen (if use foreign key, declare PRAGMA

    //public long saveUser(String name, String email, String remark){
        public long saveUser(User user){
        ContentValues values = new ContentValues();
        values.put(USER_NAME,user.getName());
        values.put(USER_EMAIL,user.getEmail());
        values.put(USER_REMARK,user.getRemark());

        long user_id = database.insertOrThrow(TABLE_NAME,null,values);
        return user_id;
    }//end of saveUser

    //add qualification
    public long addQualification(Qualification qualification){
        ContentValues values = new ContentValues();
        values.put(Q_TITLE,qualification.getTitle());//title
        values.put(Q_YEAR,qualification.getYear());//year
        values.put(Q_USER_ID,qualification.getUser_id());//user_id
        long q_id = database.insertOrThrow(QUALIFICATION_TABLE_NAME,null,values);
        return q_id;
    }//end of qualification

    public ArrayList<String> getAllUsers(){
        database =getReadableDatabase();
        Cursor results = database.query(TABLE_NAME,new String[]{USER_ID,USER_NAME,USER_EMAIL,USER_REMARK},null,null,null,null,USER_NAME);
        ArrayList<String> user_array = new ArrayList<>();
        results.moveToFirst();//first row (id, name, email, remark)

        while (!results.isAfterLast())
        {
            int id = results.getInt(0);
            String name = results.getString(1);
            String email = results.getString(2);
            String remark = results.getString(3);

            String record = id+": "+name+", "+email+", "+remark+"\n";
            user_array.add(record);
            results.moveToNext();//next row
        }//end of loop

        return user_array;
    }//end of getAllUsers

    public ArrayList<Qualification> getAllQualifications(int user_id){

        database =getReadableDatabase();

        Cursor results = database.query(QUALIFICATION_TABLE_NAME,
                         new String[]{Q_ID,Q_TITLE,Q_YEAR,Q_USER_ID},
                         Q_USER_ID+"=?",new String[]{String.valueOf(user_id)},
                         null,null,null);

        ArrayList<Qualification> qualification_array = new ArrayList<>();

        results.moveToFirst();

        while (!results.isAfterLast())
        {
            int id = results.getInt(0);
            String title = results.getString(1);
            String year  = results.getString(2);
            int uid =results.getInt(3);
            //String record = id+": "+title+", "+uid;
            Qualification q = new Qualification(id,title,year);//q record
            qualification_array.add(q);//qualification array
            results.moveToNext();
        }
        return qualification_array;
    }

    public void deleteAllUsers(){
        database.delete(TABLE_NAME,null,null);
    }
    public void deleteUser(int user_id){
        database.delete(TABLE_NAME,USER_ID+"=?",new String[]{String.valueOf(user_id)});
    }//end of deleteUser (one row delete)

    public void deleteQualification(int q_id){
        database.delete(QUALIFICATION_TABLE_NAME,Q_ID+"=?",new String[]{String.valueOf(q_id)});
    }
    public void updateUser(User user){
          ContentValues values = new ContentValues();
          values.put(USER_NAME,user.getName());
          values.put(USER_EMAIL,user.getEmail());
          values.put(USER_REMARK,user.getRemark());
          database.update(TABLE_NAME,values,USER_ID+"=?",new String[]{String.valueOf(user.getId())});
    }//end of updateUser (one row update)

    public void updateQualification(Qualification qualification){
        ContentValues values = new ContentValues();
        values.put(Q_TITLE,qualification.getTitle());
        values.put(Q_YEAR,qualification.getYear());
        //values.put(Q_USER_ID,qualification.getUser_id());
        database.update(QUALIFICATION_TABLE_NAME,values,Q_ID+"=?",
                new String[]{String.valueOf(qualification.get_id())});
    }

    //search user name
    public ArrayList<String> getUser(String key){
        database =getReadableDatabase();

        Cursor results = database.query(TABLE_NAME,new String[]{USER_ID,USER_NAME,USER_EMAIL,USER_REMARK},
                USER_NAME+" LIKE ? OR "+USER_EMAIL+" LIKE ?",
                new String[]{"%"+key+"%","%"+key+"%"},null,null,null);
        ArrayList<String> user_array = new ArrayList<>();
        results.moveToFirst();
        while (!results.isAfterLast())
        {
            int id = results.getInt(0);
            String name = results.getString(1);
            String email = results.getString(2);
            String remark = results.getString(3);

            String record = id+": "+name+", "+email+", "+remark+"\n";
            user_array.add(record);
            results.moveToNext();//next row
        }//end of loop

        return user_array;
    }//end of getUser
        public Cursor fetchAllUsers(){
            database = getReadableDatabase();
            String[] columns = new String[]{USER_ID,USER_NAME,USER_EMAIL,USER_REMARK};
            Cursor cursor = database.query(TABLE_NAME,columns,null,null,null,null,null);
            if(cursor!=null)cursor.moveToFirst();
            return cursor;
        }//end of fetchAllUsers

}//end of class
