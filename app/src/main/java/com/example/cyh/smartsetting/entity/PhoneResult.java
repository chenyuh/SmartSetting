package com.example.cyh.smartsetting.entity;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.entity
 * 文件名：  PhoneResult
 * 创建者：  CYH
 * 创建时间：2017/5/19 14:11
 * 描述：    手机归属地Json数据
 */

public class PhoneResult extends ParseJson<PhoneResult> {
    /**
     * error_code : 0
     * reason : Return Successd!
     * result : {"areacode":"0571","card":"","city":"杭州","company":"移动","province":"浙江","zip":"310000"}
     * resultcode : 200
     */

    private int error_code;
    private String reason;
    private ResultBean result;
    private String resultcode;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public static class ResultBean {
        /**
         * areacode : 0571
         * card :
         * city : 杭州
         * company : 移动
         * province : 浙江
         * zip : 310000
         */

        private String areacode;
        private String card;
        private String city;
        private String company;
        private String province;
        private String zip;

        public String getAreacode() {
            return areacode;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }
    }
}
