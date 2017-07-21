package cn.edu.cqupt.nmid.igds.constant;

/**
 * Created by Kevin on 2017/3/28.
 */

/**
 * 存放Page相关常量，主要是每一类数据页的页数
 */
public enum PageConstant {
    DRUG(15),
    LINK(20);
    int pageNumber;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    private PageConstant (int pageNumber){
        this.pageNumber = pageNumber;
    }
}
