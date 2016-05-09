package src.web.order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.usermodel.Paragraph;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;

import src.dao.PhaOrderDao;
import src.dao.PhaUserDao;
import src.entity.PhaOrder;
import src.entity.PhaUser;
import src.web.PhaBase;
import src.web.common.PhaUtil;
import src.web.menu.MenuPage;

public class OrderListPage extends PhaBase {

	private PhaOrderDao dao;
	private PhaUserDao UserDao;

	public OrderListPage() {
		dao = (PhaOrderDao) getContainer().getComponent(PhaOrderDao.class);
		UserDao = (PhaUserDao) getContainer().getComponent(PhaUserDao.class);

	}

	@SubapplicationScope
	public List<PhaOrder> detailItems;

	@SubapplicationScope
	public Integer select_row;

	@SubapplicationScope
	public String up_user_id;

	@SubapplicationScope
	public String orderid;
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

	public String sel_user_nm;

	@SubapplicationScope
	public boolean manager;

	public Class<OrderListPage> initialize() {

		detailItems = new ArrayList<PhaOrder>();

		return selGoodsList();
	}

	private void setUserFlg() {
		PhaUser para = new PhaUser();
		para.user_id = super.login_user_id;
		PhaUser ret = UserDao.getPhaUser(para);
		if ("0".equals(ret.level)) {
			// 0の場合、代理
			manager = true;
		} else {
			// １の場合、顧客です
			manager = false;
		}
	}

	/*
	 * 情報検索
	 */
	public Class<OrderListPage> doSelect() {
		return selGoodsList();
	}

	/*
	 * 情報検索
	 */
	public Class<OrderListPage> doSelUser() {
		return selGoodsList();
	}

	private Class<OrderListPage> selGoodsList() {

		setUserFlg();
		detailItems = new ArrayList<PhaOrder>();
		/*
		 * PhaOrder param = new PhaOrder(); param.orderid = sel_goodsName;
		 * param.goodsid = sel_goodsType; detailItems =
		 * dao.getPhaOrderList(param);
		 */
		return null;
	}

	public Class<OrderListPage> prerender() {
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
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,orderid")
	public Class<OrderRegisterPage> doRegist() {
		orderid = "";
		return OrderRegisterPage.class;
	}

	/*
	 * 更新を削除する
	 */
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,orderid")
	public Class<OrderRegisterPage> doUpdate() {

		PhaOrder info = detailItems.get(select_row);
		// orderid = info.goodsid;
		return OrderRegisterPage.class;
	}

	/*
	 * 利用者を削除する
	 */
	public Class<OrderListPage> doDelete() {

		PhaOrder info = detailItems.get(select_row);

		PhaOrder updInfo = new PhaOrder();

		Timestamp sysDate = PhaUtil.getTimeStamp();
		// updInfo.goodsid = info.goodsid;

		updInfo.upd_date = sysDate;
		updInfo.upd_id = "001";
		updInfo.del_flg = "1";
		dao.modifydPhaOrders(updInfo);
		return selGoodsList();
	}
}
