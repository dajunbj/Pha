package src.web.goods;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;

import com.sun.xml.internal.fastinfoset.stax.events.Util;

import src.common.CommonUtil;
import src.common.ConstUtil;
import src.dao.GoodProducerDao;
import src.dao.GoodTypeDao;
import src.dao.GoodsDao;
import src.entity.GoodProducer;
import src.entity.Goods;
import src.web.PhaBase;
import src.web.common.ErrorConst;
import src.web.common.PhaUtil;

public class GoodsRegisterPage extends PhaBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GoodsDao dao;
	private GoodTypeDao typeDao;
	private GoodProducerDao goodProducerDao;

	public GoodsRegisterPage() {
		dao = (GoodsDao) getContainer().getComponent(GoodsDao.class);
		// 商品種別リストの初期化
		typeDao = (GoodTypeDao) getContainer().getComponent(GoodTypeDao.class);
		// 商品品名リストの初期化
		goodProducerDao = (GoodProducerDao) getContainer().getComponent(GoodProducerDao.class);

	}

	public List<Map<String, String>> ajaxTest() {
		sel_good_producer_idItems = new ArrayList<Map<String, String>>();
		initSelTypeId();
		return sel_good_producer_idItems;
	}

	/* レベル表示の設定 */
	// 代理フラグ
	public boolean hide_type;
	public boolean hide_rate;
	@SubapplicationScope
	public String up_user_id;

	@SubapplicationScope
	public String goods_id;

	public String goods_nm;

	public String unit;

	public String capacity;

	public String user_id;

	public String regist;

	public String sel_typeId;
	public List<Map<String, String>> sel_typeIdItems;

	public String sel_good_producer_id;
	public List<Map<String, String>> sel_good_producer_idItems;

	/**
	 * 初期化処理1
	 * 
	 */
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id")
	public Class initialize() {
		System.out.println(login_user_id);
		// 商品種別リストボックス
		sel_typeIdItems = typeDao.selectValueLabel();

		if (CommonUtil.isEmpty(goods_id)) {
			// 画面項目をクリアする
		} else {
			// 画面項目を検索して、設定する
			Goods param = new Goods();
			param.goods_id = goods_id;
			Goods ret = dao.getGoods(param);
			// 画面項目の設定
			goods_id = ret.goods_id;
			sel_good_producer_id = ret.type_id;
			goods_nm = ret.goods_nm;
			unit = ret.unit;
			capacity = ret.capacity;
			user_id = ret.user_id;

			// 商品品名の連動
			GoodProducer inputParam = new GoodProducer();
			inputParam.type_id = ret.type_id;
			this.initSelTypeId();

			// リストボックスの初期値の設定
			sel_typeId = ret.type_id;
			sel_good_producer_id = ret.good_producer_id;
		}

		return null;
	}

	private void initSelTypeId() {

		if (CommonUtil.isNotEmpty(sel_typeId)) {
			GoodProducer inputParam = new GoodProducer();
			inputParam.type_id = sel_typeId;
			sel_good_producer_idItems = goodProducerDao.selectValueLabel(inputParam);
		}
	}

	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id")
	public Class doRegist() {
		System.out.println(login_user_id);
		// システム日付の取得
		Timestamp sysDate = PhaUtil.getTimeStamp();

		Goods param = new Goods();

		// パラメータ作成
		if (!Util.isEmptyString(goods_id)) {
			// 更新
			Goods updInfo = new Goods();

			updInfo.goods_id = goods_id;
			updInfo.type_id = sel_typeId;
			updInfo.good_producer_id = sel_good_producer_id;
			updInfo.goods_nm = goods_nm;

			updInfo.unit = unit;
			updInfo.capacity = capacity;
			updInfo.user_id = login_user_id;

			updInfo.registe_date = sysDate;
			updInfo.registe_id = login_user_id;
			updInfo.upd_date = sysDate;
			updInfo.upd_id = login_user_id;
			updInfo.del_flg = ConstUtil.ZERO;

			dao.modifyGoods(updInfo);

			return GoodsListPage.class;
		} else {

			param = new Goods();

			param.goods_nm = goods_nm;
			// 新規
			int recCount = dao.getCounts(param);

			if (recCount > 0) {
				// ログインIDの存在チェック

				addErrorMessage(ErrorConst.ERR_EXISTS_CHECK_001);
				return null;
			}
			int all_count = dao.getCounts(null);
			Goods insertInfo = new Goods();
			// 新規
			insertInfo.type_id = sel_typeId;
			insertInfo.good_producer_id = sel_good_producer_id;
			insertInfo.goods_id = String.valueOf(all_count + 1);
			insertInfo.goods_nm = goods_nm;
			insertInfo.unit = unit;
			insertInfo.capacity = capacity;
			insertInfo.user_id = login_user_id;

			insertInfo.registe_date = sysDate;
			insertInfo.registe_id = login_user_id;
			insertInfo.upd_date = sysDate;
			insertInfo.upd_id = login_user_id;
			insertInfo.del_flg = ConstUtil.ZERO;

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

}
