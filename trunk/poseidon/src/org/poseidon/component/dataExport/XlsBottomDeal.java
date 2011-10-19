package org.poseidon.component.dataExport;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public interface XlsBottomDeal {
    public Integer dealBottom(HSSFSheet sheet, Integer rowCnt) throws Exception;
}
