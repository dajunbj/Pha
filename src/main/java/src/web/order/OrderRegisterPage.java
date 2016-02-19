package src.web.order;

import java.sql.Timestamp;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;
import org.seasar.teeda.extension.annotation.validator.Required;

import src.dao.GoodsManageDao;
import src.entity.GoodsManage;
import src.web.PhaBase;
import src.web.common.ErrorConst;
import src.web.common.PhaUtil;
import src.web.goods.GoodsListPage;

import com.sun.xml.internal.fastinfoset.stax.events.Util;

public class OrderRegisterPage extends PhaBase {

	private GoodsManageDao dao;

	public OrderRegisterPage() {
		dao = (GoodsManageDao) getContainer()
				.getComponent(GoodsManageDao.class);
	}

	/* レベル表示の設定 */
	// 代理フラグ
	public boolean hide_type;
	public boolean hide_rate;
	@SubapplicationScope
	public String up_user_id;

	@SubapplicationScope
	public String goodsid;

	@Required(target = "doRegist", messageId = "user.required")
	public String goodstype;

	@Required(target = "doRegist", messageId = "user.required")
	public String goodsname;

	@Required(target = "doRegist", messageId = "user.required")
	public String tanka;

	@Required(target = "doRegist", messageId = "user.required")
	public String tani;

	public String regist;

	public Class doRegist() {
		System.out.println(login_user_id);
		// システム日付の取得
		Timestamp sysDate = PhaUtil.getTimeStamp();

		GoodsManage param = new GoodsManage();

		// パラメータ作成
		if (!Util.isEmptyString(goodsid)) {
			// 更新
			GoodsManage updInfo = new GoodsManage();

			updInfo.goodsid = goodsid;
			updInfo.goodstype = goodstype;
			updInfo.goodsname = goodsname;
			updInfo.tanka = tanka;
			updInfo.tani = tani;
			updInfo.bikou = "";
			updInfo.registe_date = sysDate;
			updInfo.registe_id = login_user_id;
			updInfo.upd_date = sysDate;
			updInfo.upd_id = "";
			updInfo.del_flg = "0";

			dao.modifydGoodsManages(updInfo);

			return OrderListPage.class;
		} else {

			param = new GoodsManage();

			param.goodsname = goodsname;
			// 新規
			int recCount = dao.getCounts(param);

			if (recCount > 0) {
				// ログインIDの存在チェック

				addErrorMessage(ErrorConst.ERR_EXISTS_CHECK_001);
				return null;
			}
			int all_count = dao.getCounts(null);
			GoodsManage insertInfo = new GoodsManage();
			// 新規
			insertInfo.goodsid = String.valueOf(all_count + 1);
			insertInfo.goodstype = goodstype;
			insertInfo.goodsname = goodsname;
			insertInfo.tanka = tanka;
			insertInfo.tani = tani;
			insertInfo.bikou = "";
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
		if (Util.isEmptyString(goodsid)) {
			// 画面項目をクリアする
		} else {
			// 画面項目を検索して、設定する
			GoodsManage param = new GoodsManage();
			param.goodsid = goodsid;
			GoodsManage retInfo = dao.getGoodsManage(param);

			goodsid = retInfo.goodsid;
			goodstype = retInfo.goodstype;
			goodsname = retInfo.goodsname;
			tanka = retInfo.tanka;
			tani = retInfo.tani;
		}

		return null;
	}

	public Class prerender() {
		return null;
	}

}
