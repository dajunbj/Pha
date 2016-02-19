package src.web.goodType;

import java.sql.Timestamp;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;
import org.seasar.teeda.extension.annotation.validator.Required;
import org.seasar.teeda.extension.taglib.TPopupCalendarTag;

import src.dao.GoodTypeDao;
import src.entity.GoodType;
import src.web.PhaBase;
import src.web.common.ErrorConst;
import src.web.common.PhaUtil;
import src.web.goods.GoodsListPage;

import com.sun.xml.internal.fastinfoset.stax.events.Util;

public class GoodTypeRegisterPage extends PhaBase {

	private GoodTypeDao dao;

	public GoodTypeRegisterPage() {
		dao = (GoodTypeDao) getContainer().getComponent(GoodTypeDao.class);
	}

	/* レベル表示の設定 */
	// 代理フラグ
	public boolean hide_type;
	public boolean hide_rate;
	@SubapplicationScope
	public String up_user_id;

	@SubapplicationScope
	public String type_id;

	@Required(target = "doRegist", messageId = "user.required")
	public String tp_nm;

	public Class doRegist() {
		System.out.println(login_user_id);
		// システム日付の取得
		Timestamp sysDate = PhaUtil.getTimeStamp();

		GoodType param = new GoodType();

		// パラメータ作成
		if (!Util.isEmptyString(type_id)) {
			// 更新
			GoodType updInfo = new GoodType();

			updInfo.type_id = type_id;
			updInfo.tp_nm = tp_nm;

			updInfo.registe_date = sysDate;
			updInfo.registe_id = login_user_id;
			updInfo.upd_date = sysDate;
			updInfo.upd_id = "";
			updInfo.del_flg = "0";

			dao.modifydGoodType(updInfo);

			return GoodTypeListPage.class;
		} else {

			param = new GoodType();

			param.tp_nm = tp_nm;
			// 新規
			int recCount = dao.getCounts(param);

			if (recCount > 0) {
				// ログインIDの存在チェック

				addErrorMessage(ErrorConst.ERR_EXISTS_CHECK_001);
				return null;
			}

			int all_count = dao.getCounts(null);

			GoodType insertInfo = new GoodType();
			// 新規
			insertInfo.type_id = String.valueOf(all_count + 1);
			insertInfo.tp_nm = tp_nm;

			insertInfo.registe_date = sysDate;
			insertInfo.registe_id = login_user_id;
			insertInfo.upd_date = sysDate;
			insertInfo.upd_id = "";
			insertInfo.del_flg = "0";

			dao.insert(insertInfo);

			return GoodTypeListPage.class;
		}
	}

	public Class doReturn() {
		return GoodTypeListPage.class;
	}

	/**
	 * 初期化処理1
	 * 
	 * */
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,type_id")
	public Class initialize() {
		if (Util.isEmptyString(type_id)) {
			// 画面項目をクリアする
		} else {
			// 画面項目を検索して、設定する
			GoodType param = new GoodType();
			param.type_id = type_id;
			GoodType retInfo = dao.getGoodType(param);

			type_id = retInfo.type_id;
			tp_nm = retInfo.tp_nm;

		}

		return null;
	}

	public Class prerender() {
		return null;
	}

}
