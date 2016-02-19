package src.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table = "pha_order")
public class PhaOrder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String order_id;
	public String goods_id;
	public String amount;
	public Timestamp order_date;
	public String user_id;
	public String manager_id;
	public String close_kbn;
	public String state;
	public Timestamp registe_date;
	public String registe_id;
	public Timestamp upd_date;
	public String upd_id;
	public String del_flg;
}
