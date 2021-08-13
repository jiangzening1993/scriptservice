package com.jiang.scriptservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiang
 */
@Getter
@Setter
public class PageDto<T> {
    private List<T> data;
    private boolean showPre;
    private boolean showNext;
    private boolean showFirst;
    private boolean showLast;

    private int page;
    private List<Integer> pages = new ArrayList<>();
    private int totalPage;

    public void setPagination(int totalCount, int page, int size) {
        this.page = page;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //page<1就显示1，page>最大页数就显示最大页数
        if (page<1){
            this.page=1;
        }
        if (page>totalPage){
            this.page=totalPage;
        }
        //将需要展示的页码插入到pages中
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        //是否展示上一页
        showPre = page != 1;
        //是否展示后一页
        showNext = page != totalPage;
        //是否展示第一页
        showFirst = !pages.contains(1);
        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showLast = false;
        } else {
            showLast = true;
        }
    }
}
