package com.p2p.bawei.p2pinvest1801.more.bean;

import java.util.List;

public class AboutFinanceBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : [{"vedioUrl":"/video/bw2.mp4","vedioId":8,"userId":10018,"coverImg":"http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1109/24/c6/9067095_9067095_1316861219359.jpg"},{"vedioUrl":"/video/bw4.mp4","vedioId":9,"userId":10019,"coverImg":"http://pic1.16pic.com/00/40/20/16pic_4020036_b.jpg"},{"vedioUrl":"/video/bw6.mp4","vedioId":10,"userId":10020,"coverImg":"http://www.mayoor.net.cn/images/nfwwoltkovuw2zzomnxw2/tuku/yulantu/131229/328087-13122912494692.jpg"},{"vedioUrl":"/video/bw8.mp4","vedioId":11,"userId":10021,"coverImg":"http://pic19.nipic.com/20120303/2503591_231538026000_2.jpg"}]
     */

    private int code;
    private String msg;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * vedioUrl : /video/bw2.mp4
         * vedioId : 8
         * userId : 10018
         * coverImg : http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1109/24/c6/9067095_9067095_1316861219359.jpg
         */

        private String vedioUrl;
        private int vedioId;
        private int userId;
        private String coverImg;

        public String getVedioUrl() {
            return vedioUrl;
        }

        public void setVedioUrl(String vedioUrl) {
            this.vedioUrl = vedioUrl;
        }

        public int getVedioId() {
            return vedioId;
        }

        public void setVedioId(int vedioId) {
            this.vedioId = vedioId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }
    }
}
