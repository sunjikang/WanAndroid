package com.xing.main.update.ui;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
 import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


 import com.xing.main.R;
import com.xing.main.update.AppUpdater;
import com.xing.main.update.bean.AppInfo;
import com.xing.main.update.net.INetDownloadCallBack;
import com.xing.main.update.util.AppUtil;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

/**
 * 自定义Dialog
 */
public class ShowAppInfoDialog extends DialogFragment {

    public String FILE_SAVE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/";
    String FILE_NAME = "download.apk";
    public static final String KEY_DOWNLOAD_APP_INFO = "download_app_info";
    public  AppInfo appInfo = null;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private static final String TAG = "selenium";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取AppInfo数据
        Bundle arguments = getArguments();
        if (arguments != null) {
            appInfo = arguments.getParcelable(KEY_DOWNLOAD_APP_INFO);
        }
    }

     @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_show_app_info, null);
        TextView title = root.findViewById(R.id.tv_title);
        TextView content = root.findViewById(R.id.tv_content);
        Button updateBtn = root.findViewById(R.id.btn_update);
        title.setText(appInfo.getTitle());
        content.setText(appInfo.getContent());
        bindEvent(updateBtn);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    protected void bindEvent(final Button updateBtn) {
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBtn.setEnabled(false);
                //4.点击下载
                //先创建文件夹再创建文件
                File dir = new File(FILE_SAVE_PATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, FILE_NAME);
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                AppUpdater.getInstance().getNetManager().download(appInfo.getUrl(), file, new INetDownloadCallBack() {

                    @Override
                    public void success(File apkFile) {
                        updateBtn.setEnabled(true);
                        dismiss();
                        //check md5
                        String md5 = AppUtil.getFileMd5(apkFile);
                        Log.i(TAG, "md5: " + md5);
                        if (md5 == null || md5 != appInfo.getMd5()) {
                            //安装下载程序
                            AppUtil.installApk(getActivity(), apkFile);
                        } else {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(), "下载文件已损坏", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void progress(final int progress) {
                        Log.i(TAG, "progress: " + progress);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                updateBtn.setText(progress + "%");

                            }
                        });
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                updateBtn.setEnabled(true);
                                Toast.makeText(getActivity(), "文件下载失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }, ShowAppInfoDialog.this);
            }
        });
    }

    public static void show(FragmentActivity activity, AppInfo appInfo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DOWNLOAD_APP_INFO, appInfo);
        ShowAppInfoDialog dialog = new ShowAppInfoDialog();
        dialog.setArguments(bundle);
        dialog.show(activity.getSupportFragmentManager(), "showAppInfoDialog");
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.i(TAG, "onDismiss: canceled");
        AppUpdater.getInstance().getNetManager().cancel(ShowAppInfoDialog.this);
    }
}
