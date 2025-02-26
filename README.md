```
                                      ___ ________                            
                         68b          `MM `MMMMMMMb.                          
                         Y89           MM  MM    `Mb                    /     
  ____     ____  ___  __ ___    ___    MM  MM     MM   _____  ___  __  /M     
 6MMMMb\  6MMMMb `MM 6MM `MM  6MMMMb   MM  MM     MM  6MMMMMb `MM 6MM /MMMMM  
MM'    ` 6M'  `Mb MM69 "  MM 8M'  `Mb  MM  MM    .M9 6M'   `Mb MM69 "  MM     
YM.      MM    MM MM'     MM     ,oMM  MM  MMMMMMM9' MM     MM MM'     MM     
 YMMMMb  MMMMMMMM MM      MM ,6MM9'MM  MM  MM        MM     MM MM      MM     
     `Mb MM       MM      MM MM'   MM  MM  MM        MM     MM MM      MM     
L    ,MM YM    d9 MM      MM MM.  ,MM  MM  MM        YM.   ,M9 MM      YM.  , 
MYMMMM9   YMMMM9 _MM_    _MM_`YMMM9'Yb_MM__MM_        YMMMMM9 _MM_      YMMM9                        

```  

# 说明  
>一个灵活、高效并且轻量的串口通信框架，让串口操作变得简单易用，大家在使用遇到问题了欢迎指出，我会第一时间修复，强烈建议以依赖的方式导入，这样避免代码同步，有问题或建议？请通过博客、qq群联系我们。

# 效果图  
第三方测试报告  
<div style="display:flex; justify-content:center;">
    <img src="https://github.com/cl-6666/serialPort/blob/master/img/test_erformance1.png" alt="Image 1" style="width:45%;">
    <img src="https://github.com/cl-6666/serialPort/blob/master/img/test_erformance2.png" alt="Image 2" style="width:45%;">
</div>
<img src="https://github.com/cl-6666/serialPort/blob/master/img/test_erformance3.png" width="440" height="320" alt="演示"/>


自测机型
测试机型：RK3399  
测试系统：Android8  
测试分辨率： 1920x1200  
测试时间：持续心跳发送一个星期，无任何问题  
<img src="https://github.com/cl-6666/serialPort/blob/master/img/sample_picture.png" width="650" height="360" alt="演示"/>  
 
# 下载体验  
<img src="https://github.com/cl-6666/serialPort/blob/master/img/QRCode_336.png"><img/><br/>
因为图方便，用手机浏览器扫码可下载

# PC端串口调试助手
<img src="https://github.com/cl-6666/serialPort/blob/master/img/pc_ck.jpg" width="440" height="320" alt="演示"/>
通过网盘分享的文件：pc串口调试助手.zip
链接: https://pan.baidu.com/s/1DL2TOHz9bl9RIKIG3oCSWw?pwd=f7sh 提取码: f7sh 
--来自百度网盘超级会员v9的分享

# 项目依赖
``` Gradle
allprojects {
           repositories {
                    mavenCentral()
                    maven { url 'https://jitpack.io' }
             }
	}
