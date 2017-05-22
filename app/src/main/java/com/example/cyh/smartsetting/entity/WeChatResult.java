package com.example.cyh.smartsetting.entity;

import com.example.cyh.smartsetting.utils.ParseJson;

import java.util.List;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.entity
 * 文件名：  WeChatResult
 * 创建者：  CYH
 * 创建时间：2017/5/21 20:41
 * 描述：    微信精选数据源
 */

public class WeChatResult extends ParseJson<WeChatResult> {
    /**
     * reason : 请求成功
     * result : {"list":[{"id":"wechat_20170521027533","title":"《爸爸去哪儿5》新成员曝光，网友称这季很有看点","source":"娱乐姐","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24793579.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521027533"},{"id":"wechat_20170521027664","title":"苗风题材旧体诗，闪耀来袭！","source":"三苗网X智慧苗族","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-12769578.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521027664"},{"id":"wechat_20170521027633","title":"谁听谁哭！！《走出你的世界我更寂寞》歌词太感人了！！心都碎了！！","source":"经典歌曲推荐","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-23014845.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521027633"},{"id":"wechat_20170521027724","title":"优美文字摘抄","source":"人生美文欣赏","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24794445.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521027724"},{"id":"wechat_20170521026455","title":"若你到梅州这座小城来 这些有趣的客家话用语鱼爱滴","source":"0753门户","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-22876664.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026455"},{"id":"wechat_20170521026503","title":"只需238元把世界十大名著搬回家，做一个有生命厚度有内涵的人","source":"太平书院","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-17900299.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026503"},{"id":"wechat_20170521026536","title":"云对雨，雪对风，佛陀对苍生，这是我见过最美的国学读物","source":"美尚","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-12872546.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026536"},{"id":"wechat_20170521026581","title":"鲁迅的180个笔名，你知道多少？","source":"团结报团结网","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24789365.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026581"},{"id":"wechat_20170521026583","title":"\u201c二次元\u201d的世界里，金庸的江湖是否依然让人流连","source":"团结报团结网","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-12924387.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026583"},{"id":"wechat_20170521026686","title":"断代|清粉彩瓷器各时期的特点（多图）","source":"古玩元素网","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24789955.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026686"},{"id":"wechat_20170521026681","title":"明代御窑青花瓷器及漆器贵在哪里！|北京保利2017春拍抢先看","source":"古玩元素网","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24789903.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026681"},{"id":"wechat_20170521026682","title":"台北故宫60块\u201c稀世之珍\u201d玉璧，一次看尽兴！","source":"古玩元素网","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24789869.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026682"},{"id":"wechat_20170521026679","title":"那些博物馆镇馆之宝背后的神秘主人！","source":"古玩元素网","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24789852.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026679"},{"id":"wechat_20170521026677","title":"在古玩行，行家最不愿搭理的8种人！\u2014\u2014其中一种我有鉴定证书，这东西你收吗？","source":"古玩元素网","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24543193.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026677"},{"id":"wechat_20170521026930","title":"谜底揭露 | 10年搜集一万个民间故事，你的童年没有它太可惜","source":"二课堂","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-11527447.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026930"},{"id":"wechat_20170521026965","title":"【艺术】俄罗斯画家奥列格 古廉科夫 的作品","source":"俄罗斯旅游与文化艺术交流中心","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24791290.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026965"},{"id":"wechat_20170521026949","title":"蒙古人征服了半个地球，但蒙古民族究竟是从哪里来的呢？","source":"大嘴侃历史","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24791255.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026949"},{"id":"wechat_20170521026993","title":"你连《圣经》都没读过，还谈什么全球化和国际化？此文果敢转到朋友圈，让人认识神的话语！","source":"中小学生阅读","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24791398.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026993"},{"id":"wechat_20170521026988","title":"最全的四大名著俏皮话，幽默中透出深意！","source":"中小学生阅读","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24791378.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026988"},{"id":"wechat_20170521027106","title":"中国艺术是如何刷爆凡尔赛宫贵族朋友圈的？","source":"靠谱","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24792041.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521027106"}],"totalPage":3645,"ps":20,"pno":1}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

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

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * list : [{"id":"wechat_20170521027533","title":"《爸爸去哪儿5》新成员曝光，网友称这季很有看点","source":"娱乐姐","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24793579.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521027533"},{"id":"wechat_20170521027664","title":"苗风题材旧体诗，闪耀来袭！","source":"三苗网X智慧苗族","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-12769578.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521027664"},{"id":"wechat_20170521027633","title":"谁听谁哭！！《走出你的世界我更寂寞》歌词太感人了！！心都碎了！！","source":"经典歌曲推荐","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-23014845.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521027633"},{"id":"wechat_20170521027724","title":"优美文字摘抄","source":"人生美文欣赏","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24794445.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521027724"},{"id":"wechat_20170521026455","title":"若你到梅州这座小城来 这些有趣的客家话用语鱼爱滴","source":"0753门户","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-22876664.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026455"},{"id":"wechat_20170521026503","title":"只需238元把世界十大名著搬回家，做一个有生命厚度有内涵的人","source":"太平书院","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-17900299.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026503"},{"id":"wechat_20170521026536","title":"云对雨，雪对风，佛陀对苍生，这是我见过最美的国学读物","source":"美尚","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-12872546.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026536"},{"id":"wechat_20170521026581","title":"鲁迅的180个笔名，你知道多少？","source":"团结报团结网","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24789365.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026581"},{"id":"wechat_20170521026583","title":"\u201c二次元\u201d的世界里，金庸的江湖是否依然让人流连","source":"团结报团结网","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-12924387.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026583"},{"id":"wechat_20170521026686","title":"断代|清粉彩瓷器各时期的特点（多图）","source":"古玩元素网","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24789955.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026686"},{"id":"wechat_20170521026681","title":"明代御窑青花瓷器及漆器贵在哪里！|北京保利2017春拍抢先看","source":"古玩元素网","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24789903.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026681"},{"id":"wechat_20170521026682","title":"台北故宫60块\u201c稀世之珍\u201d玉璧，一次看尽兴！","source":"古玩元素网","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24789869.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026682"},{"id":"wechat_20170521026679","title":"那些博物馆镇馆之宝背后的神秘主人！","source":"古玩元素网","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24789852.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026679"},{"id":"wechat_20170521026677","title":"在古玩行，行家最不愿搭理的8种人！\u2014\u2014其中一种我有鉴定证书，这东西你收吗？","source":"古玩元素网","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24543193.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026677"},{"id":"wechat_20170521026930","title":"谜底揭露 | 10年搜集一万个民间故事，你的童年没有它太可惜","source":"二课堂","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-11527447.static/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026930"},{"id":"wechat_20170521026965","title":"【艺术】俄罗斯画家奥列格 古廉科夫 的作品","source":"俄罗斯旅游与文化艺术交流中心","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24791290.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026965"},{"id":"wechat_20170521026949","title":"蒙古人征服了半个地球，但蒙古民族究竟是从哪里来的呢？","source":"大嘴侃历史","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24791255.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026949"},{"id":"wechat_20170521026993","title":"你连《圣经》都没读过，还谈什么全球化和国际化？此文果敢转到朋友圈，让人认识神的话语！","source":"中小学生阅读","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24791398.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026993"},{"id":"wechat_20170521026988","title":"最全的四大名著俏皮话，幽默中透出深意！","source":"中小学生阅读","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24791378.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521026988"},{"id":"wechat_20170521027106","title":"中国艺术是如何刷爆凡尔赛宫贵族朋友圈的？","source":"靠谱","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-24792041.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170521027106"}]
         * totalPage : 3645
         * ps : 20
         * pno : 1
         */

        private int totalPage;
        private int ps;
        private int pno;
        private List<ListBean> list;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPs() {
            return ps;
        }

        public void setPs(int ps) {
            this.ps = ps;
        }

        public int getPno() {
            return pno;
        }

        public void setPno(int pno) {
            this.pno = pno;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : wechat_20170521027533
             * title : 《爸爸去哪儿5》新成员曝光，网友称这季很有看点
             * source : 娱乐姐
             * firstImg : http://zxpic.gtimg.com/infonew/0/wechat_pics_-24793579.jpg/640
             * mark :
             * url : http://v.juhe.cn/weixin/redirect?wid=wechat_20170521027533
             */

            private String id;
            private String title;
            private String source;
            private String firstImg;
            private String url;
            private String mark;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getFirstImg() {
                return firstImg;
            }

            public void setFirstImg(String firstImg) {
                this.firstImg = firstImg;
            }

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
