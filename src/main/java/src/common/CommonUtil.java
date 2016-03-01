package src.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtil {

	public static Map<String, String> addInitOptions() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(ConstUtil.ZERO, ConstUtil.EMPTY);
		return map;
	}

	public static boolean isEmpty(Object str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(Object str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		return true;
	}
}
