package com.lieni.library.toolbox.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.lieni.library.easyfloat.EasyFloat;
import com.lieni.library.toolbox.R;
import com.lieni.library.toolbox.Toolbox;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbox_main);
        initView();
    }
    private void initView(){
        findViewById(R.id.btDev).setOnClickListener(Toolbox.getInstance().getOnClickListener("dev"));
        findViewById(R.id.btTest).setOnClickListener(Toolbox.getInstance().getOnClickListener("test"));
        findViewById(R.id.btUat).setOnClickListener(Toolbox.getInstance().getOnClickListener("uat"));
        findViewById(R.id.btRelease).setOnClickListener(Toolbox.getInstance().getOnClickListener("release"));
        findViewById(R.id.btExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toolbox.exit();
                finish();
            }
        });
    }
}
