package commonlyused.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.jiuyi.model.DictBean;
import commonlyused.bean.AppItemBean;
import commonlyused.bean.LinkmanGreenBean;
import commonlyused.bean.OperatorNodeBean;

import commonlyused.db.DictBeanDao;
import commonlyused.db.AppItemBeanDao;
import commonlyused.db.LinkmanGreenBeanDao;
import commonlyused.db.OperatorNodeBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dictBeanDaoConfig;
    private final DaoConfig appItemBeanDaoConfig;
    private final DaoConfig linkmanGreenBeanDaoConfig;
    private final DaoConfig operatorNodeBeanDaoConfig;

    private final DictBeanDao dictBeanDao;
    private final AppItemBeanDao appItemBeanDao;
    private final LinkmanGreenBeanDao linkmanGreenBeanDao;
    private final OperatorNodeBeanDao operatorNodeBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dictBeanDaoConfig = daoConfigMap.get(DictBeanDao.class).clone();
        dictBeanDaoConfig.initIdentityScope(type);

        appItemBeanDaoConfig = daoConfigMap.get(AppItemBeanDao.class).clone();
        appItemBeanDaoConfig.initIdentityScope(type);

        linkmanGreenBeanDaoConfig = daoConfigMap.get(LinkmanGreenBeanDao.class).clone();
        linkmanGreenBeanDaoConfig.initIdentityScope(type);

        operatorNodeBeanDaoConfig = daoConfigMap.get(OperatorNodeBeanDao.class).clone();
        operatorNodeBeanDaoConfig.initIdentityScope(type);

        dictBeanDao = new DictBeanDao(dictBeanDaoConfig, this);
        appItemBeanDao = new AppItemBeanDao(appItemBeanDaoConfig, this);
        linkmanGreenBeanDao = new LinkmanGreenBeanDao(linkmanGreenBeanDaoConfig, this);
        operatorNodeBeanDao = new OperatorNodeBeanDao(operatorNodeBeanDaoConfig, this);

        registerDao(DictBean.class, dictBeanDao);
        registerDao(AppItemBean.class, appItemBeanDao);
        registerDao(LinkmanGreenBean.class, linkmanGreenBeanDao);
        registerDao(OperatorNodeBean.class, operatorNodeBeanDao);
    }
    
    public void clear() {
        dictBeanDaoConfig.clearIdentityScope();
        appItemBeanDaoConfig.clearIdentityScope();
        linkmanGreenBeanDaoConfig.clearIdentityScope();
        operatorNodeBeanDaoConfig.clearIdentityScope();
    }

    public DictBeanDao getDictBeanDao() {
        return dictBeanDao;
    }

    public AppItemBeanDao getAppItemBeanDao() {
        return appItemBeanDao;
    }

    public LinkmanGreenBeanDao getLinkmanGreenBeanDao() {
        return linkmanGreenBeanDao;
    }

    public OperatorNodeBeanDao getOperatorNodeBeanDao() {
        return operatorNodeBeanDao;
    }

}
