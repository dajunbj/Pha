package src.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table = "goods")
public class Goods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4330631858167682633L;
	public String goods_id;
	public String type_id;
	public String good_producer_id;
	public String goods_nm;
	public String unit;
	public String capacity;
	public String user_id;
	public Timestamp registe_date;
	public String registe_id;
	public Timestamp upd_date;
	public String upd_id;
	public String del_flg;

}
