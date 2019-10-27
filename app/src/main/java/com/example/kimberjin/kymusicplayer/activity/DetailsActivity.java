package com.example.kimberjin.kymusicplayer.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.kimberjin.kymusicplayer.R;

/**
 * Created by ky4910 on 2019/10/27 18:38
 */
public class DetailsActivity extends BaseActivity implements View.OnClickListener {

    ImageView img_drop_down;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setupViews();
    }

    private void setupViews() {
        img_drop_down = findViewById(R.id.detail_iv_drop_down);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_iv_drop_down:
                finish();
                break;
            default:
                break;
        }
    }
}
