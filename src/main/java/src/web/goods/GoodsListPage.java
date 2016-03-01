package src.web.goods;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;

import src.common.CommonUtil;
import src.common.ConstUtil;
import src.dao.GoodProducerDao;
import src.dao.GoodTypeDao;
import src.dao.GoodsDao;
import src.entity.GoodProducer;
import src.entity.Goods;
import src.web.PhaBase;
import src.web.common.PhaUtil;
import src.web.menu.MenuPage;

public class GoodsListPage extends PhaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GoodsDao dao;

	private GoodTypeDao typeDao;

	private GoodProducerDao goodProducerDao;

	public GoodsListPage() {
		dao = (GoodsDao) getContainer().getComponent(GoodsDao.class);
		// 商品種別リストの初期化
		typeDao = (GoodTypeDao) getContainer().getComponent(GoodTypeDao.class);
		// 商品品名リストの初期化
		goodProducerDao = (GoodProducerDao) getContainer().getComponent(GoodProducerDao.class);
	}

	public List<Map<String, String>> ajaxTest() {
		sel_good_producer_idItems = new ArrayList<Map<String, String>>();
		common_sel_init();
		return sel_good_producer_idItems;
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

	@SubapplicationScope
	public String sel_good_producer_id;
	@SubapplicationScope
	public List<Map<String, String>> sel_good_producer_idItems;

	// 商品種別選択のリストボックス
	@SubapplicationScope
	public String sel_typeId;
	@SubapplicationScope
	public List<Map<String, String>> sel_typeIdItems;

	public Class<?> initialize() {

		// 商品種別リストボックス
		sel_typeIdItems = typeDao.selectValueLabel();

		// 明細
		return selGoodsList();
	}

	/*
	 * 情報検索
	 */
	public Class<?> doSelect() {
		return selGoodsList();
	}

	public String selectedTypeId;

	private Class<?> selGoodsList() {

		detailItems = new ArrayList<Goods>();

		Goods param = new Goods();
		// 種別ID
		param.type_id = sel_typeId;
		// 品名ID
		param.good_producer_id = sel_good_producer_id;
		// 明細検索
		detailItems = dao.getGoodsList(param);

		sel_good_producer_idItems = new ArrayList<Map<String, String>>();

		common_sel_init();
		return null;
	}

	private void common_sel_init() {

		if (CommonUtil.isNotEmpty(sel_typeId)) {
			GoodProducer inputParam = new GoodProducer();
			inputParam.type_id = sel_typeId;
			sel_good_producer_idItems = goodProducerDao.selectValueLabel(inputParam);
		}
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
	public Class<?> doDelete() {

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
