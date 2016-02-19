package src.web.error;

import java.util.ResourceBundle;

import src.web.common.Const;

public class MessageUtil {

	public static String getMessageContent(String param) {
		try {
			ResourceBundle bundle = ResourceBundle
					.getBundle("src.web.error.messges");
			return bundle.getString(param);
		} catch (Exception e) {
			return Const.EMPTY;
		}

	}

	// public static void main(String[] args) {
	// System.out.println(getMessageContent("URL"));
	// }
}
