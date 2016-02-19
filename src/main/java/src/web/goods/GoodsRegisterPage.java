package src.web.goods;

import java.sql.Timestamp;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;
import org.seasar.teeda.extension.annotation.validator.Required;

import src.dao.GoodsDao;
import src.entity.Goods;
import src.web.PhaBase;
import src.web.common.ErrorConst;
import src.web.common.PhaUtil;

import com.sun.xml.internal.fastinfoset.stax.events.Util;

public class GoodsRegisterPage extends PhaBase {

	private GoodsDao dao;

	public GoodsRegisterPage() {
		dao = (GoodsDao) getContainer().getComponent(GoodsDao.class);
	}

	/* レベル表示の設定 */
	// 代理フラグ
	public boolean hide_type;
	public boolean hide_rate;
	@SubapplicationScope
	public String up_user_id;

	@SubapplicationScope
	public String goods_id;

	@Required(target = "doRegist", messageId = "user.required")
	public String type_id;

	@Required(target = "doRegist", messageId = "user.required")
	public String good_producer_id;
	
	@Required(target = "doRegist", messageId = "user.required")
	public String goods_nm;

	public String unit;

	public String capacity;

	public String user_id;

	public String regist;

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
			updInfo.type_id = type_id;
			updInfo.good_producer_id = good_producer_id;
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
			insertInfo.type_id = type_id;
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
	 * */
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,goodsid")
	public Class initialize() {
		if (Util.isEmptyString(goods_id)) {
			// 画面項目をクリアする
		} else {
			// 画面項目を検索して、設定する
			Goods param = new Goods();
			param.goods_id = goods_id;
			Goods retInfo = dao.getGoods(param);

			goods_id = retInfo.goods_id;
			type_id = retInfo.type_id;
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
