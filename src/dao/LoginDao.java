
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import model.UserInfo;
import utils.DataConnect;

public class LoginDao {
	
	private static final Logger logger = LogManager.getLogger(LoginDao.class);

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcTemplate.setFetchSize(1200);
	}
	
	public List<UserInfo> getUser(String user, String Password) throws Exception {
		
		String queryString = "Select uid, password, fname, lname, tn, address, role from Users where uid = ? and password = ?";

		logger.info("SQL :" + queryString + "--userId: " + user);
		return jdbcTemplate.query(queryString, new LoginUserMapper(), user, Password);
	}
	
	public static final class LoginUserMapper implements RowMapper<UserInfo> {
		public UserInfo mapRow(ResultSet rs, int rownum) throws SQLException {

			UserInfo _result = new UserInfo();

			_result.setUid(rs.getString("UID"));
			_result.setFname(rs.getString("FNAME"));
			_result.setLname(rs.getString("LNAME"));
			_result.setPhone(rs.getString("TN"));
			_result.setPassword(rs.getString("PASSWORD"));
			_result.setAddress(rs.getString("ADDRESS"));
			_result.setRole(rs.getString("ROLE"));

			return _result;
		}
		
	}
	
	public List<UserInfo> getPassword(String user) throws Exception {
		
		String queryString = "Select uid, password, email from Users where uid = ? ";

		logger.info("SQL :" + queryString + "--userId: " + user);
		return jdbcTemplate.query(queryString,new PasswordMapper(), user);
	}
	
	public static final class PasswordMapper implements RowMapper<UserInfo> {
		public UserInfo mapRow(ResultSet rs, int rownum) throws SQLException {

			UserInfo _result = new UserInfo();

			_result.setPassword(rs.getString("PASSWORD"));
			_result.setEmail(rs.getString("EMAIL"));
			
			return _result;
		}
		
	}
	
	public int updPassword(String user, String pwd, String newPwd) throws Exception {
		
		String queryString = "update users set password = ? where uid = ? and password = ? ";

		logger.info("SQL :" + queryString + "--userId: " + user);
		// define query arguments
        Object[] params = {newPwd, user, pwd};
     // define SQL types of the arguments
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		return jdbcTemplate.update(queryString, params, types);
		
	}

}
