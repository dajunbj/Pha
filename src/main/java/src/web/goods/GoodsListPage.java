package src.web.goods;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;

import src.dao.GoodTypeDao;
import src.dao.GoodsDao;
import src.entity.Goods;
import src.web.PhaBase;
import src.web.common.PhaUtil;
import src.web.menu.MenuPage;

public class GoodsListPage extends PhaBase {

	private GoodsDao dao;

	private GoodTypeDao typeDao;

	public GoodsListPage() {
		dao = (GoodsDao) getContainer().getComponent(GoodsDao.class);
		// 商品種別リストの初期化
		typeDao = (GoodTypeDao) getContainer().getComponent(GoodTypeDao.class);
	}

	@SubapplicationScope
	public List<Goods> detailItems;

	@SubapplicationScope
	public Integer select_row;

	@SubapplicationScope
	public String up_user_id;

	@SubapplicationScope
	public String goodsid;
	@SubapplicationScope
	public String goodstype;
	@SubapplicationScope
	public String goodsname;
	@SubapplicationScope
	public String tanka;
	@SubapplicationScope
	public String tani;

	@SubapplicationScope
	public String selectedId;

	// 半角文字
	@RegularExpression(target = "doSelect", messageId = "user.banjiaoWenzi", pattern = "[ -~]+")
	public String sel_goodsType;

	public String sel_goodsName;

	// 商品種別選択のリストボックス
	public String sel_typeId;
	public List<Map<String, String>> sel_typeIdItems;

	public Class<GoodsListPage> initialize() {
		detailItems = new ArrayList<Goods>();

		return selGoodsList();
	}

	/*
	 * 情報検索
	 */
	public Class<GoodsListPage> doSelect() {
		return selGoodsList();
	}

	private Class<GoodsListPage> selGoodsList() {
		detailItems = new ArrayList<Goods>();

		Goods param = new Goods();
		param.goods_nm = sel_goodsName;
		param.type_id = sel_goodsType;
		detailItems = dao.getGoodsList(param);

		// 商品種別リストボックス
		sel_typeIdItems = typeDao.selectValueLabel();
		return null;
	}

	public Class<GoodsListPage> prerender() {
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
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,goodsid")
	public Class<GoodsRegisterPage> doRegist() {
		goodsid = "";
		return GoodsRegisterPage.class;
	}

	/*
	 * 更新を削除する
	 */
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,goodsid")
	public Class<GoodsRegisterPage> doUpdate() {

		Goods info = detailItems.get(select_row);
		goodsid = info.goods_id;
		return GoodsRegisterPage.class;
	}

	/*
	 * 利用者を削除する
	 */
	public Class<GoodsListPage> doDelete() {

		Goods info = detailItems.get(select_row);

		Goods updInfo = new Goods();

		Timestamp sysDate = PhaUtil.getTimeStamp();
		updInfo.goods_id = info.goods_id;

		updInfo.upd_date = sysDate;
		updInfo.upd_id = "001";
		updInfo.del_flg = "1";
		dao.modifyGoods(updInfo);
		return selGoodsList();
	}
}
