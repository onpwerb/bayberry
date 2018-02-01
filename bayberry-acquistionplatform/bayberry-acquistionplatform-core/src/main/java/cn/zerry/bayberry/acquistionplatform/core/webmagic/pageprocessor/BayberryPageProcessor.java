package cn.zerry.bayberry.acquistionplatform.core.webmagic.pageprocessor;

import cn.zerry.bayberry.acquistionplatform.core.bean.po.ExtractionRuleDefine;
import cn.zerry.bayberry.acquistionplatform.core.webmagic.downloader.BayberryDownloader;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.configurable.ConfigurablePageProcessor;
import us.codecraft.webmagic.configurable.ExpressionType;
import us.codecraft.webmagic.configurable.ExtractRule;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author linzengrui
 * @Description 自定义PageProcessor
 * @date 2018年01月31日 17:11
 */
public class BayberryPageProcessor implements PageProcessor{

    private String url;

    public BayberryPageProcessor(){}
    public BayberryPageProcessor(String url){
        this.url = url;
    }

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return null;
    }

    public void extract(Integer type, String expressionValue, String fieldName){
        List<ExtractRule> extractRules = Lists.newArrayList();
        ExtractRule extractRule = new ExtractRule();

        switch (type){
            case ExtractionRuleDefine.EXTRACT_CSS:
                extractRule.setExpressionType(ExpressionType.Css);
                break;
            case ExtractionRuleDefine.EXTRACT_ENDS:
                //todo
                break;
            case ExtractionRuleDefine.EXTRACT_REGULAR:
                extractRule.setExpressionType(ExpressionType.Regex);
                break;
            case ExtractionRuleDefine.EXTRACT_BODY:
                //todo
                break;
            case ExtractionRuleDefine.EXTRACT_XPATH:
                extractRule.setExpressionType(ExpressionType.XPath);
                break;
            case ExtractionRuleDefine.EXTRACT_JSON:
                extractRule.setExpressionType(ExpressionType.JsonPath);
                break;
            default:
                //todo
                extractRule.setExpressionType(ExpressionType.XPath);
                break;
        }
        extractRule.setExpressionValue(expressionValue);
        extractRule.setFieldName(fieldName);

        extractRules.add(extractRule);

        Spider.create(new ConfigurablePageProcessor(Site.me(), extractRules))
                .setDownloader(new BayberryDownloader())
                .get(url);

//        Spider.create(new ConfigurablePageProcessor(Site.me(), extractRules))
//                .addUrl(url)
//                .addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
//                .thread(5)
//                .run();

    }
}
