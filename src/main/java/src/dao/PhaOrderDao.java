package src.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.SqlFile;

import src.entity.PhaOrder;

public interface PhaOrderDao {
	public Class BEAN = PhaOrder.class;

	public List<PhaOrder> selectAll();

	public String selectById_ARGS = "goodsid";

	@SqlFile("sqlfile/PhaOrderDao_getCounts.sql")
	public int getCounts(PhaOrder param);

	@SqlFile("sqlfile/PhaOrderDao_getPhaOrder.sql")
	public PhaOrder getPhaOrder(PhaOrder param);

	@SqlFile("sqlfile/PhaOrderDao_getPhaOrderList.sql")
	public List<PhaOrder> getPhaOrderList(PhaOrder param);

	@SqlFile("sqlfile/PhaOrderDao_modifyPhaOrder.sql")
	public int modifydPhaOrders(PhaOrder param);

	public int insert(PhaOrder param);

	public int update(PhaOrder param);

}