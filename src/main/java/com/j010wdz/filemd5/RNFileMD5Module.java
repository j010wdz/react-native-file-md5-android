package com.j010wdz.filemd5;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class RNFileMD5Module extends ReactContextBaseJavaModule {

    private static final String E_FILE_NOT_EXISTS = "E_FILE_NOT_EXISTS";
    private static final String E_CALCULATE_FILE_MD5_FAILED = "E_CALCULATE_FILE_MD5_FAILED";

    private ReactApplicationContext reactContext;
    private Promise promise;

    public RNFileMD5Module(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
      return "FileMD5Android";
    }

    @ReactMethod
    protected void getFileMD5(final String filePath, Promise promise) {

        this.promise = promise;

        File file = new File(filePath);
        if (file == null || !file.exists() || !file.isFile()) {
            this.promise.reject(E_FILE_NOT_EXISTS, "文件不存在");
            return;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            this.promise.reject(E_CALCULATE_FILE_MD5_FAILED, "计算文件md5失败");
            return;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        this.promise.resolve(bigInt.toString(16));
    }
}
