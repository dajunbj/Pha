package src.web.goodProducer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;

import src.dao.GoodProducerDao;
import src.dao.GoodTypeDao;
import src.entity.GoodProducer;
import src.web.PhaBase;
import src.web.common.PhaUtil;
import src.web.goods.GoodsListPage;
import src.web.goods.GoodsRegisterPage;
import src.web.menu.MenuPage;

import com.sun.xml.internal.fastinfoset.stax.events.Util;

/*商品品名登録一覧画面*/
public class GoodProducerListPage extends PhaBase {

	private GoodProducerDao dao;

	private GoodTypeDao typeDao;

	public GoodProducerListPage() {
		dao = (GoodProducerDao) getContainer().getComponent(
				GoodProducerDao.class);

		// 商品種別リストの初期化
		typeDao = (GoodTypeDao) getContainer().getComponent(GoodTypeDao.class);
	}

	@SubapplicationScope
	public String good_producer_id;
	public String type_id;
	public String tp_nm;
	public String good_producer_nm;
	public String founder;
	public String address;
	public String phone;

	@SubapplicationScope
	public List<GoodProducer> detailItems;

	@SubapplicationScope
	public Integer select_row;

	@SubapplicationScope
	public String up_user_id;

	@SubapplicationScope
	public String selectedId;

	// 商品
	@RegularExpression(target = "doSelect", messageId = "user.banjiaoWenzi", pattern = "[ -~]+")
	public String sel_good_producer_nm;

	// 商品種別選択のリストボックス
	public String sel_typeId;
	public List<Map<String, String>> sel_typeIdItems;

	/** ----------検索条件 終了---------- **/

	public Class<GoodProducerListPage> initialize() {
		detailItems = new ArrayList<GoodProducer>();

		return selGoodProducerList();
	}

	/*
	 * 情報検索
	 */
	public Class<GoodProducerListPage> doSelect() {
		return selGoodProducerList();
	}

	private Class<GoodProducerListPage> selGoodProducerList() {
		detailItems = new ArrayList<GoodProducer>();

		GoodProducer param = new GoodProducer();

		if (!Util.isEmptyString(sel_good_producer_nm)) {
			param.good_producer_nm = sel_good_producer_nm + "%";
		}
		param.type_id = sel_typeId;

		// 明細取得
		detailItems = dao.getGoodProducerList(param);

		// 商品種別リストボックス
		sel_typeIdItems = typeDao.selectValueLabel();

		return null;
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
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,good_producer_id")
	public Class<GoodProducerRegisterPage> doRegist() {
		good_producer_id = "";
		return GoodProducerRegisterPage.class;
	}

	/*
	 * 更新を削除する
	 */
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,good_producer_id")
	public Class<GoodProducerRegisterPage> doUpdate() {

		GoodProducer info = detailItems.get(select_row);
		good_producer_id = info.good_producer_id;
		return GoodProducerRegisterPage.class;
	}

	/*
	 * 利用者を削除する
	 */
	public Class<GoodProducerListPage> doDelete() {

		GoodProducer info = detailItems.get(select_row);

		GoodProducer updInfo = new GoodProducer();

		Timestamp sysDate = PhaUtil.getTimeStamp();
		updInfo.good_producer_id = info.good_producer_id;

		updInfo.upd_date = sysDate;
		updInfo.upd_id = "001";
		updInfo.del_flg = "1";
		dao.modifydGoodProducer(updInfo);
		return selGoodProducerList();
	}
}
