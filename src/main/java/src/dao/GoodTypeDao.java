package src.dao;

import java.util.List;
import java.util.Map;

import org.seasar.dao.annotation.tiger.SqlFile;

import src.entity.GoodType;

public interface GoodTypeDao {
	public Class BEAN = GoodType.class;

	public List<GoodType> selectAll();

	public String selectById_ARGS = "type_id";

	@SqlFile("sqlfile/GoodTypeDao_getCounts.sql")
	public int getCounts(GoodType param);

	@SqlFile("sqlfile/GoodTypeDao_getGoodType.sql")
	public GoodType getGoodType(GoodType param);

	@SqlFile("sqlfile/GoodTypeDao_getGoodTypeList.sql")
	public List<GoodType> getGoodTypeList(GoodType param);

	@SqlFile("sqlfile/GoodTypeDao_selectValueLabel.sql")
	public List<Map<String, String>> selectValueLabel();

	@SqlFile("sqlfile/GoodTypeDao_modifydGoodType.sql")
	public int modifydGoodType(GoodType param);

	public int insert(GoodType param);

	public int update(GoodType param);

}