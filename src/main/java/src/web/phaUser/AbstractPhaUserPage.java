package src.web.phaUser;

import java.sql.Timestamp;
import java.math.BigDecimal;

import src.dao.PhaUserDao;
import src.web.AbstractCrudPage;

public abstract class AbstractPhaUserPage extends AbstractCrudPage {

	private PhaUserDao phaUserDao;
	
	private PhaUserDxo phaUserDxo;
	
	private String userId;

	private String userNm;

	private String smartPhone;

	private String userType;

	private String mail;

	private String address;

	private String postalCode;

	private BigDecimal exchangeRate;

	private BigDecimal rate;

	private String level;

	private String recommendId;

	private Timestamp registeDate;

	private String registeId;

	private Timestamp updDate;

	private String updId;

	private String delFlg;

	public AbstractPhaUserPage() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return this.userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getSmartPhone() {
		return this.smartPhone;
	}

	public void setSmartPhone(String smartPhone) {
		this.smartPhone = smartPhone;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public BigDecimal getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRecommendId() {
		return this.recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	public Timestamp getRegisteDate() {
		return this.registeDate;
	}

	public void setRegisteDate(Timestamp registeDate) {
		this.registeDate = registeDate;
	}

	public String getRegisteId() {
		return this.registeId;
	}

	public void setRegisteId(String registeId) {
		this.registeId = registeId;
	}

	public Timestamp getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getUpdId() {
		return this.updId;
	}

	public void setUpdId(String updId) {
		this.updId = updId;
	}

	public String getDelFlg() {
		return this.delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}


	public PhaUserDao getPhaUserDao() {
		return this.phaUserDao;
	}

	public void setPhaUserDao(PhaUserDao phaUserDao) {
		this.phaUserDao = phaUserDao;
	}

	public PhaUserDxo getPhaUserDxo() {
		return this.phaUserDxo;
	}

	public void setPhaUserDxo(PhaUserDxo phaUserDxo) {
		this.phaUserDxo = phaUserDxo;
	}
}