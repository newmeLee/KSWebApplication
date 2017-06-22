package cn.zjzt.service.system;

import cn.zjzt.dao.system.LoginDao;

public class LoginService {

	private LoginDao loginDao;
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	private static final String MESSAGE_CORRECT_PSD = "200";
	private static final String MESSAGE_ERROR_PSD = "500";
	private static final String MESSAGE_ERROR_NONE_USER = "404";

	public String validateUser(String userName, String password) {
		if (loginDao.queryUser(userName) > 0) {
			return loginDao.getUserPassword(userName).equals(password) ? MESSAGE_CORRECT_PSD
					: MESSAGE_ERROR_PSD;
		}else{
			return MESSAGE_ERROR_NONE_USER;
		}
	}
}
