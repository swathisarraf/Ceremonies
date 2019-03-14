package context;

import org.springframework.context.ApplicationContext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.LoginDao;
import model.UserInfo;
import utils.AppContextUtils;

public class LoginContext {
	
	private static final Logger logger = LogManager.getLogger(LoginContext.class);
	private LoginDao loginDao = null;
	private UserInfo userdtl;
	
	public LoginContext() {
		ApplicationContext context = AppContextUtils.getApplicationContext();
		loginDao = context.getBean("loginDao", LoginDao.class);
	}
	
	public UserInfo getUserAccess(String user, String Password) throws Exception {
		String methodName = "getUserAccess";
		logger.info(methodName + " Method Enter ..");
		userdtl = null;
		List<UserInfo> _result = loginDao.getUser(user,Password);
		if (_result.size() > 0) {
			 userdtl = _result.get(0);
			// if (!user.getUserId().equalsIgnoreCase("ms4145")) {
			// loginDao.insertUserAccessQuery(user.getUserId());
			// }
		}

		logger.info(methodName + " Method Exit ...");
		return userdtl;
	}
	
	public UserInfo getPassword(String user) throws Exception {
		String methodName = "getPassword";
		logger.info(methodName + " Method Enter ..");
		userdtl = null;
		
		List<UserInfo> _result = loginDao.getPassword(user);
		if (_result.size() > 0) {
			System.out.println("Query resulted data");
			 userdtl = _result.get(0);
			// if (!user.getUserId().equalsIgnoreCase("ms4145")) {
			// loginDao.insertUserAccessQuery(user.getUserId());
			// }
		}

		logger.info(methodName + " Method Exit ...");
		return userdtl;
	}
	
	public int updPassword(String user, String pwd, String newPwd) throws Exception {
		String methodName = "getPassword";
		logger.info(methodName + " Method Enter ..");
		
		int result = loginDao.updPassword(user,pwd,newPwd);
		return result;
	}

}
