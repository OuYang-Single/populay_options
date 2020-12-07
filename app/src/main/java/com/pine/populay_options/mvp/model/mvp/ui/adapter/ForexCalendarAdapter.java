package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Book;
import com.pine.populay_options.mvp.model.entity.ForexCalendar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ForexCalendarAdapter extends DefaultAdapter<ForexCalendar> {
    @Inject
    public ForexCalendarAdapter(List<ForexCalendar> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<ForexCalendar> getHolder(@NonNull View v, int viewType) {
        return new ContextItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.forex_calendar_item;
    }


    public class ContextItemBaseHolder extends BaseHolder<ForexCalendar> {

        @BindView(R.id.tv_forex_calendar_item_time)
        TextView time;
        @BindView(R.id.tv_forex_calendar_item_currency)
        TextView tv_forex_calendar_item_currency;
        @BindView(R.id.tv_forex_calendar_item_event)
        TextView tv_forex_calendar_item_event;
        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull ForexCalendar data, int position) {
            time.setText(data.getTime());
            tv_forex_calendar_item_currency.setText(data.getCurrency());
            tv_forex_calendar_item_event.setText(data.getEvent());
        }
    }
}