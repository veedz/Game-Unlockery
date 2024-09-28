package spoof.tai.anying;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.security.KeyStore;
import java.util.Arrays;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

@SuppressLint("DiscouragedPrivateApi")
@SuppressWarnings("ConstantConditions")
public class GAMEUNLOCKER implements IXposedHookLoadPackage {

    private static final String TAG = HAKANKUSIA.class.getSimpleName();
    // Packages to Spoof as Poco F4
    private static final String[] packagesToChangePocoF4 = {
        "com.android.camera"
    };

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) {

        String packageName = loadPackageParam.packageName;

        // Poco F4
        if (Arrays.asList(packagesToChangePocoF4).contains(packageName)) {
            propsToChangePocoF4();
            XposedBridge.log("Dipalsukan " + packageName + " sebagai Poco F4");
        }
    }

    // Poco F4
    // Props to Spoof as Poco F4
    private static void propsToChangePocoF4() {
        setPropValue("BRAND", "Xiaomi");
        setPropValue("MANUFACTURER", "Xiaomi");
        setPropValue("DEVICE", "munch");
        setPropValue("MODEL", "munch");
    }

    private static void setPropValue(String key, Object value) {
        try {
            Log.d(TAG, "Mendefinisikan prop " + key + " ke " + value.toString());
            Field field = Build.class.getDeclaredField(key);
            field.setAccessible(true);
            field.set(null, value);
            field.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            XposedBridge.log("Gagal untuk set prop: " + key + "\n" + Log.getStackTraceString(e));
        }
    }
}