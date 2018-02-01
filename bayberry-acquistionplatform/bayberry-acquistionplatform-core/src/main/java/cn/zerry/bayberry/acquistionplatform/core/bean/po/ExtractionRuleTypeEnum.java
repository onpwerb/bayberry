package cn.zerry.bayberry.acquistionplatform.core.bean.po;

/**
 * @author linzengrui
 * @Description TODO
 * @date 2018年01月31日 17:39
 */
public enum ExtractionRuleTypeEnum {

    //CSS截取
    EXTRACT_CSS(0),
    //前后截取
    EXTRACT_ENDS(1),
    //正则截取
    EXTRACT_REGULAR(2),
    //正文截取
    EXTRACT_BODY(3),
    //XPath提取
    EXTRACT_XPATH(4),
    //JSON提取
    EXTRACT_JSON(5);


    private Integer value;

    ExtractionRuleTypeEnum(Integer value){
        this.value = value;
    }

    public Integer value() {
        return value;
    }

    @Override
    public String toString() {
        return "{" +
                "value = " + value +
                "}";
    }
}
