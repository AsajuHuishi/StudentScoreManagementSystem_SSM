package indi.huishi.pojo;

import java.util.List;

/**
 * 分页模型对象
 * @param <T>泛型是具体javaBean类
 */
public class Page<T> {

    //当前页显示数量
    public static Integer PAGE_SIZE=10;
    // 总页数
    private Integer pageTotal;
    // 总记录数
    private Integer pageTotalCount;
    // 当前页
    private Integer pageNo;
    // 当前页数据
    private List<T> items;

    // 分页条请求地址
    private String url;

    public Integer getPageTotal() {
        return pageTotal;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        // 数据边界有效检查
        // 如果页码小于1 就显示第一页
        if (pageNo < 1){
            pageNo = 1;
        }else if (pageNo > pageTotal){
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }
    public void setPageSize(Integer pageSize) {
        PAGE_SIZE = pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageNo=" + pageNo +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
