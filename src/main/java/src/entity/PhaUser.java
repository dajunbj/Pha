package src.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table = "pha_user")
public class PhaUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4330631858167682633L;

	public String user_id;
	public String password;
	public String login_id;
	public String user_nm;
	public String smart_phone;
	public String phone;
	public String mail;
	public String address;
	public String postal_code;
	public BigDecimal exchange_rate;
	public BigDecimal rate;
	public String level;
	public String recommend_id;
	
	public Timestamp registe_date;
	public String registe_id;
	public Timestamp upd_date;
	public String upd_id;
	public String del_flg;

}
