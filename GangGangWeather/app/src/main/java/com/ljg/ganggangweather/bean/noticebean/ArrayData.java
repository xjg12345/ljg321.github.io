package com.ljg.ganggangweather.bean.noticebean;

import java.io.Serializable;

public class ArrayData implements Serializable {
    private String  id;//天气类型Code
    private String  type;//天气类型，如"多云"
    private String  notice;//天气建议，如"阴晴之间，谨防紫外线侵扰"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        return '\n' + "天气类型：" + type + '\n' +
                "温馨提示：" + notice + '\n';
    }
}
