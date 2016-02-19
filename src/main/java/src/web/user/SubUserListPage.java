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
import src.web.order.OrderListPage;

import com.sun.xml.internal.fastinfoset.stax.events.Util;

public class SubUserListPage extends PhaBase {

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

	public Class<SubUserListPage> initialize() {
		return selUserList();
	}

	/*
	 * 情報検索
	 */
	public Class<SubUserListPage> doSelect() {
		return selUserList();
	}

	private Class<SubUserListPage> selUserList() {
		phaUserItems = new ArrayList<PhaUser>();
		PhaUserDao dao = (PhaUserDao) getContainer().getComponent(
				PhaUserDao.class);
		PhaUser param = new PhaUser();

		param = new PhaUser();
		param.user_nm = PhaUtil.trimString(sel_user_nm);

		param.recommend_id = login_user_id;
		// 一覧情報の取得
		phaUserItems = dao.getPhaUserList(param);

		return null;
	}

	public Class<SubUserListPage> prerender() {
		return null;
	}

	/*
	 * 明細行を選択
	 */
	@TakeOver(type = TakeOverType.INCLUDE, properties = "sel_user_nm")
	public Class<OrderListPage> jumpOk() {

		PhaUser info = phaUserItems.get(select_row);
		sel_user_nm = info.user_nm;
		return OrderListPage.class;
	}

}
