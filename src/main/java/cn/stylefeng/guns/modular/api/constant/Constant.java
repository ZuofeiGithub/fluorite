package cn.stylefeng.guns.modular.api.constant;

import java.io.Serializable;

public class Constant implements Serializable {
    private static final long serialVersionUID = 5459424257218722901L;
    public static String appKey = "9e53b2561f314f00ba68b5d58d66e4c0";
    public static String appSecret = "d7f0d6c7ffc567ff42d8ab1dca480b33";


    public static final String TOKENURL = "https://open.ys7.com/api/lapp/token/get"; //根据appKey和secret获取accessToken
    public static final String DEVICEVERSIONINFO = "https://open.ys7.com/api/lapp/device/version/info";//获取设备版本信息
    public static final String DEVICEUPGRADE = "https://open.ys7.com/api/lapp/device/upgrade";//设备升级固件
    public static final String DEVICEADD = "https://open.ys7.com/api/lapp/device/add"; //添加设备到账号下
    public static final String DEVICEDELETE = "https://open.ys7.com/api/lapp/device/delete"; //删除账号下指定设备
    public static final String DEVICENAMEUPDATE = "https://open.ys7.com/api/lapp/device/name/update"; //修改设备名称
    public static final String DEVICECAPTURE = "https://open.ys7.com/api/lapp/device/capture"; //抓拍设备的当前画面
    public static final String CAMERANAMEUPDATE = "https://open.ys7.com/api/lapp/camera/name/update";//修改通道名称
    public static final String DEVICEWIFIQRCODE = "https://open.ys7.com/api/lapp/device/wifi/qrcode"; //生成设备配网二维码
    public static final String DEVICEPASSWORDUPDATE = "https://open.ys7.com/api/lapp/device/password/update"; //修改设备密码
    public static final String DEVICELIST = "https://open.ys7.com/api/lapp/device/list"; //获取设备列表
    public static final String DEVICEINFO = "https://open.ys7.com/api/lapp/device/info"; //获取单个设备信息
    public static final String CAMERALIST = "https://open.ys7.com/api/lapp/camera/list"; //获取摄像头列表
    public static final String DEVICEUUIDPICTURE = "https://open.ys7.com/api/lapp/device/uuid/picture"; //根据UUID查询抓拍图片（设备互联互通使用）
    public static final String DEVICESTATUS = "https://open.ys7.com/api/lapp/device/status/get"; // 获取设备状态信息
    public static final String DEVICECAMERALIST = "https://open.ys7.com/api/lapp/device/camera/list"; //  获取指定设备的通道信息
    public static final String DEVICESUPPORTEZVIZ = "https://open.ys7.com/api/lapp/device/support/ezviz";//查询设备是否支持萤石协议
    public static final String DEVICECAPACITY = "https://open.ys7.com/api/lapp/device/capacity";//根据设备序列号查询设备能力集
    public static final String DEVICEDEFENCESET = "https://open.ys7.com/api/lapp/device/defence/set";//设备布撤防
    public static final String DEVICEENCRYPTOFF = "https://open.ys7.com/api/lapp/device/encrypt/off";//关闭设备视频加密

    /**
     * 云台控制
     */
    public static final String DEVICEPTZSTART = "https://open.ys7.com/api/lapp/device/ptz/start";//开始云台控制
    public static final String DEVICEPIZSTOP = "https://open.ys7.com/api/lapp/device/ptz/stop";//停止云台控制

    /**
     * 直播接口
     */
    public static final String VIDEOLIST = "https://open.ys7.com/api/lapp/live/video/list";// 获取用户下直播视频列表
    public static final String LIVEADDRESSLIMITED = "https://open.ys7.com/api/lapp/live/address/limited";// 获取指定有效期的直播地址

}
