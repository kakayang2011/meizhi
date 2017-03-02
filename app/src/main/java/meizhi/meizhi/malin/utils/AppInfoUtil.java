package meizhi.meizhi.malin.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.tencent.bugly.crashreport.CrashReport;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 类描述:App信息获取的工具类
 * 创建人:malin.myemail@163.com
 * 创建时间:16-10-09
 * 备注:{@link }
 * 修改人:
 * 修改时间:
 * 修改备注:
 * 版本:
 */

public final class AppInfoUtil {
    private AppInfoUtil() {
    }

    /**
     * 获取application中<meta-data>的值，渠道名
     *
     * @return Channel Name
     */
    public static String getChannelName(Context context) {
        String channelName;
        try {
            ApplicationInfo appInfo = context.getApplicationContext().getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), PackageManager.GET_META_DATA);
            channelName = appInfo.metaData.getString("PRODUCT");
        } catch (PackageManager.NameNotFoundException e) {
            CrashReport.postCatchedException(e);
            e.printStackTrace();
            channelName = "Exception Channel";
        }
        return channelName;
    }

    /**
     * get app version name
     *
     * @return Version Name
     */
    public static String getAppVersionName(Context context, String defaultAppVersionName) {
        String versionName;
        try {
            PackageInfo applicationInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0);
            versionName = applicationInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            CrashReport.postCatchedException(e);
            e.printStackTrace();
            versionName = defaultAppVersionName;
        }
        return versionName;
    }

    /**
     * get app package name
     *
     * @return Package Name
     */
    public static String getPackageName(Context context, String defaultPackageName) {
        String packageName;
        try {
            packageName = context.getApplicationContext().getPackageName();
        } catch (Throwable e) {
            CrashReport.postCatchedException(e);
            e.printStackTrace();
            packageName = defaultPackageName;
        }
        return packageName;
    }


    private static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        return sw.toString();
    }
}
