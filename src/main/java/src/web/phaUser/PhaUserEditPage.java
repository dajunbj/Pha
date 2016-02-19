package src.web.phaUser;

import java.math.BigDecimal;

import org.seasar.teeda.core.exception.AppFacesException;

import src.entity.PhaUser;
import src.web.CrudType;

public class PhaUserEditPage extends AbstractPhaUserPage {

	public PhaUserEditPage() {
	}

	public Class initialize() {
		if (getCrudType() == CrudType.UPDATE) {
			PhaUser data = getPhaUserDao().selectById(getUserId());
			if (data == null) {
				throw new AppFacesException("E0000001");
			}
			getPhaUserDxo().convert(data, this);
		}
		return null;
	}

	public Class prerender() {
		return null;
	}

	public static final String userNm_TRequiredValidator = null;

	public void setUserNm(String userNm) {
		super.setUserNm(userNm);
	}

	public static final String smartPhone_TRequiredValidator = null;

	public void setSmartPhone(String smartPhone) {
		super.setSmartPhone(smartPhone);
	}

	public static final String userType_TRequiredValidator = null;

	public void setUserType(String userType) {
		super.setUserType(userType);
	}

	public static final String address_TRequiredValidator = null;

	public void setAddress(String address) {
		super.setAddress(address);
	}

	public static final String postalCode_TRequiredValidator = null;

	public void setPostalCode(String postalCode) {
		super.setPostalCode(postalCode);
	}

	public static final String exchangeRate_TRequiredValidator = null;

	public void setExchangeRate(BigDecimal exchangeRate) {
		super.setExchangeRate(exchangeRate);
	}

	public static final String rate_TRequiredValidator = null;

	public void setRate(BigDecimal rate) {
		super.setRate(rate);
	}

	public static final String level_TRequiredValidator = null;

	public void setLevel(String level) {
		super.setLevel(level);
	}

	public String getIsNotCreateStyle() {
		return getCrudType() == CrudType.CREATE ? "display: none;" : null;
	}
}