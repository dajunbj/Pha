package src.web.user;

import java.util.ArrayList;
import java.util.List;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;

import src.dao.PhaUserDao;
import src.entity.PhaUser;
import src.web.PhaBase;
import src.web.common.PhaUtil;
import src.web.menu.MenuPage;

import com.sun.xml.internal.fastinfoset.stax.events.Util;

public class UserRegisterListPage extends PhaBase {

	@SubapplicationScope
	public List<PhaUser> phaUserItems;

	@SubapplicationScope
	public Integer select_row;

	@SubapplicationScope
	public String up_user_id;

	// ユーザーID
	@SubapplicationScope
	public String user_id;
	// ユーザー名称
	@SubapplicationScope
	public String user_nm;
	// ユーザーID
	@SubapplicationScope
	public String login_id;
	// 電話番号
	@SubapplicationScope
	public String smart_phone;
	// ユーザータイプ
	@SubapplicationScope
	public String user_type;
	// メール
	@SubapplicationScope
	public String mail;
	// アドレス
	@SubapplicationScope
	public String address;
	// 郵便番号
	@SubapplicationScope
	public String postal_code;
	// 実際レート
	@SubapplicationScope
	public String exchange_rate;
	// ユーザーID
	@SubapplicationScope
	public String rate;
	// レベル
	@SubapplicationScope
	public String level;
	// 推薦
	@SubapplicationScope
	public String recommend_id;

	@SubapplicationScope
	public String selectedId;

	// 半角文字
	@RegularExpression(target = "doSelect", messageId = "user.banjiaoWenzi", pattern = "[ -~]+")
	public String sel_user_id;

	public String sel_user_nm;

	public Class<UserRegisterListPage> initialize() {
		return selUserList();
	}

	/*
	 * 情報検索
	 */
	public Class<UserRegisterListPage> doSelect() {
		return selUserList();
	}

	private Class<UserRegisterListPage> selUserList() {
		phaUserItems = new ArrayList<PhaUser>();
		PhaUserDao dao = (PhaUserDao) getContainer().getComponent(
				PhaUserDao.class);
		PhaUser param = new PhaUser();

		// ログイン利用者IDのレベルの取得
		param.user_id = login_user_id;
		PhaUser ret = dao.getPhaUser(param);

		param = new PhaUser();
		param.login_id = PhaUtil.trimString(sel_user_id);
		param.user_nm = PhaUtil.trimString(sel_user_nm);

		if (ret != null) {
			param.level = ret.level;
		}

		param.recommend_id = login_user_id;
		// 一覧情報の取得
		phaUserItems = dao.getPhaUserList(param);

		return null;
	}

	public Class<UserRegisterListPage> prerender() {
		return null;
	}

	/*
	 * 前画面へ戻る
	 */
	public Class<MenuPage> doReturn() {

		return MenuPage.class;
	}

	/*
	 * 利用者新規画面へ遷移
	 */
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id")
	public Class<UserRegisterPage> doRegist() {

		return UserRegisterPage.class;
	}

	/*
	 * 更新を削除する
	 */
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,up_user_id")
	public Class<UserRegisterPage> doUpdate() {

		PhaUser info = phaUserItems.get(select_row);
		up_user_id = info.user_id;
		return UserRegisterPage.class;
	}

	/*
	 * 利用者を削除する
	 */
	public Class<UserRegisterListPage> doDelete() {

		PhaUser info = phaUserItems.get(select_row);

		PhaUserDao dao = (PhaUserDao) getContainer().getComponent(
				PhaUserDao.class);
		PhaUser phaUser = new PhaUser();
		// 削除
		phaUser.user_id = info.user_id;
		
		PhaUser updInfo = new PhaUser();

		// 利用者ID
		updInfo.user_id = up_user_id;
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
		dao.updPhaUser(updInfo);
		// 再検索
		doSelect();

		return null;
	}
}
