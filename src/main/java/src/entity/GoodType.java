package src.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table = "good_type")
public class GoodType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4330631858167682633L;
	public String type_id;
	public String tp_nm;
	public Timestamp registe_date;
	public String registe_id;
	public Timestamp upd_date;
	public String upd_id;
	public String del_flg;

}
