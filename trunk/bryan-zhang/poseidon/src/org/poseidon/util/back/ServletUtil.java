package org.poseidon.util.back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.poseidon.util.ObjectUtil;

/**
 * ServletUtil
 * 
 * @author 何晓滨
 * @version 创建时间：Sep 1, 2008 3:00:29 PM
 */
public abstract class ServletUtil {

    /**
     * 将request中的参数整合到有序集合
     * @param request
     * @return 有序集合
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        List<String> names = new ArrayList<String>();
        for (Enumeration<String> enumd = request.getParameterNames(); enumd.hasMoreElements();) {
            names.add(enumd.nextElement());
        }
        Collections.sort(names);
        Map<String, String> params = new LinkedHashMap<String, String>();
        for (String name : names) {
            params.put(name, request.getParameter(name));
        }
        params.put("_Method", request.getMethod());
        params.put("_RemoteAddr", request.getRemoteAddr());
        return params;
    }

    /**
     * 将request中的参数格式化
     * @param request
     * @return 格式化字符串
     */
    public static String getParameterMapDisplayString(HttpServletRequest request) {
        Map<String, String> params = getParameterMap(request);
        return ObjectUtil.getDisplayString(params);
    }

}
