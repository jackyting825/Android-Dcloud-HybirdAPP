## Android_Hybird_Integrate

> Android端采用dcloud通用集成原生和H5+实例

### 集成步骤

1.将dcloud下载到的SDK下lib目录下lib.5plus.base-release.aar文件复制到项目app/libs/目录下.老版本的SDK不存在这个文件,需要拷贝对应的jar包

2.配置gradle文件.如果拷贝的是jar包则跳过此步骤.在dependencies同级别的地方增加

``` gradle
repositories {
  flatDir {
    dirs 'libs' //自己项目的libs对应目录
  }
} ```

3.在dependencies下面增加依赖配置

```compile(name: 'lib.5plus.base-release', ext: 'aar')```

4.在AndroidManifest.xml中增加对应的权限配置信息

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.GET_TASKS" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
```

5.在src/main/目录下新建assets目录,在该目录下新建apps/模块名/www结构的目录,然后将manifest.json文件复制到对应模块的www文件夹下,并把通过H5+编译后的html,js,image等文件拷贝到对应模块的www目录下.然后修改json文件中的id,name,launch_path节点的值为对应的模块名(appid),和对应执行的html文件路径.

    注:之前旧版本是需要对不同模块进行建立不同名称的目录,然后修改对应json文件配置.但是经测试,在当前版本(项目中使用的sdk版本以上),其实不需要建立多个模块的目录的,只需要建立一个目录即可,将打包好的html5+文件放进去,然后在不同的webviewActivity中修改加载各自的页面路径即可

6.将SDK目录下面的data(含有dcloud_control.xml,dcloud_error.xml,dcloud_properties.xml)文件夹拷贝到assets目录下.修改对应的dcloud_control.xml中的appid值(可改为项目名).dcloud_properties.xml文件定义了一些dcloud封装好的插件类库

7.把SDK目录下的io.dcloud.RInformation.java按照io.dcloud的包名(包名必须为io.dcloud)放到项目下,编写对应的webview的activity类.详情见项目的plus_activity包下(主要修改对应模块的appid(模块名)和加载页面路径).然后在对应的原生activity中启动该activity即可

8.编写dcloud插件类,实现原生和H5+模块的数据传递交互.详情见plus_plugins包下(插件类编写好后,可以通过在dcloud_properties.xml文件中feature节点注册,也可以在对应的webview的activity类中代码注册.示例为代码注册,推荐为代码注册的方式)

9.启动H5+模块防止白屏加入过度启动图.在webview的activity类中监听器中实现,对应的图片放到drawable目录下即可,但图片名称必须为splash.

10.项目中第0个模块对应移动端H5通用脚手架的index模块,第1个模块对应移动端H5通用脚手架的vuex模块
