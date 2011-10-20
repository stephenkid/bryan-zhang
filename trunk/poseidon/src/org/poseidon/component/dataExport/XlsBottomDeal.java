package org.poseidon.component.dataExport;

/**
 * @author Bryan Zhang
 * Xls文件底部扩展接口
 */

import org.apache.poi.hssf.usermodel.HSSFSheet;

public interface XlsBottomDeal {
    public Integer dealBottom(HSSFSheet sheet, Integer rowCnt) throws Exception;
}
