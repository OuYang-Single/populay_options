package com.pine.populay_options.app;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.mvp.IPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.PositionPresenter;

import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;

public abstract class  BaseAseActivitys<P extends IPresenter> extends BaseActivity<P> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
