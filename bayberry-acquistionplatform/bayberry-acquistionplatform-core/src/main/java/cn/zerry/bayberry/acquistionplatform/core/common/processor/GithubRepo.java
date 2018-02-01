package cn.zerry.bayberry.acquistionplatform.core.common.processor;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

/**
 * @author linzengrui
 * @Description 基于注解实现GithubRepoPageProcessor类型功能
 * @date 2018年01月20日 22:08
 */
@TargetUrl("https://github.com/\\w+/\\w+")
@HelpUrl("https://github.com/\\w+")
public class GithubRepo {
    @ExtractBy(value = "//h1[@class='entry-title public']/strong/a/text()", notNull = true)
    private String name;

    @ExtractByUrl("https://github\\.com/(\\w+)/.*")
    private String author;

    @ExtractBy("//div[@id='readme']/tidyText()")
    private String readme;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000), new ConsolePageModelPipeline(), GithubRepo.class)
                .addUrl("https://github.com/code4craft")
                .addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
                .thread(5)
                .run();
    }
}
