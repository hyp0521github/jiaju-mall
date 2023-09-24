package com.yz.furn.entity;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class Page<T> {
    public static final Integer PAGESIZE = 3;
    private Integer pageno; // 页数
    private Integer pagesize = PAGESIZE; // 条数
    private Integer totalSize; // 总数据条数
    private Integer totalPages; // 总页数
    private List<T> items; // furn集合
    private String url; // 导航url
    public Page() {
    }

    public Page(Integer pageno, Integer pagesize, Integer totalSize, Integer totalPages, List<T> items, String url) {
        this.pageno = pageno;
        this.pagesize = pagesize;
        this.totalSize = totalSize;
        this.totalPages = totalPages;
        this.items = items;
        this.url = url;
    }

    public Integer getPageno() {
        return pageno;
    }

    public void setPageno(Integer pageno) {
        this.pageno = pageno;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "page{" + "pageno=" + pageno + ", pagesize=" + pagesize + ", items=" + items + '}';
    }
}
