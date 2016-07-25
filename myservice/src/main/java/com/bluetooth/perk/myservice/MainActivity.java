package com.bluetooth.perk.myservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startService;
    private Button stopService;

    private Button bindService;
    private Button unbindService;
    private MyService.DownloadBinder downloadBinder;
    //1、创建ServiceConnection匿名类
    private ServiceConnection connection = new ServiceConnection() {
        //重写onServiceConnected()和onServiceDisconnected();
        //这两个方法分别在活动与服务“成功绑定”和“解绑定”的时候被调用
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBinder = (MyService.DownloadBinder) iBinder;
            //通过向下转型得到DownLBinder实例
            //这个实例使活动和服务之间紧密联系起来
            //这样就可以在活动中根据具体需求调用DownLoadBinder中任何public方法
            //实现指挥服务的功能
            downloadBinder.startDownLoad();
            downloadBinder.getProgress();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews(){
        startService = (Button) findViewById(R.id.start_service);
        startService.setOnClickListener(this);
        stopService = (Button) findViewById(R.id.stop_service);
        stopService.setOnClickListener(this);
        bindService = (Button) findViewById(R.id.bind_service);
        bindService.setOnClickListener(this);
        unbindService = (Button) findViewById(R.id.unbind_service);
        unbindService.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_service:
                Intent startIntent = new Intent(this,MyService.class);
                //每个服务只会存在一个实例，不管调用多少次startService()，只调用一次stopService()或stopSelf()
                //服务就会停止
                startService(startIntent);
                break;
            case R.id.stop_service:
                //如果没有点击StopService服务就会一直处于运行状态
                //在服务中服务自己调用stopSelf()，服务可以自己停止
                Intent stopService = new Intent(this,MyService.class);

                stopService(stopService);
                break;

            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                //BIND_AUTO_CREATE：活动和服务进行绑定后自动创建服务
                // 使MyService 中的 onCreate()方法得到执行
                // 但 onStartCommand()方法不会执行
                bindService(bindIntent,connection,BIND_AUTO_CREATE);//绑定服务

                break;
            case R.id.unbind_service:
                //服务如果没有绑定就触发解绑功能就会出现非法操作异常Service not registered
                unbindService(connection);

                break;
            default:
                break;
        }
    }
}

/**绑定服务的步骤：
 * 1、创建一个继承自IBinder的方法类（该类中有具体的想要执行的方法，一般为自定义Service内部类）
 * 2、在自定义Service中创建该方法类实例化后的对象，并在IBinder方法中返回该实例化对象
 * 3、在操作view的Activity中创建之前“方法类成员变量对”，创建ServiceConnection实例化对象并重写onServiceConnected()和onServiceDisconnected()抽象方法
 * 4、在onServiceConnected()方法中i：实现IBinder的向下转型于“方法类成员变量对象”;ii：根据需求调用方法类中的方法
 * 5、完成以上部署
 *    绑定服务操作：
 *      i  创建“绑定意图”，通过bindService(Intent intent，ServiceConnection connection,int flag)启动服务
 *         **三个参数，分别是：intent对象，ServiceConnection对象，flag标签(常用：BIND_AUTO_CREATE[表示活动和服务进行绑定之后自动“创建服务”])
 *    解绑服务操作：
 *      i 通过unbindService(ServiceConnection connection)完成解绑操作；
 *
 *  (ss)startService(Intent intent)和(bs)bindService(Intent intent,ServiceConnection conn,int flags)区别
 *  1、“只通过”ss启动的服务不和Activity同生共死，“只通过”bs启的Service和Activity同生共死
 *  2、命周期：ss启动Service ：onCreate()->onStartCommand();
 *                  (当ss被多次调用后)：只执行onStartCommand()1次...2次...3次...
 *            stoopService(Intent intent)销毁Service：onDestroy();此时服务消失
 *
 *            bs启动Service：onCreate()->onBind()(之后执行方法类中“被绑定的方法")
 *                  (服务一旦属于“绑定”就无法通过stopService()来销毁服务)
 *                  (bs只可以执行一次,多次触发后不会像ss一样继续相应)
 *                  (bs之后可以ss触发就只执行startCommand()，无论触发多少次)
 *            unbindService(ServiceConnection conn)销毁Service：onDestroy();此时服务消失
 *  3、如果ss和bs同时执行，要同时stopService()和unbindService()同时执行以后才可以执行onDestroy()销毁Service
 */

