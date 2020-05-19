package cn.zerry.baeldung.core;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 请求数据
 *
 * @Author: linzengrui
 * @Date: 2020/5/6 20:22
 */
@Data
@Accessors(chain = true)
public class RequestData {

    private int intValue;

    private String stringValue;
}
