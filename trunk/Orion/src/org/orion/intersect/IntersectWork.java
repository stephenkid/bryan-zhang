package org.orion.intersect;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.orion.util.XlsUtils;

public class IntersectWork {
	
	private String fileUrl;
	
	private Integer sheetIdx;
	
	private Integer writeSheetIdx;
	
	private Integer colA;
	
	private Integer colB;
	
	public void findAndRecordData() throws Exception{
		Object[][] data = XlsUtils.readSheet(fileUrl, sheetIdx);
		
		List list1 = XlsUtils.returnColList(data, 0);
		List list2 = XlsUtils.returnColList(data, 1);
		
		List interSectList = (List)CollectionUtils.intersection(list1, list2);
		System.out.println(interSectList);
		XlsUtils.writeCol(fileUrl, 1, 0, interSectList);
	}
	
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getColA() {
		return colA;
	}

	public void setColA(Integer colA) {
		this.colA = colA;
	}

	public Integer getColB() {
		return colB;
	}

	public void setColB(Integer colB) {
		this.colB = colB;
	}

	public Integer getSheetIdx() {
		return sheetIdx;
	}

	public void setSheetIdx(Integer sheetIdx) {
		this.sheetIdx = sheetIdx;
	}

	public Integer getWriteSheetIdx() {
		return writeSheetIdx;
	}

	public void setWriteSheetIdx(Integer writeSheetIdx) {
		this.writeSheetIdx = writeSheetIdx;
	}
}
