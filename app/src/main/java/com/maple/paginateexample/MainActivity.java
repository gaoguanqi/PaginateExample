package com.maple.paginateexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.PermissionUtils;
import com.maple.paginateexample.paginate.MyActivity;
import com.maple.paginateexample.paginate.PermissionAdapter;
import com.maple.paginateexample.utils.LogUtils;
import com.maple.paginateexample.utils.PermissionUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MaterialDialog mDialog;
    private MaterialDialog.Builder mBuilder;
    private PermissionAdapter mPermissionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(v -> {
            //startActivity(new Intent(MainActivity.this, PaginateActivity.class));
            startActivity(new Intent(MainActivity.this, MyActivity.class));
        });
        applyPermissions();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        applyPermissions();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.logGGQ("onActivityResult   requestCode:"+requestCode+"resultCode:"+resultCode);
    }

    private void applyPermissions() {
        PermissionUtil.applyPermissions(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {

                LogUtils.logGGQ("权限通过");
            }

            @Override
            public void onRequestPermissionFailure(List<String> permissions) {
                LogUtils.logGGQ("权限拒绝");
                showPermissionFailDialog(permissions);
            }

            @Override
            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
                LogUtils.logGGQ("权限不在提示");

                showPermissionFailDialog(permissions);
            }
        },new RxPermissions(this));
    }

    private void showPermissionFailDialog(List<String> permissions) {
        if(mBuilder == null){
            mBuilder = new MaterialDialog.Builder(MainActivity.this);
            View view = MainActivity.this.getLayoutInflater().inflate(R.layout.layout_permissions, null, false);
            RecyclerView recycler = view.findViewById(R.id.recycler);
            LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recycler.setLayoutManager(manager);
            mPermissionAdapter = new PermissionAdapter(MainActivity.this,permissions);
            recycler.setAdapter(mPermissionAdapter);
            Button btnCancle = view.findViewById(R.id.btn_cancle);
            btnCancle.setOnClickListener(v -> {
                if(mDialog != null && mDialog.isShowing()){
                    mDialog.cancel();
                }
            });
            btnCancle.setVisibility(View.GONE);
            Button btnConfirm = view.findViewById(R.id.btn_confirm);
            btnConfirm.setOnClickListener(v -> {
                if(mDialog != null && mDialog.isShowing()){
                    mDialog.cancel();
                }
                mBuilder = null;
                mDialog = null;
                PermissionUtils.launchAppDetailsSettings();
            });
            mBuilder.cancelable(false);
            mBuilder.customView(view, false);
        }

        if(mDialog == null){
            mDialog = mBuilder.show();
        }
    }

}
