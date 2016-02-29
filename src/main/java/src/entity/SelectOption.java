package src.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.seasar.dao.annotation.tiger.Bean;

public class SelectOption implements Serializable {

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4330631858167682633L;
	public String goods_id;
	public String type_id;

}