```

Step 2. Add the dependency

``` Gradle
dependencies {
    implementation 'com.github.cl-6666:serialPort:v4.1.1'
}
```  
### 属性支持
|	属性	|	参数	|
|	---		|	---		|
|	数据位	|	5,6,7,8 ;默认值8	|
|	校验位	|	无奇偶校验(NONE), 奇校验(ODD), 偶校验(EVEN), 0校验(SPACE), 1校验(MARK); 默认无奇偶校验，对应关系NONE(0)-ODD(1)-EVEN(2)-SPACE(3)-MARK(4);	|
|	停止位		|	1,2 ;默认值1	|
|	标志位	|	不使用流控(NONE), 硬件流控(RTS/CTS), 软件流控(XON/XOFF); 默认不使用流控	|

### 错误码参照表  
|	错误编码	|	错误说明	|
|	---		|	---		|
|	1000	|	未知错误	|
|	1001	|	串口打开失败	|
|	1002	|	串口数据发送失败	|
|	1003	|	未知的串口类型，请检查串口路线是否错误	|
|	1004	|	串口数据接收失败	|
|	1005	|	未初始化的串口	|
|	1006	|	文件未找到	|
|	1007	|	串口数量不符合要求,目前最大只支持6路串口	|
|	1008	|	黏包数量配置不合法，请检查是否配置了错误的参数，或者没有配置	|
|	1009	|	权限被拒绝,请检查是否有串口的读写权限，建议运行SelectSerialPortActivity查看	|

  
### 框架初始化，在Application里面,支持动态配置，根据场景灵活初始化，一般简单初始化即可

- 参数：上下文，是否打开日志，日志标签，简单初始化
``` Java
   public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化串口框架  简单配置  
         * 是否打开日志、日志标识、串口接发间隔速度 
         * 数据量单一情况下建议设置100  数据量大情况建议设置500
         */
       SerialUtils.getInstance().init(this,true,"TAG",100);
    }
}
```

- 初始化自定义黏包配置，默认使用的是BaseStickPackageHelper
``` Java
   public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化串口框架  简单配置  
         * 是否打开日志、日志标识、串口接发间隔速度 
         * 数据量单一情况下建议设置100  数据量大情况建议设置500
         */
       SerialUtils.getInstance().init(this,true,"TAG",100);

      //串口粘包配置，框架默认使用BaseStickPackageHelper,无特殊需求，可不设置，多串口情况需要配置,这里面配置需要和manyOpenSerialPort里面的顺序对应，总共支持6路
        SerialUtils.getInstance().setStickPackageHelper(new BaseStickPackageHelper(),
                new BaseStickPackageHelper());
    }
}
```  

- 带校验位方式的初始化，如果无特殊设置，使用简单初始化
``` Java
   public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
         /**
         * 设置停止位、数据位、校验位
         */
        SerialUtils.getInstance().init(this,true,"TAG", 50,8,0,1);
    }
}
```

- 假如需要详细配置日志参数，可以使用以下方法，日志框架地址：https://github.com/cl-6666/xlog
``` Java
   public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //配置串口相关参数
        SerialConfig serialConfig = new SerialConfig.Builder()
                //配置日志参数
                .setXLogConfig(logConfig)
                //配置发送间隔速度
                .setIntervalSleep(200)
                //是否开启串口重连   目前还没有实现
                .setSerialPortReconnection(false)
                //标志位
                .setFlags(0)
                 //数据位
                .setDatabits(8)
                 //停止位
                .setStopbits(1)
                 //校验位：0 表示无校验位，1 表示奇校验，2 表示偶校验
                .setParity(0)
                .build();
        SerialUtils.getInstance().init(this, serialConfig);
    }
}
```

- 业务代码设置参数
``` Java
   //设置数据位
  SerialUtils.getInstance().getmSerialConfig().setDatabits();
   //设置停止位
  SerialUtils.getInstance().getmSerialConfig().setStopbits();
   //校验位：0 表示无校验位，1 表示奇校验，2 表示偶校验
  SerialUtils.getInstance().getmSerialConfig().setParity();
   //标志位
  SerialUtils.getInstance().getmSerialConfig().setFlags();
   //设置串口接收间隔时间
  SerialUtils.getInstance().getmSerialConfig().setIntervalSleep();
  //自定义粘包处理类，下面有介绍说明
  SerialUtils.getInstance().setStickPackageHelper("自定义粘包处理类");
```

### 数据监听状态以及打开状况

``` Java
        SerialUtils.getInstance().setmSerialPortDirectorListens(new SerialPortDirectorListens() {
            /**
             *  接收回调
             * @param bytes 接收到的数据
             * @param serialPortEnum  串口类型
             */
            @Override
            public void onDataReceived(byte[] bytes, SerialPortEnum serialPortEnum) {
                Log.i(TAG, "当前接收串口类型：" + serialPortEnum.name());
                Log.i(TAG, "onDataReceived [ byte[] ]: " + Arrays.toString(bytes));
                Log.i(TAG, "onDataReceived [ String ]: " + new String(bytes));
            }

            /**
             *  发送回调
             * @param bytes 发送的数据
             * @param serialPortEnum  串口类型
             */
            @Override
            public void onDataSent(byte[] bytes, SerialPortEnum serialPortEnum) {
                Log.i(TAG, "当前发送串口类型：" + serialPortEnum.name());
                Log.i(TAG, "onDataSent [ byte[] ]: " + Arrays.toString(bytes));
                Log.i(TAG, "onDataSent [ String ]: " + new String(bytes));
            }

            /**
             * 串口打开回调
             * @param serialPortEnum  串口类型
             * @param device  串口号
             * @param status 打开状态
             */
            @Override
            public void openState(SerialPortEnum serialPortEnum, File device, SerialStatus status) {
                XLog.i("串口打开状态："+device.getName()+"---打开状态："+status.name());
                switch (serialPortEnum) {
                    case SERIAL_ONE:
                        switch (status) {
                            case SUCCESS_OPENED:
                                ToastUtils.show("串口打开成功");
                                break;
                            case NO_READ_WRITE_PERMISSION:
                                ToastUtils.show("没有读写权限");
                                break;
                            case OPEN_FAIL:
                                ToastUtils.show("串口打开失败");
                                break;
                        }
                        break;
                    case SERIAL_TWO:
                        XLog.i("根据实际多串口场景演示");
                        break;
                }
            }
        });
	
	//多路串口打开逻辑.....
