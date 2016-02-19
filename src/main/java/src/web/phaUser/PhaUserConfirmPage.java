package src.web.phaUser;

import java.sql.Timestamp;
import java.math.BigDecimal;


import org.seasar.teeda.core.exception.AppFacesException;
import org.seasar.teeda.extension.util.LabelHelper;

import src.entity.PhaUser;
import src.web.CrudType;

public class PhaUserConfirmPage extends AbstractPhaUserPage {
	
	private LabelHelper labelHelper;
	
	public PhaUserConfirmPage() {
	}
	
	public Class initialize() {
		if(isComeFromList()) {
			PhaUser data = getPhaUserDao().selectById(getUserId());
			if(data == null) {
				throw new AppFacesException("E0000001");
			}
			getPhaUserDxo().convert(data ,this);
		}
		return null;
	}
	
	public Class prerender() {
		return null;
	}

	public static final String doFinish_TAKE_OVER = "type=never";
	public Class doFinish() {
		switch(getCrudType()) {
			case CrudType.CREATE:
				getPhaUserDao().insert(getPhaUserDxo().convert(this));
				break;
			case CrudType.UPDATE:
				getPhaUserDao().update(getPhaUserDxo().convert(this));
				break;
			case CrudType.DELETE:
				getPhaUserDao().delete(getPhaUserDxo().convert(this));
				break;
			default:
				break;
		}
		return PhaUserListPage.class;
	}
	
	public boolean isComeFromList() {
        return getCrudType() == CrudType.READ || getCrudType() == CrudType.DELETE;
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

	public static final String registeDate_TDateTimeConverter = null;
	public Timestamp getRegisteDate() {
		return super.getRegisteDate();
	}

	public static final String updDate_TDateTimeConverter = null;
	public Timestamp getUpdDate() {
		return super.getUpdDate();
	}

	public void setLabelHelper(LabelHelper labelHelper) {
		this.labelHelper = labelHelper;
	}
	
	public LabelHelper getLabelHelper() {
		return this.labelHelper;
	}
	
	public String getJumpPhaUserEditStyle() {
		return isComeFromList() ? "display: none;" : "";
	}

	public String getDoFinishValue() {
        return labelHelper.getLabelValue(CrudType.toString(getCrudType()));
    }
}