package com.example.lenovo.homework9.Model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VersionBean {

    /**
     * code : 200
     * res : success
     * data : [{"id":1,"url":"http://www.baidu.com","versioncode":1,"package":"com.example","name":"wanandroid"}]
     */

    private int code;
    private String res;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * url : http://www.baidu.com
         * versioncode : 1
         * package : com.example
         * name : wanandroid
         */

        private int id;
        private String url;
        private int versioncode;
        @SerializedName("package")
        private String packageX;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getVersioncode() {
            return versioncode;
        }

        public void setVersioncode(int versioncode) {
            this.versioncode = versioncode;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