```

### 打开多路串口，目前支持6路串口，使用的时候请在回调方法之后执行

``` Java
        //多串口演示
        List<Driver> list2=new ArrayList<>();
        //串口ttyS4，这里假如是多串口，也需要配置对应的黏包规则，具体请参考Application里面
        list2.add(new Driver("/dev/ttyUSB0", "115200"));
        list2.add(new Driver("/dev/ttyUSB1", "115200"));
        list2.add(new Driver("/dev/ttyS4", "115200"));
        SerialUtils.getInstance().manyOpenSerialPort(list2);

```


### 发送数据

- 参数：发送哪路串口，发送数据 byte[]
- 返回：发送是否成功

``` Java
//todo 这里默认发送一路串口，根据用户自定义
boolean sendBytes = SerialUtils.getInstance().sendData(SerialPortEnum.SERIAL_ONE, sendContentBytes);
```

### 关闭串口

``` Java
SerialUtils.getInstance().serialPortClose();

```

### 粘包处理
1. [不处理](https://github.com/cl-6666/serialPort/blob/master/serial_lib/src/main/java/com/kongqw/serialportlibrary/stick/BaseStickPackageHelper.java)(默认)
2. [首尾特殊字符处理](https://github.com/cl-6666/serialPort/blob/master/serial_lib/src/main/java/com/kongqw/serialportlibrary/stick/SpecifiedStickPackageHelper.java)
3. [固定长度处理](https://github.com/cl-6666/serialPort/blob/master/serial_lib/src/main/java/com/kongqw/serialportlibrary/stick/StaticLenStickPackageHelper.java)
4. [动态长度处理](https://github.com/cl-6666/serialPort/blob/master/serial_lib/src/main/java/com/kongqw/serialportlibrary/stick/VariableLenStickPackageHelper.java)
支持自定义粘包处理，第一步实现[AbsStickPackageHelper](https://github.com/cl-6666/serialPort/blob/master/serial_lib/src/main/java/com/kongqw/serialportlibrary/stick/AbsStickPackageHelper.java)接口


### 通用疑问解答  
1.假如正常打开串口，但是收不到消息的话，请检查你的波特率  
2.假如提示没有权限的话，请通过运行SelectSerialPortActivity起来看一下是否有读写权限  
3.假如能接收到数据，但出现接收数据不完整，请检查波特率是否对的，然后修改一下数据接收的间隔时间  
4.在grdle8.x依赖不了问题,依赖方式有所改变    
5.在pc端调试工具发送指令，不要转16进制发送，不然会出现接收不了消息情况，后续版本会优化这个问题  
6.错误码1008，代表当前打开的串口和黏包配置的串口数量不匹配，请检查Application里面setStickPackageHelper方法是否和打开串口数量一致  

``` java
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```  
5.有好的建议或者问题欢迎提出

### 版本更新历史：  
[![](https://jitpack.io/v/cl-6666/serialPort.svg)](https://jitpack.io/#cl-6666/serialPort) 

- v4.1.1：(2024年10月11日)
  - 增加多串口黏包配置
  - 增加错误码，方便开发者排查问题
  - 现有代码优化

- v4.0.1：(2024年04月18日)
  - 修复校验位无效问题
  - demo打开串口和关闭串口相关优化

- v4.0.0：(2024年04月06日)
  - 增加对外设置停止位、数据位、校验位、流控等参数
  - 代码优化

- v3.1.7：(2023年11月09日)
  - 增加对外日志参数配置，也支持默认配置
  - 增加对外串口读写速度参数设置

### QQ 群：458173716  
<img src="https://github.com/cl-6666/serialPort/blob/master/img/qq2.jpg" width="350" height="560" alt="演示"/>  

### 感谢
- [AndroidSerialPort](https://github.com/kongqw/AndroidSerialPort)

### 作者博客地址    
博客地址：https://blog.csdn.net/a214024475/article/details/113735085  

