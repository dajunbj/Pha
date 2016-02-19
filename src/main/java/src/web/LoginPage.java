package src.web;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.teeda.extension.annotation.scope.RedirectScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;
import org.seasar.teeda.extension.annotation.validator.ByteLength;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;

import src.dao.PhaUserDao;
import src.entity.PhaUser;
import src.web.menu.MenuPage;

public class LoginPage extends PhaBase {

	@RedirectScope
	@Required(target = "doLogin", messageId = "user.required")
	@RegularExpression(target = "doLogin", messageId = "check_numberstr", pattern = "[a-zA-Z0-9]+")
	@ByteLength(minimum = 3, maximum = 8, minimumMessageId = "check_minlength", maximumMessageId = "check_maxlength")
	public String login_id;

	@Required(target = "doLogin", messageId = "user.required")
	@RegularExpression(target = "doLogin", messageId = "check_numberstr", pattern = "[a-zA-Z0-9]+")
	@ByteLength(minimum = 3, maximum = 32, minimumMessageId = "check_minlength", maximumMessageId = "check_maxlength")
	public String password;

	public String allMessages;

	public JdbcManager jdbcManager;

	PhaUserDao phaUserDao;

	/*
	 * 利用者情報検索
	 */
	public PhaUserDao getPhaUserDao() {
		return (PhaUserDao) getContainer().getComponent(PhaUserDao.class);
	}

	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id")
	public Class<?> doLogin() {
		try {
			PhaUser userinfo = new PhaUser();
			userinfo.login_id = login_id;
			PhaUser info = getPhaUserDao().getPhaUser(userinfo);
			if (info == null) {
				this.addErrorMessage("user_err001");
			}

			userinfo.password = password;
			info = getPhaUserDao().getPhaUser(userinfo);
			if (info == null) {
				this.addErrorMessage("user_err002");
			}
			// 全局利用者ID保存
			this.login_user_id = info.user_id;

		} catch (Exception e) {
			return null;
		}

		return MenuPage.class;
	}

	public Class<MenuPage> doTest() {

		return MenuPage.class;
	}

	public Class<?> initialize() {
		super.clearError();
		return null;
	}

	public Class<?> prerender() {
		return null;
	}

}
