package dp.creationpattern.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * 2008-12-6
 * 
 * @author <a href="mailto:liyongibm@hotmail.com">锟斤拷锟斤拷</a>
 * 
 */
/*
 * 注意点:
 *  * 构造函数private
 *  * 单例对象private static
 *  * getInstance() 同步. synchronized 可以在方法上，也可以在块上，块上更快些.
 *    如果时块上加锁，需要两次检查，分别在锁之前和锁之后.
 *  
 * JDK中: java.lang.Runtime#getRuntime()  用的eager模式.
 * 
 */
public final class AboutSingleton {
	private String url = "jdbc:oracle:thin:@127.0.0.1:1522:COMPBK";
	private String user = "leslie1";
	private String password = "leslie1";

	// private static JdbcUtilsSing instance = new JdbcUtilsSing();
	private static AboutSingleton instance = null;
	
	private AboutSingleton() {
	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	/*
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
	}
	*/
	public static AboutSingleton getInstance() {
		if (instance == null) {
			// 这里必须是静态的Reference, 不可以时int等.
			synchronized (AboutSingleton.class) {
				if (instance == null) {
					instance = new AboutSingleton();
				}
			}
		}
		return instance;
	}
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
	}
}
