package src.web.goods;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;
import org.seasar.teeda.extension.annotation.validator.Required;

import com.sun.xml.internal.fastinfoset.stax.events.Util;

import src.dao.GoodProducerDao;
import src.dao.GoodTypeDao;
import src.dao.GoodsDao;
import src.entity.GoodProducer;
import src.entity.Goods;
import src.web.PhaBase;
import src.web.common.ErrorConst;
import src.web.common.PhaUtil;

public class GoodsRegisterPage extends PhaBase {

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

			updInfo.unit = "";
			updInfo.capacity = "";
			updInfo.user_id = "";

			updInfo.registe_date = sysDate;
			updInfo.registe_id = login_user_id;
			updInfo.upd_date = sysDate;
			updInfo.upd_id = "";
			updInfo.del_flg = "0";

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
			insertInfo.goods_id = String.valueOf(all_count + 1);
			insertInfo.type_id = sel_typeId;
			insertInfo.goods_nm = goods_nm;
			insertInfo.unit = "";
			insertInfo.capacity = "";
			insertInfo.user_id = "";

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

	/**
	 * 初期化処理1
	 * 
	 */
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,goodsid")
	public Class initialize() {

		// 商品種別リストボックス
		sel_typeIdItems = typeDao.selectValueLabel();

		if (Util.isEmptyString(goods_id)) {
			// 画面項目をクリアする
		} else {
			// 画面項目を検索して、設定する
			Goods param = new Goods();
			param.goods_id = goods_id;
			Goods retInfo = dao.getGoods(param);
			GoodProducer inputParam = new GoodProducer();

			// 商品品名の連動
			inputParam.type_id = retInfo.type_id;
			sel_good_producer_idItems = goodProducerDao.selectValueLabel(inputParam);

			goods_id = retInfo.goods_id;
			sel_good_producer_id = retInfo.type_id;
			goods_nm = retInfo.goods_nm;
			unit = retInfo.unit;
			capacity = retInfo.capacity;
			user_id = retInfo.user_id;

		}

		return null;
	}

	public Class prerender() {
		return null;
	}

}
