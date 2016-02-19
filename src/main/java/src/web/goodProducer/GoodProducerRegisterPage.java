package src.web.goodProducer;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;

import src.dao.GoodProducerDao;
import src.dao.GoodTypeDao;
import src.entity.GoodProducer;
import src.web.PhaBase;
import src.web.common.ErrorConst;
import src.web.common.PhaUtil;

import com.sun.xml.internal.fastinfoset.stax.events.Util;

/*商品品名登録画面*/
public class GoodProducerRegisterPage extends PhaBase {

	private GoodProducerDao dao;

	private GoodTypeDao typeDao;

	public GoodProducerRegisterPage() {
		dao = (GoodProducerDao) getContainer().getComponent(
				GoodProducerDao.class);
		// 商品種別リストの初期化
		typeDao = (GoodTypeDao) getContainer().getComponent(GoodTypeDao.class);
	}

	@SubapplicationScope
	public String up_user_id;

	public String good_producer_id;
	public String type_id;
	public String good_producer_nm;
	public String founder;
	public String address;
	public String phone;

	// 商品種別選択のリストボックス
	public String sel_typeId;
	public List<Map<String, String>> sel_typeIdItems;

	public Class doRegist() {
		System.out.println(login_user_id);

		// システム日付の取得
		Timestamp sysDate = PhaUtil.getTimeStamp();

		GoodProducer param = new GoodProducer();

		// パラメータ作成
		if (!Util.isEmptyString(good_producer_id)) {
			// 更新
			GoodProducer updInfo = new GoodProducer();

			updInfo.good_producer_id = good_producer_id;
			updInfo.type_id = sel_typeId;
			updInfo.good_producer_nm = good_producer_nm;
			updInfo.founder = founder;
			updInfo.address = address;

			updInfo.registe_date = sysDate;
			updInfo.registe_id = login_user_id;
			updInfo.upd_date = sysDate;
			updInfo.upd_id = "";
			updInfo.del_flg = "0";

			dao.modifydGoodProducer(updInfo);

			return GoodProducerListPage.class;
		} else {

			param = new GoodProducer();

			param.good_producer_nm = good_producer_nm;
			// 新規
			int recCount = dao.getCounts(param);

			if (recCount > 0) {
				// ログインIDの存在チェック

				addErrorMessage(ErrorConst.ERR_EXISTS_CHECK_001);
				return null;
			}
			int all_count = dao.getCounts(null);
			GoodProducer insertInfo = new GoodProducer();
			// 新規
			insertInfo.good_producer_id = String.valueOf(all_count + 1);
			insertInfo.type_id = sel_typeId;
			insertInfo.good_producer_nm = good_producer_nm;
			insertInfo.founder = founder;
			insertInfo.address = address;
			insertInfo.phone = phone;

			insertInfo.registe_date = sysDate;
			insertInfo.registe_id = login_user_id;
			insertInfo.upd_date = sysDate;
			insertInfo.upd_id = "";
			insertInfo.del_flg = "0";

			dao.insert(insertInfo);

			return GoodProducerListPage.class;
		}
	}

	public Class doReturn() {
		return GoodProducerListPage.class;
	}

	/**
	 * 初期化処理1
	 * 
	 * */
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,good_producer_id")
	public Class initialize() {

		// 商品種別リストボックス
		sel_typeIdItems = typeDao.selectValueLabel();

		if (Util.isEmptyString(good_producer_id)) {
			// 画面項目をクリアする
		} else {
			// 画面項目を検索して、設定する
			GoodProducer param = new GoodProducer();
			param.good_producer_id = good_producer_id;
			GoodProducer retInfo = dao.getGoodProducer(param);

			good_producer_id = retInfo.good_producer_id;
			type_id = retInfo.type_id;
			good_producer_nm = retInfo.good_producer_nm;
			founder = retInfo.founder;
			address = retInfo.address;
			phone = retInfo.phone;

		}

		return null;
	}

	public Class prerender() {
		return null;
	}

}
