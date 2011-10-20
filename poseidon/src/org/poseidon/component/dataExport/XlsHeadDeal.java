package org.poseidon.component.dataExport;

/**
 * @author Bryan Zhang
 * Xls头部扩展接口
 */

import org.apache.poi.hssf.usermodel.HSSFSheet;

public interface XlsHeadDeal {
    public Integer dealHead(HSSFSheet sheet, Integer rowCnt) throws Exception;
}
