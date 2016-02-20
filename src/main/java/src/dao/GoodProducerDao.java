package src.dao;

import java.util.List;
import java.util.Map;

import org.seasar.dao.annotation.tiger.SqlFile;

import src.entity.GoodProducer;

public interface GoodProducerDao {
	public Class BEAN = GoodProducer.class;

	public List<GoodProducer> selectAll();

	public String selectById_ARGS = "good_producer_id";

	@SqlFile("sqlfile/GoodProducerDao_getCounts.sql")
	public int getCounts(GoodProducer param);

	@SqlFile("sqlfile/GoodProducerDao_getGoodProducer.sql")
	public GoodProducer getGoodProducer(GoodProducer param);

	@SqlFile("sqlfile/GoodProducerDao_getGoodProducerList.sql")
	public List<GoodProducer> getGoodProducerList(GoodProducer param);

	@SqlFile("sqlfile/GoodProducerDao_selectValueLabel.sql")
	public List<Map<String, String>> selectValueLabel(GoodProducer param);

	@SqlFile("sqlfile/GoodProducerDao_modifydGoodProducer.sql")
	public int modifydGoodProducer(GoodProducer param);

	public int insert(GoodProducer param);

	public int update(GoodProducer param);

}