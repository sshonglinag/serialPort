package com.cl.myapplication;

import android.app.Application;

import com.cl.log.XLogConfig;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.kongqw.serialportlibrary.SerialConfig;
import com.kongqw.serialportlibrary.SerialUtils;
import com.kongqw.serialportlibrary.stick.BaseStickPackageHelper;

/**
 * 项目：serialPort
 * 作者：Arry
 * 创建日期：2021/10/20
 * 描述：
 * 修订历史：
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 Toast 框架
        ToastUtils.init(this);
//        SerialUtils.getInstance().init(this,true,"TAG",50);
        /**
         * 设置停止位、数据位、校验位
         */
        SerialUtils.getInstance().init(this,true,"TAG",
                50,8,0,1);

        //串口粘包配置，框架默认使用BaseStickPackageHelper,无特殊需求，可不设置，多串口情况需要配置
//        SerialUtils.getInstance().setStickPackageHelper(new BaseStickPackageHelper(),
//                new BaseStickPackageHelper());

        //初始化日志框架
//        XLogConfig logConfig = new XLogConfig.Builder()
//                //全局TAG
//                .setGlobalTag("TAG")
//                //是否包含线程信息
//                .setWhetherThread(true)
//                //Xlog是否可用
//                .setWhetherToPrint(true)
//                //是否存储日志到本地  log文件的有效时长，单位毫秒，<=0表示一直有效
//                .setStoreLog(true, 0)
//                //堆栈的深度
//                .setStackDeep(5)
//                .setInjectSequence(new XLogConfig.JsonParser() {
//                    @Override
//                    public String toJson(Object src) {
//                        String json = new Gson().toJson(src);
//                        return json;
//                    }
//                }).build();
//
//        SerialConfig serialConfig = new SerialConfig.Builder()
//                .setXLogConfig(logConfig)
//                .setIntervalSleep(100)
//                .setFlags(0)
//                .setDatabits(8)
//                .setStopbits(1)
//                .setParity(0)
//                .build();
//        SerialUtils.getInstance().init(this, serialConfig);

    }
}
