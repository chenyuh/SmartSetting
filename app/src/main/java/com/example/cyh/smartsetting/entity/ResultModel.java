package com.example.cyh.smartsetting.entity;

import java.util.List;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.entity
 * 文件名：  ResultModel
 * 创建者：  CYH
 * 创建时间：2017/5/18 16:59
 * 描述：    json数据类型
 */

public class ResultModel {

    /**
     * error_code : 0
     * reason : 成功的返回
     * result : {"com":"yt","company":"圆通","list":[{"datetime":"2017-04-19 16:29:06","remark":"【常熟市场部售后组公司】 取件人: 曹联武 已收件","zone":""},{"datetime":"2017-04-20 00:57:06","remark":"【江苏省苏州市常熟市公司】 已收件","zone":""},{"datetime":"2017-04-20 00:59:21","remark":"【江苏省苏州市常熟市公司】 已打包","zone":""},{"datetime":"2017-04-20 01:02:15","remark":"【江苏省苏州市常熟市公司】 已发出 下一站 【无锡转运中心】","zone":""},{"datetime":"2017-04-20 03:34:37","remark":"【无锡转运中心】 已收入","zone":""},{"datetime":"2017-04-20 03:38:09","remark":"【无锡转运中心】 已发出 下一站 【广州转运中心】","zone":""},{"datetime":"2017-04-21 10:08:41","remark":"【广州转运中心】 已收入","zone":""},{"datetime":"2017-04-21 10:46:11","remark":"【广州转运中心】 已发出 下一站 【广东省广州市天河区车陂公司】","zone":""},{"datetime":"2017-04-21 14:46:10","remark":"【广东省广州市天河区车陂公司】 已收入","zone":""},{"datetime":"2017-04-21 15:02:44","remark":"【广东省广州市天河区车陂公司】 派件人: 李振雄 派件中 派件员电话15302491005","zone":""},{"datetime":"2017-04-21 17:16:46","remark":"客户 签收人: 他人代收 已签收 感谢使用圆通速递，期待再次为您服务","zone":""}],"no":"884803129832320753","status":"1"}
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
         * com : yt
         * company : 圆通
         * list : [{"datetime":"2017-04-19 16:29:06","remark":"【常熟市场部售后组公司】 取件人: 曹联武 已收件","zone":""},{"datetime":"2017-04-20 00:57:06","remark":"【江苏省苏州市常熟市公司】 已收件","zone":""},{"datetime":"2017-04-20 00:59:21","remark":"【江苏省苏州市常熟市公司】 已打包","zone":""},{"datetime":"2017-04-20 01:02:15","remark":"【江苏省苏州市常熟市公司】 已发出 下一站 【无锡转运中心】","zone":""},{"datetime":"2017-04-20 03:34:37","remark":"【无锡转运中心】 已收入","zone":""},{"datetime":"2017-04-20 03:38:09","remark":"【无锡转运中心】 已发出 下一站 【广州转运中心】","zone":""},{"datetime":"2017-04-21 10:08:41","remark":"【广州转运中心】 已收入","zone":""},{"datetime":"2017-04-21 10:46:11","remark":"【广州转运中心】 已发出 下一站 【广东省广州市天河区车陂公司】","zone":""},{"datetime":"2017-04-21 14:46:10","remark":"【广东省广州市天河区车陂公司】 已收入","zone":""},{"datetime":"2017-04-21 15:02:44","remark":"【广东省广州市天河区车陂公司】 派件人: 李振雄 派件中 派件员电话15302491005","zone":""},{"datetime":"2017-04-21 17:16:46","remark":"客户 签收人: 他人代收 已签收 感谢使用圆通速递，期待再次为您服务","zone":""}]
         * no : 884803129832320753
         * status : 1
         */

        private String com;
        private String company;
        private String no;
        private String status;
        private List<ListBean> list;

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * datetime : 2017-04-19 16:29:06
             * remark : 【常熟市场部售后组公司】 取件人: 曹联武 已收件
             * zone :
             */

            private String datetime;
            private String remark;
            private String zone;

            public String getDatetime() {
                return datetime;
            }

            public void setDatetime(String datetime) {
                this.datetime = datetime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getZone() {
                return zone;
            }

            public void setZone(String zone) {
                this.zone = zone;
            }
        }
    }
}
