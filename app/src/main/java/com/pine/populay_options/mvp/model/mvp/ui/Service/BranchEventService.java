package com.pine.populay_options.mvp.model.mvp.ui.Service;

import android.Manifest;
import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.IBinder;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;

import com.jess.arms.base.BaseService;
import com.jess.arms.utils.ArmsUtils;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.BranchEvent;
import com.pine.populay_options.mvp.model.mvp.ui.activity.MainActivity;
import com.wq.photo.widget.PickConfig;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionGen;

import static com.jess.arms.integration.AppManager.getAppManager;


public class BranchEventService extends BaseService {

    @Inject
    Application mApplication;
    @Override
    public void init() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBranchEvent(BranchEvent message) {
        switch (message.getEventName()){
            case ShowTitleBarEVent:
                View view= getAppManager().getCurrentActivity().findViewById(R.id.toolbar);
                if (view!=null){
                    view.setVisibility(((Boolean)message.getData())?View.VISIBLE:View.GONE);
                }
                break;
            case takePortraitPicture:

                //图片剪裁的一些设置
                UCrop.Options options = new UCrop.Options();
                //图片生成格式
                options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
                //图片压缩比
                options.setCompressionQuality(2);
                new  PickConfig.Builder(getAppManager().getCurrentActivity())
                        .maxPickSize(1)//最多选择几张
                        .isneedcamera(true)//是否需要第一项是相机
                        .spanCount(4)//一行显示几张照片
                        .actionBarcolor(Color.parseColor("#E91E63"))//设置toolbar的颜色
                        .statusBarcolor(Color.parseColor("#D81B60")) //设置状态栏的颜色(5.0以上)
                        .isneedcrop(false)//受否需要剪裁
                        .setUropOptions(options) //设置剪裁参数
                        .isSqureCrop(true) //是否是正方形格式剪裁
                        .pickMode(PickConfig.MODE_SINGLE_PICK)//单选还是多选
                        .build();
               // getAppManager().getCurrentActivity().
                break;
        }
    }
    // OnClick
}
