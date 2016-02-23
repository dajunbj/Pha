package src.web;

import javax.faces.internal.FacesMessageUtil;
import javax.servlet.http.HttpServlet;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.teeda.core.exception.AppFacesException;
import org.seasar.teeda.extension.annotation.scope.RedirectScope;
import org.seasar.teeda.extension.util.FacesMessageHelper;

public class PhaBase extends HttpServlet implements FacesMessageHelper {

	@RedirectScope
	public String login_user_id;

	/* ログイン画面のログインユーザID */
	@RedirectScope
	public String level;

	// 設定ファイルのPath
	public static final String PATH = "pha/s2Dao.dicon";

	public static S2Container getContainer() {
		// 設定ファイルを読み込む.
		SingletonS2ContainerFactory.setConfigPath(PATH);

		// 初期化する.
		SingletonS2ContainerFactory.init();

		// コンテナを取得する.
		return SingletonS2ContainerFactory.getContainer();

	}

	public void clearError() {

	}

	@Override
	public void addWarnMessage(String messageId) {
		throw new AppFacesException(messageId);
	}

	@Override
	public void addWarnMessage(String messageId, Object[] args) {
		addErrorMessage(messageId, args);
		throw new AppFacesException(messageId);

	}

	@Override
	public void addWarnMessage(String clientId, String messageId) {

	}

	@Override
	public void addWarnMessage(String clientId, String messageId, Object[] args) {

	}

	@Override
	public void addInfoMessage(String messageId) {

	}

	@Override
	public void addInfoMessage(String messageId, Object[] args) {

	}

	@Override
	public void addInfoMessage(String clientId, String messageId) {

	}

	@Override
	public void addInfoMessage(String clientId, String messageId, Object[] args) {

	}

	@Override
	public void addErrorMessage(String messageId) {
		FacesMessageUtil.addErrorMessage(messageId);
		// throw new AppFacesException("");
	}

	@Override
	public void addErrorMessage(String messageId, Object[] args) {
		FacesMessageUtil.addErrorMessage(messageId, args);
		throw new AppFacesException("");
	}

	@Override
	public void addErrorMessage(String clientId, String messageId) {

	}

	@Override
	public void addErrorMessage(String clientId, String messageId, Object[] args) {

	}

	@Override
	public void addFatalMessage(String messageId) {

	}

	@Override
	public void addFatalMessage(String messageId, Object[] args) {

	}

	@Override
	public void addFatalMessage(String clientId, String messageId) {

	}

	@Override
	public void addFatalMessage(String clientId, String messageId, Object[] args) {

	}

	@Override
	public String getSummary(String messageId, Object[] args) {
		return null;
	}

	@Override
	public String getDetail(String messageId, Object[] args) {
		return null;
	}

	@Override
	public boolean hasMessages() {
		return false;
	}

	@Override
	public boolean hasErrorOrFatalMessage() {
		return false;
	}

}
