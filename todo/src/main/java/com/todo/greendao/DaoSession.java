package com.todo.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.todo.bean.Todo;

import com.todo.greendao.TodoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig todoDaoConfig;

    private final TodoDao todoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        todoDaoConfig = daoConfigMap.get(TodoDao.class).clone();
        todoDaoConfig.initIdentityScope(type);

        todoDao = new TodoDao(todoDaoConfig, this);

        registerDao(Todo.class, todoDao);
    }
    
    public void clear() {
        todoDaoConfig.clearIdentityScope();
    }

    public TodoDao getTodoDao() {
        return todoDao;
    }

}
