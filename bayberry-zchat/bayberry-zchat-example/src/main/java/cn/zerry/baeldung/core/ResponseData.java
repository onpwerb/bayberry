package cn.zerry.baeldung.core;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 返回数据
 *
 * @Author: linzengrui
 * @Date: 2020/5/6 20:40
 */
@Data
@Accessors(chain = true)
public class ResponseData {

    private int intValue;
}
