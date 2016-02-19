package src.web.menu;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;

import src.dao.PhaUserDao;
import src.entity.PhaUser;
import src.web.PhaBase;
import src.web.common.PhaConstCommon;
import src.web.goodProducer.GoodProducerListPage;
import src.web.goodType.GoodTypeListPage;
import src.web.goods.GoodsListPage;
import src.web.order.OrderListPage;
import src.web.user.UserRegisterListPage;
import src.web.user.UserRegisterPage;

public class MenuPage extends PhaBase {

	public String subMenuId;
	public String title_name = "メニュー";
	/* スパーユーザの項目表示フラグ */
	public boolean superUserDisplay;
	/* システム利用者フラグ */
	public boolean systemUserDisplay;

	@SubapplicationScope
	public PhaUser phaUserItem;

	public Class initialize() {
		PhaUserDao dao = (PhaUserDao) getContainer().getComponent(
				PhaUserDao.class);

		PhaUser param = new PhaUser();
		param.user_id = login_user_id;
		PhaUser ret = dao.getPhaUser(param);

		if (ret != null) {
			super.level = ret.level;
		}
		if (PhaConstCommon.user_level_0.equals(super.level)) {
			// 代理メニューを表示
			superUserDisplay = true;
			// システム利用者メニューを非表示
			systemUserDisplay = false;
		} else if (PhaConstCommon.user_level_1.equals(super.level)) {
			// 普通顧客メニューを表示
			superUserDisplay = false;
			// システム利用者メニューを非表示
			systemUserDisplay = false;
		} else if (PhaConstCommon.user_level_9.equals(super.level)) {
			// システム利用者メニュー以外を表示
			superUserDisplay = true;
			// システム利用者メニューを表示
			systemUserDisplay = true;
		}

		return null;
	}

	public Class prerender() {
		return null;
	}

	// -------------------------------------------------------------------------------------------

	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id")
	public Class doGoodsType() {

		return GoodTypeListPage.class;

	}

	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id")
	public Class doGoodProducer() {

		return GoodProducerListPage.class;

	}

	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id")
	public Class doGoods_Register() {

		return GoodsListPage.class;

	}

	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id")
	public Class doOrder_Register() {

		return OrderListPage.class;
	}

	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id")
	public Class doUser_Register() {

		return OrderListPage.class;
	}

	// -------------------------------------------------------------------------------------------

	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id")
	public Class doUser_register() {
		if (PhaConstCommon.user_level_9.equals(super.level)) {
			// 管理員
			return UserRegisterListPage.class;
		} else if (PhaConstCommon.user_level_1.equals(super.level)) {
			// 代理
			return UserRegisterListPage.class;
		} else if (PhaConstCommon.user_level_0.equals(super.level)) {
			// 利用者
			return UserRegisterPage.class;
		} else {

			return UserRegisterListPage.class;
		}
	}

	public Class doGoodList() {
		return GoodsListPage.class;
	}

	public Class doGoods_delivery() {
		return null;
	}

	public Class doOrder() {
		return null;
	}

	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id")
	public Class doReturn() {

		return GoodTypeListPage.class;

	}
}
