package com.pine.populay_options.mvp.model.wigth;

import androidx.viewpager.widget.ViewPager;
import com.pine.populay_options.mvp.model.entity.TransType;

public class MyTransformer {

    public static ViewPager.PageTransformer getMyTransformer(TransType type) {
       return getMyTransformer(type, -1000f);
    }

    public static ViewPager.PageTransformer getMyTransformer(TransType type, float maxValue) {
        ViewPager.PageTransformer transformer =null;
        switch (type) {
            case H3D:
                transformer = new PageTransformer3D(maxValue);
            break;
             case H3DINTO:
                transformer = new PageTransformer3DInTo(maxValue);
            break;
            case FADEIN:
                transformer =new PageTransformerFadeIn(maxValue);
                break;

            case TANDEM:
                transformer = new PageTransformerTandem(maxValue);
                break;
                case OVERLAP:
                transformer = new PageTransformerOverlap(maxValue);
                break;

        }
        return transformer;
    }
}
