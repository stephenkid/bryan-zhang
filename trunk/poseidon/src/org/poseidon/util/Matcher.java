package org.poseidon.util;

/**
 * @Project Steel
 * @Description 匹配器接口
 * @Company 99bill
 * @Create 2010-3-27
 * @author alex.yang
 */
public interface Matcher<T> {
	/**
	 * 判断输入条件是否匹配
	 * 
	 * @param t
	 *            输入条件
	 * @return 是否满足
	 */
	public boolean matches(T t);
}
