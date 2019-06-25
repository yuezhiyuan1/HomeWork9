package com.example.lenovo.homework9;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.example.lenovo.homework9.Model.bean.VersionBean;
import com.example.lenovo.homework9.base.BaseActivity;
import com.example.lenovo.homework9.intc.IBaseView;
import com.example.lenovo.homework9.intc.IPresenter;
import com.example.lenovo.homework9.intc.news.SplashContract;
import com.example.lenovo.homework9.presenter.SplashPersenter;
import com.example.lenovo.homework9.utils.AppUtils;
import com.example.lenovo.homework9.utils.DownloadUtils;
import com.example.lenovo.homework9.utils.SystemUtils;

import java.util.List;

import me.jessyan.progressmanager.body.ProgressInfo;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class SplashActivity extends BaseActivity implements SplashContract.View, EasyPermissions.PermissionCallbacks {

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        /*startActivity(new Intent(this, LoginActivity.class));
        finish();*/
    }

    @Override
    protected IPresenter initMvpPresnter() {
         return new SplashPersenter();
    }

    @Override
    protected void initData() {
        String pgname = SystemUtils.getPgName(context);
        ((SplashPersenter)presenter).getVersion(pgname,"wanandroid");
    }


    @Override
    public void versionReturn(final VersionBean versionBean) {
        if(versionBean.getCode() == 200){
            String pgname = SystemUtils.getPgName(context);
            long versioncode =  SystemUtils.getVersionCode(context,pgname);
            if(versioncode < versionBean.getData().get(0).getVersioncode()){
                String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if(!EasyPermissions.hasPermissions(context,perms)){
                    EasyPermissions.requestPermissions((Activity) context,"需要添加权限",1,perms);
                    return;
                }
                //下载最新的apk
                String name = DownloadUtils.getNameFromUrl(versionBean.getData().get(0).getUrl());
                DownloadUtils.getInstance().download(versionBean.getData().get(0).getUrl(), "apks", name, new DownloadUtils.OnDownloadListener() {
                    @Override
                    public void onDownloadSuccess() {
                        String path = DownloadUtils.getFileByUrl("apks",versionBean.getData().get(0).getUrl());
                        installApkO(context,path);
                    }

                    @Override
                    public void onDownloading(ProgressInfo progress) {

                    }

                    @Override
                    public void onDownloadFailed() {

                    }
                });
                return;
            }
        }
        gotoMainActivity();
    }

    /**
     * 兼容8.0安装位置来源的权限
     */
    private void installApkO(Context context, String downloadApkPath) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //是否有安装位置来源的权限
            boolean haveInstallPermission = getPackageManager().canRequestPackageInstalls();
            if (haveInstallPermission) {
                AppUtils.installApk(context, downloadApkPath);
            } else {
                Uri packageUri = Uri.parse("package:"+ SystemUtils.getPgName(context));
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,packageUri);
                startActivity(intent);
            }
        } else {
            AppUtils.installApk(context, downloadApkPath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
        }
        if (requestCode == 1) {

            //String successDownloadApkPath = SDUtils.getSDPath()+ "QQ.apk";
            //installApkO(MainActivity.this, successDownloadApkPath);
        }
    }

    private void gotoMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void OnSuccess() {

    }

    @Override
    public void OnFiule(String st) {

    }
}
