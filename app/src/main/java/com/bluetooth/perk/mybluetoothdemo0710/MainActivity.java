package com.bluetooth.perk.mybluetoothdemo0710;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private final int REQUEST_AUTHORITY = 0;
    private final int REQUEST_ENABLE_BT = 1;
    private final int REQUEST_CAMERA = 2;
    private final int REQUEST_WRITE_EXTERNAL_STORAGE = 3;

    private BluetoothAdapter mBluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //1、获取设备蓝牙对象
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            mBluetoothAdapter = bluetoothManager.getAdapter();
        }

        //2、通过mBluetoothAdapter是否为空来判断设备是否具备蓝牙模块
        if(mBluetoothAdapter == null){
            Toast.makeText(this,"该设备不支持蓝牙",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"支持蓝牙",Toast.LENGTH_SHORT).show();
            if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
                Toast.makeText(this,"该蓝牙不支持BLE特性",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"设备支持BLE特性",Toast.LENGTH_SHORT).show();
            }
        }

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_AUTHORITY);
        //检查Manifest中权限是否有被允许（申请的权限必须在Manifest中提前注册才行）
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){//检测某个权限是否已经被授权，如果没有授权
            //如果没有被允许

        }

    }

    @Override //此回调用函数可根据用户的选择情况来进行逻辑处理
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_AUTHORITY:{
                //如果请求被cancelled，grantResult长度为空（=申请的权限数目grantResult.length）
                if ( grantResults[0] == PackageManager.PERMISSION_DENIED){
                    //如果权限不被允许的逻辑如下
                    Toast.makeText(this,"相机权限不允许",Toast.LENGTH_SHORT).show();
                    //ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA);

                }else if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this,"文件权限读取不允许",Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //当判断了蓝牙适配器对象已经获取
        if (mBluetoothAdapter != null){
            if (!mBluetoothAdapter.isEnabled()){//判断蓝牙是否打开

                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);//Settings.ACTION_BLUETOOTH_SETTINGS
                //这个Intent action请求一个Dialog
                startActivityForResult(enableIntent,REQUEST_ENABLE_BT);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_CANCELED){
            //如果点击Dialog的“取消”按钮
            Toast.makeText(this,"取消了",Toast.LENGTH_SHORT).show();
            finish();
        }else if (requestCode == REQUEST_ENABLE_BT &&resultCode == RESULT_OK){
            //如果点击Dialog的“确认”按钮
            Toast.makeText(this,"蓝牙已经打开",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBluetoothAdapter.disable();

    }
}
