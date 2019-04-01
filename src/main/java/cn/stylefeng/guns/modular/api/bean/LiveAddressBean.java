package cn.stylefeng.guns.modular.api.bean;

import java.util.List;

public class LiveAddressBean extends BaseResp{
    private PageBean page;
    private List<DataBean> data;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * total : 3
         * page : 0
         * size : 3
         */

        private int total;
        private int page;
        private int size;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    public static class DataBean {
        private String deviceSerial;
        private int channelNo;
        private String deviceName;
        private String liveAddress;
        private String hdAddress;
        private String rtmp;
        private String rtmpHd;
        private long beginTime;
        private long endTime;
        private int status;
        private int exception;

        public String getDeviceSerial() {
            return deviceSerial;
        }

        public void setDeviceSerial(String deviceSerial) {
            this.deviceSerial = deviceSerial;
        }

        public int getChannelNo() {
            return channelNo;
        }

        public void setChannelNo(int channelNo) {
            this.channelNo = channelNo;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getLiveAddress() {
            return liveAddress;
        }

        public void setLiveAddress(String liveAddress) {
            this.liveAddress = liveAddress;
        }

        public String getHdAddress() {
            return hdAddress;
        }

        public void setHdAddress(String hdAddress) {
            this.hdAddress = hdAddress;
        }

        public String getRtmp() {
            return rtmp;
        }

        public void setRtmp(String rtmp) {
            this.rtmp = rtmp;
        }

        public String getRtmpHd() {
            return rtmpHd;
        }

        public void setRtmpHd(String rtmpHd) {
            this.rtmpHd = rtmpHd;
        }

        public long getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(long beginTime) {
            this.beginTime = beginTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getException() {
            return exception;
        }

        public void setException(int exception) {
            this.exception = exception;
        }
    }
}
