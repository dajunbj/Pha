package src.web.common;

import java.sql.Timestamp;
import java.util.Date;

public class PhaUtil {

	/*
	 * システム日付取得
	 */
	public static Timestamp getTimeStamp() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

	public static Timestamp getMessageContent() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

	/*
	 * 文字列の空白を削除する
	 */
	public static String trimString(String str) {
		if (str != null) {
			return str.trim();
		}
		return null;
	}
}
