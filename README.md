# SearchItem
商品条形码／二维码搜索 Android APP  

---
### 下载
* [Google Play](https://play.google.com/store/apps/details?id=com.breadykid.searchitem)
欢迎评论～～～

--
### 简述
*一个轻量的商品条码／二维码扫描工具，并能够实时查询淘宝最低价。*

该应用采用Google Zxing进行条码／二维码的扫描，参考官网与[chentao0707](https://github.com/chentao0707/ZXingProject)的精简代码；取得扫描返回值后用Retrofit进行网络通信，从中国物品编码网查询得到商品的官方信息，该网站接口返回的是一个html，这里通过Jsoup解析；再查询淘宝该商品的最低价(前三名)，解析返回json并处理显示。

中文字体：*NotoSansCJKsc-Light*  
风格：*Theme.Material.Light.NoActionBar*  
组件：*cart && button*  
动画：*触摸反馈*

接口来源：(网页接口)  

* [中国商品信息服务平台](http://search.anccnet.com)
* [淘宝网](https://www.taobao.com)

--
### 适用版本

```
minSdkVersion 21
targetSdkVersion 25
```
--
### 第三方组件
* 网络通信：[Retrofit](https://square.github.io/retrofit/)
* 网页解析：[Jsoup](https://jsoup.org/)
* [FireBase](https://firebase.google.com/)： 后台数据分析[Analyses](https://firebase.google.com/docs/analytics/android/start/) && 实时数据库[Real DataBase](https://firebase.google.com/docs/database/android/start/)
* 控件绑定：[ButterKnife](http://jakewharton.github.io/butterknife/)
* 条码／二维码扫描：[Zxing](https://github.com/zxing/zxing)

--
### 依赖

```
dependencies {
    ...
    
    // Google zxing扫描
    compile 'com.google.zxing:zxing-parent:3.3.0'
    compile files('libs/zxing.jar')
    // 控件绑定
    compile 'com.jakewharton:butterknife:7.0.1'
    // Google FireBase
    compile 'com.google.firebase:firebase-core:9.8.0'
    compile 'com.google.firebase:firebase-database:9.8.0'
    // 网络通信
    compile 'com.squareup.okhttp3:okhttp:3.4.2'
    compile 'com.squareup.retrofit2:retrofit:2.0.2'
    // jsoup HTML parser library @ http://jsoup.org/
    compile 'org.jsoup:jsoup:1.10.1'
}

```
--
### 权限

```
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="android.permission.VIBRATE" />
    <uses-permission android:name="android.permission.CAMERA" />
```
--
### 展示
<img src="https://github.com/BreadKid/SearchItem/blob/master/readme_pic/Screenshot_2016-12-14-19-21-02-895_com.breadykid.searchitem.png?raw=true" width = "270" height = "480"/>   <img src="https://github.com/BreadKid/SearchItem/blob/master/readme_pic/Screenshot_2016-12-14-19-22-26-141_com.breadykid.searchitem.png?raw=true" width = "270" height = "480"/>   <img src="https://github.com/BreadKid/SearchItem/blob/master/readme_pic/Screenshot_2016-12-14-19-22-45-163_com.breadykid.searchitem.png?raw=true" width = "270" height = "480"/>   <img src="https://github.com/BreadKid/SearchItem/blob/master/readme_pic/Screenshot_2016-12-14-19-22-53-686_com.breadykid.searchitem.png?raw=true" width = "270" height = "480"/>

--
### Bug
欢迎大家去Google Play或issue里留言～～～