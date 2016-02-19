package src.web.user;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.ByteLength;
import org.seasar.teeda.extension.annotation.validator.Email;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;

import src.dao.PhaUserDao;
import src.entity.PhaUser;
import src.web.PhaBase;
import src.web.common.Const;
import src.web.common.ErrorConst;
import src.web.common.PhaUtil;
import src.web.menu.MenuPage;

public class UserRegisterPage extends PhaBase {

	/* レベル表示の設定 */
	// 代理フラグ
	public boolean hide_type;
	public boolean hide_rate;
	@SubapplicationScope
	public String up_user_id;

	@Required(target = "doRegist", messageId = "user.required")
	public String user_id;

	@Required(target = "doRegist", messageId = "user.required")
	public String login_id;

	@Required(target = "doRegist", messageId = "user.required")
	public String password;

	@Required(target = "doRegist", messageId = "user.required")
	public String user_nm;

	@Required(target = "doRegist", messageId = "user.required")
	@RegularExpression(target = "doRegist", messageId = "user.numbercheck", pattern = "[0-9]+")
	@ByteLength(target = "doRegist", minimum = 11, maximum = 11)
	public String smart_phone;

	@Required(target = "doRegist", messageId = "user.required")
	public String user_type;

	@Required(target = "doRegist", messageId = "user.mail")
	@Email(target = "doRegist", messageId = "user.mail")
	public String mail;

	@Required(target = "doRegist", messageId = "user.required")
	public String address;

	@Required(target = "doRegist", messageId = "user.required")
	public String postal_code;

	@Required(target = "doRegist", messageId = "user.required")
	public String exchange_rate;

	@Required(target = "doRegist", messageId = "user.required")
	public String rate;

	public String level;

	public String recommend_id;

	public String regist;
	private PhaUserDao dao;

	private void initDao() {
		dao = (PhaUserDao) getContainer().getComponent(PhaUserDao.class);
	}

	public Class doRegist() {
		initDao();
		// システム日付の取得
		Timestamp sysDate = PhaUtil.getTimeStamp();

		// パラメータ作成
		PhaUser user = new PhaUser();
		if (up_user_id != null && !Const.EMPTY.equals(up_user_id)) {
			// 更新
			PhaUser updInfo = new PhaUser();

			// 利用者ID
			updInfo.user_id = up_user_id;
			updInfo.password = password;
			// ログインID
			updInfo.login_id = login_id;
			// 利用者姓名
			updInfo.user_nm = user_nm;
			updInfo.smart_phone = smart_phone;
			updInfo.mail = mail;
			updInfo.address = address;
			updInfo.postal_code = postal_code;
			updInfo.level = user_type;
			updInfo.recommend_id = login_user_id;
			updInfo.upd_date = sysDate;
			updInfo.upd_id = login_user_id;
			dao.updPhaUser(updInfo);

			return UserRegisterListPage.class;
		} else {
			// 新規
			user.login_id = login_id;
			PhaUser ret = dao.getPhaUser(user);
			if (ret != null) {
				// ログインIDの存在チェック
				addErrorMessage(ErrorConst.ERR_EXISTS_CHECK_001);
				return null;
			}

			// 利用者件数の取得
			int redConts = dao.getAllPhaUsersCount();

			PhaUser insertInfo = new PhaUser();

			// 利用者ID
			insertInfo.user_id = new BigDecimal(redConts + 1).toString();
			// ログインID
			insertInfo.login_id = login_id;
			// 利用者姓名
			insertInfo.user_nm = user_nm;
			// パスワード
			insertInfo.password = Const.USER_DEFAULT_PASSWORD;
			insertInfo.smart_phone = smart_phone;
			insertInfo.mail = mail;
			insertInfo.address = address;
			insertInfo.postal_code = postal_code;
			insertInfo.exchange_rate = new BigDecimal(exchange_rate);
			insertInfo.rate = new BigDecimal(rate);
			insertInfo.level = user_type;
			insertInfo.recommend_id = login_user_id;

			insertInfo.registe_date = sysDate;
			insertInfo.registe_id = Const.EMPTY;
			insertInfo.upd_date = sysDate;
			insertInfo.upd_id = login_user_id;
			insertInfo.del_flg = Const.ZERO;
			dao.insert(insertInfo);
			return UserRegisterListPage.class;
		}
	}

	public Class doReturn() {
		initDao();

		PhaUser param = new PhaUser();
		param.user_id = login_user_id;
		PhaUser ret = dao.getPhaUser(param);
		if (ret != null) {
			if (Const.USER_TYPE_9.equals(ret.level)
					|| Const.USER_TYPE_1.equals(ret.level)) {
				// 利用者一覧画面へ戻る
				return UserRegisterListPage.class;
			} else {
				// 利用者一覧画面へ戻る
				return MenuPage.class;
			}
		} else {

			// 利用者一覧画面へ戻る
			return UserRegisterListPage.class;
		}
	}

	/**
	 * 初期化処理1
	 * 
	 * */
	public Class initialize() {

		// 変数の初期化
		initVariable();

		// 画面制御の設定
		initScreenControl();

		// ログインIDによって、画面の制御を設定する

		return null;
	}

	/**
	 * 画面制御の設定
	 * 
	 * */
	private void initVariable() {

	}

	/**
	 * 変数の初期化
	 * 
	 * */
	private void initScreenControl() {
		PhaUserDao dao = (PhaUserDao) getContainer().getComponent(
				PhaUserDao.class);
		PhaUser param = new PhaUser();
		param.user_id = login_user_id;
		PhaUser ret = dao.getPhaUser(param);
		String user_level = ret.level;
		if (ret != null) {
			user_level = ret.level.trim();
		}

		if (Const.USER_TYPE_9.equals(user_level)) {
			// 管理者
			hide_type = true;
		} else {
			// 代理
			hide_type = false;
		}
		// 交易レート
		if (Const.USER_TYPE_9.equals(user_level)
				|| Const.USER_TYPE_1.equals(user_level)) {
			// 管理者と代理の場合、交易レートを修正できます。
			hide_rate = true;
		} else {
			// 管理者と代理以外の場合、交易レートを修正でできません。
			hide_rate = false;
		}

		recommend_id = ret.user_id;
		if (up_user_id != null && !Const.EMPTY.equals(up_user_id)) {
			param = new PhaUser();
			param.user_id = up_user_id;
			ret = dao.getPhaUser(param);

			user_id = ret.user_id;
			password = ret.password;
			login_id = ret.login_id;
			user_nm = ret.user_nm;
			smart_phone = ret.smart_phone;
			mail = ret.mail;
			address = ret.address;
			postal_code = ret.postal_code;
			exchange_rate = ret.exchange_rate.toString();
			rate = ret.rate.toString();
			level = ret.level;
			recommend_id = ret.recommend_id;
		} else {
			recommend_id = login_user_id;
		}

	}

	public Class prerender() {
		return null;
	}

}
