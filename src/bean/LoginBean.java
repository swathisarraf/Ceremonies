package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import context.LoginContext;
import dao.LoginDao;
import model.UserInfo;
import utils.Email;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
		
	private static final long serialVersionUID = 1094801825228386363L;
	
	private String pwd;
	private String msg;
	private String user;
	private String newPwd;
	private UserInfo userdtl;
	private LoginContext context;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public UserInfo getUserdtl() {
		return userdtl;
	}

	public void setUserdtl(UserInfo userdtl) {
		this.userdtl = userdtl;
	}

	@PostConstruct
	public void init() {
		System.out.println("managed bean is invoked");
		
		//System.out.println("user id is "+ user);
		//System.out.println("password is "+ pwd);
		context = new LoginContext();
		//userl = new UserInfo();
	}
	//validate login
	public String validateUser() {
		try {
			System.out.println("validateUser: user id is "+ user);
			System.out.println("validateUser: password is "+ pwd);
			//LoginContext context = new LoginContext();
			userdtl = context.getUserAccess(user,pwd);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("user name is "+ userdtl.getUid());

		return userdtl.getUid();
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
	
	public void getPassword() {
		try{
			System.out.println("getPassword: Getting password for "+ user);
			userdtl = context.getPassword(user);
			if (userdtl != null) {
				System.out.println("getPassword: Password for "+ user+ " is "+ userdtl.getPassword());
				System.out.println("getPassword: Email for "+ user+ " is "+ userdtl.getEmail());
				// Mail java code 
				String sub = "Password reset";
				String message = "Your password for the ceremonies application is "+userdtl.getPassword() ;
				Email.sendEmail(sub, message, userdtl.getEmail());
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_INFO,null,
								"Password for the "+ user+ " has been sent to "+userdtl.getEmail()));
			}
			else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,null, user+ " doesn't exist."));
			}
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String changePassword() {
		try{
			System.out.println("Change Password : Get the current password for "+ user);
			userdtl = context.getPassword(user);
			if (userdtl != null) {
				if (!pwd.equals(userdtl.getPassword())) {
					System.out.println("Change Password : Current password and Entered old password don't match for the  "+ user);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,null,"Current password and Entered old password don't match for the "+ user));
					return "Error";
				}
			}
			else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,null,user+" Not found."));
				return "Error";
			}
				
			System.out.println("Change Password : Updating password for "+ user);
			int status = context.updPassword(user,pwd,newPwd);
			if (status > 0) {
				System.out.println("Change Password: Password for "+ user+ " has been updated ");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,null,"Password for"+ user + "has been changed"));
			}
			else {
				System.out.println("Change Password: Password for "+ user+ " is not updated. Please check ");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,null,"Password for"+ user + "is not changed"));
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
				
	}
}