package src.web.phaUser;

import java.sql.Timestamp;
import java.util.List;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;

import src.entity.PhaUser;
import src.web.CrudType;

public class PhaUserListPage extends AbstractPhaUserPage {

	// ユーザーIDのインデックス
	@SubapplicationScope
	public Integer rowIndex;

	@SubapplicationScope
	public String rowStyleClass;

	public String getRowStyleClass() {
		return (rowIndex + 1) % 2 == 0 ? "even" : "odd";
	}

	private List<PhaUser> phaUserItems;

	private int phaUserIndex;

	public PhaUserListPage() {
	}

	public Class initialize() {
		return null;
	}

	public Class prerender() {
		phaUserItems = getPhaUserDao().selectAll();

		return null;
	}

	public static final String doCreate_TAKE_OVER = "properties='crudType'";

	public Class doCreate() {
		setCrudType(CrudType.CREATE);
		return PhaUserEditPage.class;
	}

	public static final String registeDate_TDateTimeConverter = null;

	public Timestamp getRegisteDate() {
		return super.getRegisteDate();
	}

	public static final String updDate_TDateTimeConverter = null;

	public Timestamp getUpdDate() {
		return super.getUpdDate();
	}

	public List<PhaUser> getPhaUserItems() {
		return this.phaUserItems;
	}

	public void setPhaUserItems(List<PhaUser> items) {
		this.phaUserItems = items;
	}

	public int getPhaUserIndex() {
		return this.phaUserIndex;
	}

	public void setPhaUserIndex(int phaUserIndex) {
		this.phaUserIndex = phaUserIndex;
	}

}