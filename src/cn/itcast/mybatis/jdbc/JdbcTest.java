package cn.itcast.mybatis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * 通过jdbc方式查询数据。
 * @author huber_000
 * @Question
 * 1.数据库连接，使用时就创建，不适用就立即释放，都数据库进行频繁的开启与关闭，
 * 造成数据库资源浪费，影响数据性能。
 * 设想：使用数据连接池
 * 
 * 2.将sql语句硬编码到java代码中。如果sql语句修改，需要重新编译java代码，不利于系统维护
 * 设想：将sql语句配置在xml配置文件中，即使sql变化，不需要对java代码进行重新编译
 * 
 * 3.向preparedStatement中设置参数，对占位符号位置和设置参数值，硬编码在java代码中，不利于系统维护
 * 设想:将sql语句及占位符好和参数全部配置在xml中
 * 
 * 4.从resultSet中遍历结果集数据时，存在硬编码，将获取表的字段进行硬编码。
 * 设想：将查询的结果集，自动映射成java对象
 */

public class JdbcTest {
	public static void main(String[] args) {
		//数据库连接
		Connection connection = null;
		//预编译的statement，使用预编译的Statement提高数据库的性能
		PreparedStatement preparedStatement = null;
		//结果集对象
		ResultSet resultSet = null;
		
		try {
			//加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//通过驱动管理类获取数据库连接
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8","root","123");
			//定义sql语句?占位符
			String sql ="select * from user where username=?";
			//获取预处理statement
			preparedStatement = connection.prepareStatement(sql);
			//设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
			preparedStatement.setString(1, "王五");
			//向数据库发出sql执行查询，查询出结果集
			resultSet = preparedStatement.executeQuery();
			//遍历擦汗寻结果集
			while(resultSet.next()){
				System.out.println(resultSet.getString("id")+" "+resultSet.getString("username"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//释放资源
			if(resultSet!=null){
				try {
					resultSet.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		}
	}
}
