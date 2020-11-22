package com.pine.populay_options.mvp.model.entity;

import com.volley.library.flowtag.OptionCheck;

public class KSelectBean implements OptionCheck {



    private   String tagName;
    private  boolean Checked;
    public KSelectBean(String s, boolean b) {
        tagName=s;
        Checked=b;
    }
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public boolean isChecked() {
        return Checked;
    }

    public void setChecked(boolean checked) {
        Checked = checked;
    }
}
