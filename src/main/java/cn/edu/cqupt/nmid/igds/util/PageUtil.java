package cn.edu.cqupt.nmid.igds.util;

import cn.edu.cqupt.nmid.igds.constant.PageConstant;
import cn.edu.cqupt.nmid.igds.model.page.Page;

/**
 * Created by Administrator on 2017/7/8.
 */
public class PageUtil {
    public static Page getPageOfObject(int currentPage, int totalNumber,int limitOfOnepage){
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        page.setPageNumber(limitOfOnepage);

        page.count();
        return page;
    }
}
