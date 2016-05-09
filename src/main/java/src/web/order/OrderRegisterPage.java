package src.web.order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;

import com.sun.xml.internal.fastinfoset.stax.events.Util;

import src.common.CommonListUtil;
import src.common.CommonUtil;
import src.dao.GoodProducerDao;
import src.dao.GoodTypeDao;
import src.dao.GoodsDao;
import src.dao.PhaOrderDao;
import src.entity.GoodProducer;
import src.entity.Goods;
import src.entity.PhaOrder;
import src.web.PhaBase;
import src.web.common.PhaUtil;
import src.web.goods.GoodsListPage;

public class OrderRegisterPage extends PhaBase {

	private PhaOrderDao dao;

	public OrderRegisterPage() {
		dao = (PhaOrderDao) getContainer().getComponent(PhaOrderDao.class);

	}

	/* レベル表示の設定 */
	// 代理フラグ
	public boolean hide_type;
	public boolean hide_rate;
	@SubapplicationScope
	public String up_user_id;

	public String order_id;
	public String goods_id;
	public String amount;
	public Timestamp order_date;
	public String user_id;
	public String manager_id;
	public String close_kbn;
	public String state;

	public String regist;

	// 商品種別選択のリストボックス
	@SubapplicationScope
	public String sel_typeId;
	@SubapplicationScope
	public List<Map<String, String>> sel_typeIdItems;

	@SubapplicationScope
	public String sel_good_producer_id;
	@SubapplicationScope
	public List<Map<String, String>> sel_good_producer_idItems;

	@SubapplicationScope
	public String sel_good_id;
	@SubapplicationScope
	public List<Map<String, String>> sel_good_idItems;

	/**
	 * 初期化処理1
	 * 
	 */
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,goodsid")
	public Class initialize() {

		// 商品種別リスト初期化
		sel_typeIdItems = CommonListUtil.initGoodTypeList();

		if (Util.isEmptyString(order_id)) {
			// 画面項目をクリアする
		} else {
			// 画面項目を検索して、設定する
			PhaOrder param = new PhaOrder();
			param.order_id = order_id;
			PhaOrder retInfo = dao.getPhaOrder(param);

			order_id = retInfo.order_id;
			goods_id = retInfo.goods_id;
			amount = retInfo.amount;
			order_date = retInfo.order_date;
			user_id = retInfo.user_id;
			manager_id = retInfo.manager_id;
			close_kbn = retInfo.close_kbn;
			state = retInfo.state;
		}
		return null;
	}

	public Class doRegist() {
		System.out.println(login_user_id);
		// システム日付の取得
		Timestamp sysDate = PhaUtil.getTimeStamp();

		PhaOrder param = new PhaOrder();

		// パラメータ作成
		if (!Util.isEmptyString(order_id)) {
			// 更新
			PhaOrder updInfo = new PhaOrder();

			updInfo.order_id = order_id;
			updInfo.goods_id = goods_id;
			updInfo.amount = amount;
			updInfo.order_date = sysDate;
			updInfo.user_id = user_id;
			updInfo.manager_id = manager_id;
			updInfo.close_kbn = close_kbn;
			updInfo.state = state;

			updInfo.registe_date = sysDate;
			updInfo.registe_id = login_user_id;
			updInfo.upd_date = sysDate;
			updInfo.upd_id = "";
			updInfo.del_flg = "0";

			dao.modifydPhaOrders(updInfo);

			return OrderListPage.class;
		} else {

			int all_count = dao.getCounts();
			PhaOrder insertInfo = new PhaOrder();

			insertInfo.order_id = String.valueOf(all_count + 1);
			insertInfo.goods_id = goods_id;
			insertInfo.amount = amount;
			insertInfo.order_date = sysDate;
			insertInfo.user_id = user_id;
			insertInfo.manager_id = manager_id;
			insertInfo.close_kbn = close_kbn;
			insertInfo.state = state;

			insertInfo.registe_date = sysDate;
			insertInfo.registe_id = login_user_id;
			insertInfo.upd_date = sysDate;
			insertInfo.upd_id = "";
			insertInfo.del_flg = "0";

			dao.insert(insertInfo);

			return GoodsListPage.class;
		}
	}

	public Class doReturn() {
		return GoodsListPage.class;
	}

	public Class prerender() {
		return null;
	}

	/*
	 * 品名リスト作成
	 */
	public List<Map<String, String>> ajaxInitProducerList() {
		return CommonListUtil.initProducerList(sel_typeId);
	}

	/*
	 * 品名リスト作成
	 */
	public List<Map<String, String>> ajaxInitGoodList() {
		return CommonListUtil.initGoodList(sel_typeId, sel_good_producer_id);
	}

}
