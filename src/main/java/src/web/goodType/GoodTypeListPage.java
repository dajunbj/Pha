package src.web.goodType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;

import src.dao.GoodTypeDao;
import src.entity.GoodType;
import src.web.PhaBase;
import src.web.common.PhaUtil;
import src.web.goods.GoodsListPage;
import src.web.menu.MenuPage;

public class GoodTypeListPage extends PhaBase {

	private GoodTypeDao dao;

	public GoodTypeListPage() {
		dao = (GoodTypeDao) getContainer().getComponent(GoodTypeDao.class);

	}

	@SubapplicationScope
	public List<GoodType> detailItems;

	@SubapplicationScope
	public Integer select_row;

	@SubapplicationScope
	public String up_user_id;

	@SubapplicationScope
	public String type_id;

	@SubapplicationScope
	public String tp_nm;

	@SubapplicationScope
	public String selectedId;

	// 半角文字
	@RegularExpression(target = "doSelect", messageId = "user.banjiaoWenzi", pattern = "[ -~]+")
	public String sel_tpNm;

	public Class<GoodTypeListPage> initialize() {
		detailItems = new ArrayList<GoodType>();

		return selGoodTypeList();
	}

	/*
	 * 情報検索
	 */
	public Class<GoodTypeListPage> doSelect() {
		return selGoodTypeList();
	}

	private Class<GoodTypeListPage> selGoodTypeList() {
		detailItems = new ArrayList<GoodType>();

		GoodType param = new GoodType();
		param.tp_nm = sel_tpNm;
		detailItems = dao.getGoodTypeList(param);

		return null;
	}

	public Class<GoodTypeListPage> prerender() {
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
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,type_id")
	public Class<GoodTypeRegisterPage> doRegist() {
		type_id = "";
		return GoodTypeRegisterPage.class;
	}

	/*
	 * 更新を削除する
	 */
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,type_id")
	public Class<GoodTypeRegisterPage> doUpdate() {

		GoodType info = detailItems.get(select_row);
		type_id = info.type_id;
		return GoodTypeRegisterPage.class;
	}

	/*
	 * 利用者を削除する
	 */
	public Class<GoodTypeListPage> doDelete() {

		GoodType info = detailItems.get(select_row);

		GoodType updInfo = new GoodType();

		Timestamp sysDate = PhaUtil.getTimeStamp();
		updInfo.type_id = info.type_id;

		updInfo.upd_date = sysDate;
		updInfo.upd_id = "001";
		updInfo.del_flg = "1";
		dao.modifydGoodType(updInfo);
		return selGoodTypeList();
	}
}
