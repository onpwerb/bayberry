package cn.zerry.bayberry.acquistionplatform.core.common.util;

import org.assertj.core.util.Lists;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.configurable.ConfigurablePageProcessor;
import us.codecraft.webmagic.configurable.ExpressionType;
import us.codecraft.webmagic.configurable.ExtractRule;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

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
    public void extractByEnds(Html html, String front, String end){
        Selectable selectable = html.regex(front + "(.*?)" + end);
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
    public String extractByBody(){
        return null;
    }

    /**
     * XPath提取
     */
    public String extractByXPath(){
        List<ExtractRule> extractRules = Lists.newArrayList();
        ExtractRule extractRule = new ExtractRule();
        extractRule.setExpressionType(ExpressionType.XPath);
        extractRules.add(extractRule);

        Spider.create(new ConfigurablePageProcessor(Site.me(), extractRules));


        return null;
    }

    /**
     * JSON提取
     */
    public String extractByJSON(){
        return null;
    }

    public static void main(String[] args) {

    }

}
