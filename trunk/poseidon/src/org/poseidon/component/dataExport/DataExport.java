package org.poseidon.component.dataExport;

/**
 * @author Bryan Zhang
 * 数据导出接口
 */

import java.util.LinkedHashMap;
import java.util.List;

public interface DataExport {

    /**
     * 根据原始数据，列头转换成需要的导出对象
     * @param dataList
     * @param headMap
     * @return O
     * @throws Exception
     */
    public <T, O> O convertList(List<T> dataList, LinkedHashMap<String, String> headMap);

    /**
     * 根据原始数据，列头，文件输出路径转换
     * @param dataList
     * @param headMap
     * @param path
     * @return boolean
     * @throws Exception
     */
    public <T> boolean convertList2File(List<T> dataList, LinkedHashMap<String, String> headMap, String path);

    /**
     * 根据sql生成大容量的文件,采用fetch的方式。
     * @param sql sql语句
     * @param path 生成文件路径
     * @param headMap 列头
     * @return boolean
     * @throws Exception
     */
    public void generateFileFromSql(CharSequence sql, List<Object> argList, String path);
}
