package src.dao;

import java.util.List;
import java.util.Map;

import org.seasar.dao.annotation.tiger.SqlFile;

import src.entity.GoodProducer;
import src.entity.Goods;

public interface GoodsDao {
	public Class BEAN = Goods.class;

	public List<Goods> selectAll();

	public String selectById_ARGS = "goodsid";

	@SqlFile("sqlfile/GoodsDao_getCounts.sql")
	public int getCounts(Goods param);

	@SqlFile("sqlfile/GoodsDao_getGoods.sql")
	public Goods getGoods(Goods param);

	@SqlFile("sqlfile/GoodsDao_getGoodsList.sql")
	public List<Goods> getGoodsList(Goods param);

	@SqlFile("sqlfile/GoodsDao_selectValueLabel.sql")
	public List<Map<String, String>> selectValueLabel(Goods param);

	@SqlFile("sqlfile/GoodsDao_modifyGoods.sql")
	public int modifyGoods(Goods param);

	public int insert(Goods param);

	public int update(Goods param);

}