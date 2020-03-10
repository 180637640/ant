package cn.hs.util;

public class PaginationUtil {
    public static final int DEFAULT_PAGE_SIZE = 10;

    public static int getOffset(Integer page, Integer size) {
        if (page == null || page == 0) {
            page = 1;
        }
        if (size == null || size == 0) {
            size = DEFAULT_PAGE_SIZE;
        }
        return (page - 1) * size;
    }

    public static int getPageSize(Integer size) {
        if (size == null || size == 0) {
            size = DEFAULT_PAGE_SIZE;
        }
        return size;
    }

    public static int getPage(Integer page) {
        if (page == null || page == 0) {
            page = 1;
        }
        return page;
    }

    public static int getTotalPage(int total, Integer size) {
        if (size == null || size == 0) {
            size = DEFAULT_PAGE_SIZE;
        }
        return (int) Math.ceil((double) total / (double) size);
    }
}
