package com.pine.populay_options.greendao;

import com.pine.populay_options.mvp.model.entity.User;
import org.greenrobot.greendao.AbstractDao;

public class UserManager extends BaseBeanManager<User, Long> {

    public UserManager(AbstractDao dao) {
        super(dao);
    }
}
