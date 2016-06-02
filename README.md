# react-native-file-md5-android
本项目为React Native的Android原生模块，是 https://github.com/lovetuzitong/FileMD5 项目的react-native封装模块，用于计算指定文件的md5值。

[![npm version](http://img.shields.io/npm/v/react-native-file-md5-android.svg?style=flat-square)](https://npmjs.org/package/react-native-file-md5-android "View this project on npm")
[![npm downloads](http://img.shields.io/npm/dm/react-native-file-md5-android.svg?style=flat-square)](https://npmjs.org/package/react-native-file-md5-android "View this project on npm")
[![npm licence](http://img.shields.io/npm/l/react-native-file-md5-android.svg?style=flat-square)](https://npmjs.org/package/react-native-file-md5-android "View this project on npm")


使用本模块计算指定文件的md5值

### 安装

```bash
npm install react-native-file-md5-android --save
```

### 添加到你的android项目

* 在 `android/setting.gradle` 文件中添加以下内容

```gradle
...
include ':RNFileMD5Module', ':app'
project(':RNFileMD5Module').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-file-md5-android')
```

* 在 `android/app/build.gradle` 文件中添加如下内容

```gradle
...
dependencies {
    ...
    compile project(':RNFileMD5Module')
}
```

* 注册模块 >= 0.18 (在 MainActivity.java 文件中添加内容)

```java
import com.j010wdz.filemd5.RNFileMD5Package;  // <--- import

public class MainActivity extends ReactActivity {
  ......

  @Override
  protected List<ReactPackage> getPackages() {
    return Arrays.<ReactPackage>asList(
            new MainReactPackage(),
            new RNFileMD5Package()); // <------ 添加这行代码到你的MainActivity类
  }

  ......

}
```

* 注册模块 <= 0.17 (在 MainActivity.java 文件中添加内容)

```java
import com.j010wdz.filemd5.RNFileMD5Package;  // <--- import

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {
  ......

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mReactRootView = new ReactRootView(this);

    mReactInstanceManager = ReactInstanceManager.builder()
      .setApplication(getApplication())
      .setBundleAssetName("index.android.bundle")
      .setJSMainModuleName("index.android")
      .addPackage(new MainReactPackage())
      .addPackage(new RNFileMD5Package()) // <------ 添加这行代码到你的MainActivity类
      .setUseDeveloperSupport(BuildConfig.DEBUG)
      .setInitialLifecycleState(LifecycleState.RESUMED)
      .build();

    mReactRootView.startReactApplication(mReactInstanceManager, "AndroidRNSample", null);

    setContentView(mReactRootView);
  }

  ......

}
```

## 示例 打开图片选择窗口
```javascript
var FileMD5Android = require('react-native-file-md5-android');

FileMD5Android.getFileMD5(filePath).then((md5)=>{
        alert('文件的MD5值为' + md5);
    }, (code, message)=>{
      //alert(code);
    });
```

## License
MIT
