package com.lieni.library.toolbox;

import android.app.Application;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lieni.library.easyfloat.EasyFloat;
import com.lieni.library.easyfloat.EasyFloatView;
import com.lieni.library.toolbox.ui.MainActivity;

import java.util.HashMap;
import java.util.Map;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Toolbox {
    public static volatile Toolbox instance;

    Map<String, View.OnClickListener> onClickListeners=new HashMap<>();

    public void setOnDevClickListener(View.OnClickListener clickListener){
        getInstance().onClickListeners.put("dev",clickListener);
    }
    public void setOnDebugClickListener(View.OnClickListener clickListener){
        getInstance().onClickListeners.put("debug",clickListener);
    }
    public void setOnUatClickListener(View.OnClickListener clickListener){
        getInstance().onClickListeners.put("uat",clickListener);
    }
    public void setOnReleaseClickListener(View.OnClickListener clickListener){
        getInstance().onClickListeners.put("release",clickListener);
    }

    public View.OnClickListener getOnClickListener(String tag){
        return getInstance().onClickListeners.get(tag);
    }

    public static Toolbox getInstance() {
        if (instance == null) {
            synchronized (Toolbox.class) {
                if (instance == null) {
                    instance = new Toolbox();
                }
            }
        }
        return instance;
    }

    public static void init(Application application){
        EasyFloat.init(application);
        EasyFloat.add("toolbox", new EasyFloatView() {
            @Override
            public View onCreateView(ViewGroup rootView) {
                View view=LayoutInflater.from(rootView.getContext()).inflate(R.layout.activity_toolbox_float,rootView,false);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(v.getContext(), MainActivity.class);
                        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                        v.getContext().startActivity(intent);
                    }
                });
                return view;
            }

            @Override
            public void onConfig() {
                super.onConfig();
                setInitPosition(400,400);
                addInvalidActivityName(MainActivity.class.getName());
            }
        });
    }
}
