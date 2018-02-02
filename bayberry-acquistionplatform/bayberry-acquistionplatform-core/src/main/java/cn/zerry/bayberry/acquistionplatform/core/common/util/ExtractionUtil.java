package cn.zerry.bayberry.acquistionplatform.core.common.util;

import us.codecraft.webmagic.selector.Html;

/**
 * @author linzengrui
 * @Description 提取方式api
 * @date 2018年01月31日 10:51
 */
public class ExtractionUtil {

    /**
     * 前后截取
     * @param html
     * @param front
     * @param end
     * @return
     */
    public String extractByEnds(Html html, String front, String end){
        return html.regex(front + "(.*?)" + end).get();
    }

    /**
     * 正则截取
     */
    public String extractByRegular(){
        return null;
    }

    /**
     * 正文截取
     */
    public String extractByBody(Html html, int type){
        String result = null;
        switch (type){
            //文章标题
            case 0 :
                //查找<title>标签
                result = html.$("title").get();
                break;
            //文章内容
            case 1 :
                //todo
                break;
            //发布时间
            case 2 :
                //查找 YYYY-MM-DD HH:mm:dd 格式
                String regExp = "([2][0][0-9]{2}-([0][1-9]|[1][0-2])-([0][1-9]|[1-2][0-9]|[3][0-1])\\s([0-1][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9])";
                result = html.regex(regExp).get();
                break;
            //其他
            default:
                //todo
                break;
        }

        return result;
    }

    /**
     * XPath提取
     */
    public String extractByXPath(Html html, String xpath){



        return null;
    }

    /**
     * JSON提取
     */
    public String extractByJSON(Html html, String expressionValue){
        return null;
    }

    public static void main(String[] args) {

    }

}
