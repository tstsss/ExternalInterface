package com.test.toasterlibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.egret.egretframeworknative.engine.EgretGameEngine;

public class ToasterMessage extends Activity {

    protected static final String TAG = "ToasterMessage";

    private EgretGameEngine gameEngine;
    private Context context;

    public static void s(Context c, String message){

        Toast.makeText(c,message,Toast.LENGTH_SHORT).show();

    }

    private interface IRuntimeInterface {
        public void callback(String message);
        // 因为遗留问题 callBack 也是接受的
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameEngine = new EgretGameEngine();
        context = this;
        setInterfaces();
    }

    private void setInterfaces() {
        // Egret（TypeScript）－Runtime（Java）通讯
        // setRuntimeInterface(String name, IRuntimeInterface interface) 用于设置一个runtime的目标接口
        // callEgretInterface(String name, String message) 用于调用Egret的接口，并传递消息
        gameEngine.setRuntimeInterface("sendToAndroid", new IRuntimeInterface() {
            @Override
            public void callback(String message) {
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
                gameEngine.callEgretInterface("sendToJS", "Hello VietNam");
            }
        });
    }

}
