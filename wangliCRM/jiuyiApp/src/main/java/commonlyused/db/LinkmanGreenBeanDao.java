package commonlyused.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import commonlyused.bean.LinkmanGreenBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LINKMAN_GREEN_BEAN".
*/
public class LinkmanGreenBeanDao extends AbstractDao<LinkmanGreenBean, Long> {

    public static final String TABLENAME = "LINKMAN_GREEN_BEAN";

    /**
     * Properties of entity LinkmanGreenBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Sex = new Property(2, String.class, "sex", false, "SEX");
        public final static Property Birthday = new Property(3, String.class, "birthday", false, "BIRTHDAY");
        public final static Property Email = new Property(4, String.class, "email", false, "EMAIL");
        public final static Property Address = new Property(5, String.class, "address", false, "ADDRESS");
        public final static Property TimIdentifier = new Property(6, String.class, "timIdentifier", false, "TIM_IDENTIFIER");
        public final static Property Letters = new Property(7, String.class, "letters", false, "LETTERS");
        public final static Property TelOne = new Property(8, String.class, "telOne", false, "TEL_ONE");
        public final static Property Org = new Property(9, String.class, "org", false, "ORG");
        public final static Property Dept = new Property(10, String.class, "dept", false, "DEPT");
        public final static Property Duty = new Property(11, String.class, "duty", false, "DUTY");
        public final static Property TelTwo = new Property(12, String.class, "telTwo", false, "TEL_TWO");
        public final static Property TelThree = new Property(13, String.class, "telThree", false, "TEL_THREE");
    }


    public LinkmanGreenBeanDao(DaoConfig config) {
        super(config);
    }
    
    public LinkmanGreenBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LINKMAN_GREEN_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"SEX\" TEXT," + // 2: sex
                "\"BIRTHDAY\" TEXT," + // 3: birthday
                "\"EMAIL\" TEXT," + // 4: email
                "\"ADDRESS\" TEXT," + // 5: address
                "\"TIM_IDENTIFIER\" TEXT," + // 6: timIdentifier
                "\"LETTERS\" TEXT," + // 7: letters
                "\"TEL_ONE\" TEXT," + // 8: telOne
                "\"ORG\" TEXT," + // 9: org
                "\"DEPT\" TEXT," + // 10: dept
                "\"DUTY\" TEXT," + // 11: duty
                "\"TEL_TWO\" TEXT," + // 12: telTwo
                "\"TEL_THREE\" TEXT);"); // 13: telThree
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LINKMAN_GREEN_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LinkmanGreenBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(3, sex);
        }
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(4, birthday);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(5, email);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(6, address);
        }
 
        String timIdentifier = entity.getTimIdentifier();
        if (timIdentifier != null) {
            stmt.bindString(7, timIdentifier);
        }
 
        String letters = entity.getLetters();
        if (letters != null) {
            stmt.bindString(8, letters);
        }
 
        String telOne = entity.getTelOne();
        if (telOne != null) {
            stmt.bindString(9, telOne);
        }
 
        String org = entity.getOrg();
        if (org != null) {
            stmt.bindString(10, org);
        }
 
        String dept = entity.getDept();
        if (dept != null) {
            stmt.bindString(11, dept);
        }
 
        String duty = entity.getDuty();
        if (duty != null) {
            stmt.bindString(12, duty);
        }
 
        String telTwo = entity.getTelTwo();
        if (telTwo != null) {
            stmt.bindString(13, telTwo);
        }
 
        String telThree = entity.getTelThree();
        if (telThree != null) {
            stmt.bindString(14, telThree);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LinkmanGreenBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(3, sex);
        }
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(4, birthday);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(5, email);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(6, address);
        }
 
        String timIdentifier = entity.getTimIdentifier();
        if (timIdentifier != null) {
            stmt.bindString(7, timIdentifier);
        }
 
        String letters = entity.getLetters();
        if (letters != null) {
            stmt.bindString(8, letters);
        }
 
        String telOne = entity.getTelOne();
        if (telOne != null) {
            stmt.bindString(9, telOne);
        }
 
        String org = entity.getOrg();
        if (org != null) {
            stmt.bindString(10, org);
        }
 
        String dept = entity.getDept();
        if (dept != null) {
            stmt.bindString(11, dept);
        }
 
        String duty = entity.getDuty();
        if (duty != null) {
            stmt.bindString(12, duty);
        }
 
        String telTwo = entity.getTelTwo();
        if (telTwo != null) {
            stmt.bindString(13, telTwo);
        }
 
        String telThree = entity.getTelThree();
        if (telThree != null) {
            stmt.bindString(14, telThree);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LinkmanGreenBean readEntity(Cursor cursor, int offset) {
        LinkmanGreenBean entity = new LinkmanGreenBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // sex
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // birthday
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // email
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // address
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // timIdentifier
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // letters
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // telOne
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // org
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // dept
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // duty
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // telTwo
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13) // telThree
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LinkmanGreenBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSex(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setBirthday(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEmail(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAddress(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTimIdentifier(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLetters(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTelOne(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setOrg(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setDept(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setDuty(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setTelTwo(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setTelThree(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LinkmanGreenBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LinkmanGreenBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LinkmanGreenBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
