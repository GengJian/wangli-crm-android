package commonlyused.db;

import android.content.Context;

import com.database.DBManager;
import com.jiuyi.model.DictBean;


import org.greenrobot.greendao.AbstractDao;

import commonlyused.bean.AppItemBean;
import commonlyused.bean.LinkmanGreenBean;
import commonlyused.bean.OperatorNodeBean;

/**
 * @Description: 数据库操作类，由于greenDao的特殊性，不能在框架中搭建，
 * 所有数据库操作都可以参考该类实现自己的数据库操作管理类，不同的Dao实现
 * 对应的getAbstractDao方法就行。
 */
public class DbHelper {
    private static final String DB_NAME = "huafeng.db";//数据库名称
    private static DbHelper instance;
    private static DBManager<OperatorNodeBean, Long> operatorNode;
    private static DBManager<DictBean, Long> dictBeanLongDBManager;
    private static DBManager<AppItemBean, Long> appItemBeanLongDBManager;
    private static DBManager<LinkmanGreenBean, Long> linkmanGreenBeanLongDBManager;
    private DaoMaster.DevOpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DbHelper() {

    }

    public static DbHelper getInstance() {
        if (instance == null) {
            synchronized (DbHelper.class) {
                if (instance == null) {
                    instance = new DbHelper();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public void init(Context context, String dbName) {
        mHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DBManager<DictBean, Long> dictBeanLongDBManager() {
        if (dictBeanLongDBManager == null) {
            dictBeanLongDBManager = new DBManager<DictBean, Long>() {
                @Override
                public AbstractDao<DictBean, Long> getAbstractDao() {
                    return mDaoSession.getDictBeanDao();
                }
            };
        }
        return dictBeanLongDBManager;
    }
    public DBManager<OperatorNodeBean, Long>  operatorNode() {
        if (operatorNode == null) {
            operatorNode = new DBManager<OperatorNodeBean, Long>() {
                @Override
                public AbstractDao<OperatorNodeBean, Long> getAbstractDao() {
                    return mDaoSession.getOperatorNodeBeanDao();
                }
            };
        }
        return operatorNode;
    }

    public DBManager<AppItemBean, Long> appItemBeanLongDBManager() {
        if (appItemBeanLongDBManager == null) {
            appItemBeanLongDBManager = new DBManager<AppItemBean, Long>() {
                @Override
                public AbstractDao<AppItemBean, Long> getAbstractDao() {
                    return mDaoSession.getAppItemBeanDao();
                }
            };
        }
        return appItemBeanLongDBManager;
    }

    public DBManager<LinkmanGreenBean, Long> linkmanGreenBeanLongDBManager() {
        if (linkmanGreenBeanLongDBManager == null) {
            linkmanGreenBeanLongDBManager = new DBManager<LinkmanGreenBean, Long>() {
                @Override
                public AbstractDao<LinkmanGreenBean, Long> getAbstractDao() {
                    return mDaoSession.getLinkmanGreenBeanDao();
                }
            };
        }
        return linkmanGreenBeanLongDBManager;
    }
    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public void clear() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    public void close() {
        clear();
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }
}
