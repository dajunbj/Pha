package src.web.goods;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;

import src.dao.GoodProducerDao;
import src.dao.GoodTypeDao;
import src.dao.GoodsDao;
import src.entity.GoodProducer;
import src.entity.Goods;
import src.web.PhaBase;
import src.web.common.PhaUtil;
import src.web.menu.MenuPage;

public class GoodsListPage extends PhaBase {

	private GoodsDao dao;

	private GoodTypeDao typeDao;

	private GoodProducerDao goodProducerDao;

	public GoodsListPage() {
		dao = (GoodsDao) getContainer().getComponent(GoodsDao.class);
		// 商品種別リストの初期化
		typeDao = (GoodTypeDao) getContainer().getComponent(GoodTypeDao.class);
		// 商品品名リストの初期化
		goodProducerDao = (GoodProducerDao) getContainer().getComponent(GoodProducerDao.class);
	}

	@SubapplicationScope
	public List<Goods> detailItems;

	@SubapplicationScope
	public Integer select_row;

	@SubapplicationScope
	public String up_user_id;

	@SubapplicationScope
	public String goodsid;
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

	public String sel_good_producer_id;
	public List<Map<String, String>> sel_good_producer_idItems;

	// 商品種別選択のリストボックス
	public String sel_typeId;
	public List<Map<String, String>> sel_typeIdItems;

	public Class<GoodsListPage> initialize() {

		// 商品種別リストボックス
		sel_typeIdItems = typeDao.selectValueLabel();
		// 商品品名の連動
		GoodProducer inputParam = new GoodProducer();
		inputParam.type_id = null;
		sel_good_producer_idItems = goodProducerDao.selectValueLabel(inputParam);

		detailItems = new ArrayList<Goods>();

		return selGoodsList();
	}

	/*
	 * 情報検索
	 */
	public Class<GoodsListPage> doSelect() {
		return selGoodsList();
	}
/*
	public ActionResult ReadPersons(List<PersonModel> model) 
    { 
        string result = ""; 
        if (model == null) return Content(result); 
        foreach (var s in model) 
        { 
            result += s.ToString(); 
            result += "-------------"; 
        }
        return Content(result); 
    }*/

	private Class<GoodsListPage> selGoodsList() {
		detailItems = new ArrayList<Goods>();

		Goods param = new Goods();
		param.goods_nm = sel_good_producer_id;
		param.type_id = sel_typeId;
		detailItems = dao.getGoodsList(param);

		// 商品種別リストボックス.
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
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,goodsid")
	public Class<GoodsRegisterPage> doRegist() {
		goodsid = "";
		return GoodsRegisterPage.class;
	}

	/*
	 * 更新を削除する
	 */
	@TakeOver(type = TakeOverType.INCLUDE, properties = "login_user_id,goodsid")
	public Class<GoodsRegisterPage> doUpdate() {

		Goods info = detailItems.get(select_row);
		goodsid = info.goods_id;
		return GoodsRegisterPage.class;
	}

	/*
	 * 利用者を削除する
	 */
	public Class<GoodsListPage> doDelete() {

		Goods info = detailItems.get(select_row);

		Goods updInfo = new Goods();

		Timestamp sysDate = PhaUtil.getTimeStamp();
		updInfo.goods_id = info.goods_id;

		updInfo.upd_date = sysDate;
		updInfo.upd_id = "001";
		updInfo.del_flg = "1";
		dao.modifyGoods(updInfo);
		return selGoodsList();
	}
}
