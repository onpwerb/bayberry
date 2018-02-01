package cn.zerry.bayberry.acquistionplatform.core.webmagic.downloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

/**
 * @author linzengrui
 * @Description 自定义downloader
 * @date 2018年01月31日 17:01
 */
public class BayberryDownloader implements Downloader{

    private String html;
    private String url;
    private final static int HTTP_CODE = 200;

    public BayberryDownloader(){}
    public BayberryDownloader(String html, String url){
        this.html = html;
        this.url = url;
    }

    @Override
    public Page download(Request request, Task task) {
        Page page = new Page();
        page.setRawText(html);
        page.setStatusCode(HTTP_CODE);
        page.setRequest(new Request(url));
        page.setUrl(new PlainText(url));
        return page;
    }

    @Override
    public void setThread(int i) {

    }
}
