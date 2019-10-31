package cn.zerry.po;

/**
 * @author linzengrui
 * @Description TODO
 * @date 2018年06月05日 9:58
 */
public class Greeting {
    private final long id;
    private final String buffer;

    public Greeting(long id, String buffer){
        this.id = id;
        this.buffer = buffer;
    }

    public long getId() {
        return id;
    }

    public String getBuffer() {
        return buffer;
    }
}
