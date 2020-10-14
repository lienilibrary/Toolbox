package com.lieni.toolbox;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import com.lieni.library.toolbox.Toolbox;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Toolbox.init(this);
        Toolbox.getInstance().setOnDevClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"test",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
