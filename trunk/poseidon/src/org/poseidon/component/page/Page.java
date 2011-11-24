package org.poseidon.component.page;

import java.io.Serializable;

public class Page implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    private int pageNum;
    
    private int pageRow = 10;
    
    private int totalRow;
    
    private int toPage;
    
    private int totalPage;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum -1;
    }

    public int getPageRow() {
        return pageRow;
    }

    public void setPageRow(int pageRow) {
        this.pageRow = pageRow;
    }

    public long getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }
    
    public int getToPage() {
        return toPage;
    }

    public void setToPage(int toPage) {
        this.toPage = toPage;
    }

    public int getTotalPage() {
        return (totalRow + pageRow - 1) / pageRow;
    }

    public boolean hasNextPage() {
        if(pageNum >= getTotalPage() - 1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean hasPrePage() {
        if(pageNum <= 0) {
            return false;
        }else {
            return true;
        }
    }
    
    public int getStartIndex() {
        return this.getPageNum()*this.getPageRow();
    }
}
