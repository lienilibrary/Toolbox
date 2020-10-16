package com.lieni.library.toolbox.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

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
        findViewById(R.id.btDev).setOnClickListener(getOnClickListener(Toolbox.TAG_DEV));
        findViewById(R.id.btTest).setOnClickListener(getOnClickListener(Toolbox.TAG_DEBUG));
        findViewById(R.id.btUat).setOnClickListener(getOnClickListener(Toolbox.TAG_UAT));
        findViewById(R.id.btRelease).setOnClickListener(getOnClickListener(Toolbox.TAG_RELEASE));
        findViewById(R.id.btExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toolbox.exit();
                finish();
            }
        });

        TextView tvHint=findViewById(R.id.tvHint);
        tvHint.setText(getString(R.string.env_hint,getEnv()));
    }
    private View.OnClickListener getOnClickListener(final String env){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View.OnClickListener listener=Toolbox.getInstance().getOnClickListener(env);
                if(listener!=null) listener.onClick(v);
                save(env);
                Toast.makeText(getApplicationContext(),"切换成功，请杀死APP后重新进入！",Toast.LENGTH_SHORT).show();
            }
        };
    }
    private void save(String env){
        SharedPreferences.Editor editor=getSharedPreferences("toolbox",MODE_PRIVATE).edit();
        editor.putString("env",env);
        editor.apply();
    }
    private String getEnv(){
        return getSharedPreferences("toolbox",MODE_PRIVATE).getString("env",Toolbox.getDefaultEnv());
    }
}
