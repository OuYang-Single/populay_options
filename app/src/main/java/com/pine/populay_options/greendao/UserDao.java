package com.pine.populay_options.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.pine.populay_options.mvp.model.entity.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Username = new Property(1, String.class, "username", false, "USERNAME");
        public final static Property Password = new Property(2, String.class, "password", false, "PASSWORD");
        public final static Property Avatar = new Property(3, String.class, "avatar", false, "AVATAR");
        public final static Property Email = new Property(4, String.class, "email", false, "EMAIL");
        public final static Property Phone = new Property(5, String.class, "phone", false, "PHONE");
        public final static Property Dept = new Property(6, String.class, "dept", false, "DEPT");
        public final static Property Job = new Property(7, String.class, "job", false, "JOB");
        public final static Property Enabled = new Property(8, boolean.class, "enabled", false, "ENABLED");
        public final static Property CreateTime = new Property(9, String.class, "createTime", false, "CREATE_TIME");
        public final static Property LastPasswordResetDate = new Property(10, java.util.Date.class, "lastPasswordResetDate", false, "LAST_PASSWORD_RESET_DATE");
    }


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"USERNAME\" TEXT," + // 1: username
                "\"PASSWORD\" TEXT," + // 2: password
                "\"AVATAR\" TEXT," + // 3: avatar
                "\"EMAIL\" TEXT," + // 4: email
                "\"PHONE\" TEXT," + // 5: phone
                "\"DEPT\" TEXT," + // 6: dept
                "\"JOB\" TEXT," + // 7: job
                "\"ENABLED\" INTEGER NOT NULL ," + // 8: enabled
                "\"CREATE_TIME\" TEXT," + // 9: createTime
                "\"LAST_PASSWORD_RESET_DATE\" INTEGER);"); // 10: lastPasswordResetDate
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(2, username);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(3, password);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(4, avatar);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(5, email);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(6, phone);
        }
 
        String dept = entity.getDept();
        if (dept != null) {
            stmt.bindString(7, dept);
        }
 
        String job = entity.getJob();
        if (job != null) {
            stmt.bindString(8, job);
        }
        stmt.bindLong(9, entity.getEnabled() ? 1L: 0L);
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(10, createTime);
        }
 
        java.util.Date lastPasswordResetDate = entity.getLastPasswordResetDate();
        if (lastPasswordResetDate != null) {
            stmt.bindLong(11, lastPasswordResetDate.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(2, username);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(3, password);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(4, avatar);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(5, email);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(6, phone);
        }
 
        String dept = entity.getDept();
        if (dept != null) {
            stmt.bindString(7, dept);
        }
 
        String job = entity.getJob();
        if (job != null) {
            stmt.bindString(8, job);
        }
        stmt.bindLong(9, entity.getEnabled() ? 1L: 0L);
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(10, createTime);
        }
 
        java.util.Date lastPasswordResetDate = entity.getLastPasswordResetDate();
        if (lastPasswordResetDate != null) {
            stmt.bindLong(11, lastPasswordResetDate.getTime());
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // username
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // password
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // avatar
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // email
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // phone
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // dept
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // job
            cursor.getShort(offset + 8) != 0, // enabled
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // createTime
            cursor.isNull(offset + 10) ? null : new java.util.Date(cursor.getLong(offset + 10)) // lastPasswordResetDate
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUsername(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPassword(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAvatar(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEmail(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPhone(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDept(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setJob(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setEnabled(cursor.getShort(offset + 8) != 0);
        entity.setCreateTime(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setLastPasswordResetDate(cursor.isNull(offset + 10) ? null : new java.util.Date(cursor.getLong(offset + 10)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
