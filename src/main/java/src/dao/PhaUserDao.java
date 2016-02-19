package src.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.SqlFile;

import src.entity.PhaUser;

public interface PhaUserDao {
	public Class BEAN = PhaUser.class;

	public List<PhaUser> selectAll();

	public String selectById_ARGS = "user_id";

	// ログイン利用者の検索
	@SqlFile("sqlfile/PhaUserDao_getLoginPhaUser.sql")
	public PhaUser getLoginPhaUser(PhaUser user);

	@SqlFile("sqlfile/PhaUserDao_getPhaUser.sql")
	public PhaUser getPhaUser(PhaUser user);

	@SqlFile("sqlfile/PhaUserDao_getPhaUserList.sql")
	public List<PhaUser> getPhaUserList(PhaUser user);

	@SqlFile("sqlfile/PhaUserDao_getPhaUserByName.sql")
	public int getPhaUserByName(String name);

	@SqlFile("sqlfile/PhaUserDao_getAllPhaUsers.sql")
	public List<PhaUser> getAllPhaUsers();

	@SqlFile("sqlfile/PhaUserDao_getAllPhaUsersCount.sql")
	public int getAllPhaUsersCount();

	@SqlFile("sqlfile/PhaUserDao_updPhaUsers.sql")
	public int updPhaUser(PhaUser user);

	public int insert(PhaUser phaUser);

	public int update(PhaUser phaUser);

}