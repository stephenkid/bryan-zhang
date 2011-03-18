package org.poseidon.util;

/**
 * @Project Steel
 * @Description 匹配助手类
 * @Company 99bill
 * @Create 2010-3-28
 * @author alex.yang
 */
class MatchHelper {
	/**
	 * 匹配单个元素
	 * 
	 * @param obj
	 *            待匹配值
	 * @param matchers
	 *            匹配器数组
	 * @param isMatchAll
	 *            是否满足全部条件才算匹配. 如果为false, 则只需满足任意条件就认为是匹配
	 * @return 是否匹配
	 */
	public static <T> boolean match(T obj, Matcher<T>[] matchers, boolean isMatchAll) {
		if (ArrayUtil.isEmpty(matchers)) {
			return false;
		}
		
		boolean isMatched = false;
		for (Matcher<T> matcher : matchers) {
			if (matcher == null) {
				continue;
			}

			isMatched = matcher.matches(obj);
			if (isMatched) {
				// 如果本次匹配且只需单个匹配则直接返回成功
				if (!isMatchAll) {
					return true;
				}
			} else if (isMatchAll) {
				// 如果本次不匹配且需全部匹配则放弃当前元素
				return false;
			}
		}
		
		return isMatched;
	}
}
