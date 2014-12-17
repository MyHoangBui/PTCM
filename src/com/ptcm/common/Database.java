package com.ptcm.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import com.ptcm.model.Driver;



public class Database {
	public final String PREFIX = "PTCM_";
	private Connection connection;
	
	public Database(String host,String port,String database,String username, String password) throws Exception{
		// TODO Auto-generated constructor stub
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		this.connection = DriverManager.getConnection("jdbc:sqlserver://"+host+":"+port+";database="+database,username,password);
	}
	
	private Class<?> getObjectClass(Object obj){
		return obj.getClass();
	}
	
	private String getTableName(String clsName){
		System.out.println("Class name is: "+clsName);
		StringTokenizer string = new StringTokenizer(clsName," ");
		String token = "";
		System.out.println("Number of token"+string.countTokens());
		while(string.hasMoreTokens()){
			token = string.nextToken();
			System.out.println("Token is: "+ token);
		}
		string = new StringTokenizer(token,".");
		while(string.hasMoreTokens()){
			token = string.nextToken();
			System.out.println("Token is: "+ token);
		}
		return token;
		
	}
	
	public int insertObject(Object obj) throws Exception{
		String table = this.getTableName(this.getObjectClass(obj).toString());
		String sql = "INSERT INTO "+PREFIX+table+" values(";
		Object value[] = this.getValue(obj);
		for (int i = 0; i < value.length; i++) {
			if(value[i] instanceof Date){
				Date date = (Date)value[i];
				
				String values = DateFormat.getInstance().format(date);
				sql += "'"+values+"'";
			}else{
				sql += "'"+value[i]+"'";
			}
			//sql += "'"+value[i]+"'";
			if(i != value.length -1)
				sql += ",";
		}
		
		
		
		sql += ")";
		System.out.println(sql);
		Statement stt = this.connection.createStatement();
		
		
		return stt.executeUpdate(sql);
		
	}
	
	private String[] getField(Object obj){
		Class<?> cls = obj.getClass();
		Field fields[] = cls.getDeclaredFields();
		Field supperFields[] = cls.getSuperclass().getDeclaredFields();
		
			
		int fieldLength = fields.length;
		int fieldSuperLength = supperFields.length;
		String data[] = new String[fieldLength+fieldSuperLength];
		
		
		for (int i = 0; i < fieldSuperLength; i++) {
			data[i] = supperFields[i].getName();
		}
		for (int i = 0; i < fieldLength; i++) {
			data[i+fieldSuperLength] = fields[i].getName();
		}
		return data;
		
	}
	
	private Object[] getValue(Object obj) throws Exception{
		String field[] = this.getField(obj);
		Class<?> cls = obj.getClass();
		Object data[] = new Object[field.length];
		
		
		for (int i = 0; i < field.length; i++) {
			System.out.println("Field "+field[i]);
			Class<?>clss[] = new Class[0]; 
			String pre = ""+field[i].charAt(0);
			pre = pre.toUpperCase();
			String methodName = pre+field[i].substring(1);
			Method mt = cls.getDeclaredMethod("get"+methodName,clss);
			System.out.println("Invoke method "+methodName);
			
			data[i] =  mt.invoke(obj,new Object[]{});
			System.out.println(mt.invoke(obj,new Object[]{}));
		}
		return data;
		
	}
	
	/*public static void main(String[] args) throws Exception {
		
		
		Database db = new Database("localhost", "1433", "PTCM", "sa", "1234");
		Driver dr = new Driver(1, "hieu", "sss", "222", new Date(), "sss");
		db.insertObject(dr);
		
	}*/
	
	
}
