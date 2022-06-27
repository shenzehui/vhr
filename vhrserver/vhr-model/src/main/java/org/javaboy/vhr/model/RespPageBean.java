package org.javaboy.vhr.model;

import java.util.List;

/**
 * @Author szh
 * @Date 2022/6/24 12:40
 * @PackageName:org.javaboy.vhr.model
 * @ClassName: RespPageBean
 * @Description: TODO
 * @Version 1.0
 */
public class RespPageBean {
    private Long total;
    private List<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
