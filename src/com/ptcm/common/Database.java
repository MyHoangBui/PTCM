package com.ptcm.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import com.ptcm.model.Driver;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;



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
	
	public int insertObject(Object obj) {
		String table = this.getTableName(this.getObjectClass(obj).toString());
		
		String sql = "INSERT INTO "+PREFIX+table+"(";
		String fieldName[] = this.getField(obj);
		
		for (int i = 0; i < fieldName.length; i++) {
			sql += fieldName[i];
			if(i != fieldName.length -1)
				sql += ",";
		}
		sql += ") values(";
		int result = -1;
		try {
			Object value[] = this.getValue(obj);
			for (int i = 0; i < value.length; i++) {
				if(value[i] instanceof Date){
					Date date = (Date)value[i];
					
					String values = this.dateFormat(date);
					sql += "'"+values+"'";
				}else{
					sql += "'"+value[i]+"'";
				}
				
				if(i != value.length -1)
					sql += ",";
			}
			
			
			
			sql += ")";
			System.out.println(sql);
			Statement stt = this.connection.createStatement();
			result = stt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public ArrayList<ArrayList<String>> getObject(Object obj,int page){
		
		String tableName = this.getTableName(this.getObjectClass(obj).toString());
		ArrayList<ArrayList<String>> data = new ArrayList<>();
		try {
			String sql = "SELECT TOP "+(page*30)+"* FROM "+PREFIX+tableName;
			Statement stt = this.connection.createStatement();
			stt.execute(sql);
			ResultSet result = stt.getResultSet();
			ResultSetMetaData metadata = result.getMetaData();
			ArrayList<String> row = new ArrayList<>();
			int colsize = metadata.getColumnCount();
			for (int i = 0; i < colsize; i++) {
				row.add(metadata.getColumnName(i+1));
			}
			data.add(row);
			
			while(result.next()){
				ArrayList<String> rows = new ArrayList<>();
				for (int i = 0; i < colsize; i++) {
					rows.add(result.getString(i+1));
				}
				data.add(rows);
			}
			
			return data;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	public int updateObject(Object obj){
		String tableName = this.getTableName(this.getObjectClass(obj).toString());
		
		String fields[] = this.getField(obj);
		
		try {
			Object values[] = this.getValue(obj);
			String sql = "UPDATE "+PREFIX+tableName+ " SET ";
			
			for (int i = 1; i < values.length; i++) {
				sql += fields[i]+"=";
				if(values[i] instanceof Date ){
					sql += "'"+this.dateFormat((Date)values[i])+"'";
				}else{
					sql += "'"+values[i]+"'";
				}
				if(i != values.length -1){
					sql += ",";
				}
			}
			sql += " WHERE "+fields[0]+"='"+values[0]+"'";
			Statement stt = this.connection.createStatement();
			System.out.println(sql);
			int result = stt.executeUpdate(sql);
			
			return result;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		return 0;
	}
	/**
	 * search anything in Object give with contains String text
	 * @param text text for search
	 * @param obj Object to search
	 * @return ArrayList<ArrayList<String>>
	 */
	
	public ArrayList<ArrayList<String>> searchObject(String text,Object obj){
		
		String tableName = this.getTableName(this.getObjectClass(obj).toString());
		
		String sql = "SELECT * FROM "+PREFIX+tableName+" WHERE ";
		String fields[] = this.getField(obj);
		
		for (int i = 0; i < fields.length; i++) {
			sql += fields[i] + "like %"+text+"%";
			if(i != fields.length -1 )
				sql += " OR ";
		}
		ArrayList<ArrayList<String>>data = new ArrayList<>();
		try {
			Statement stt = this.connection.createStatement();
			stt.executeQuery(sql);
			ResultSet result = stt.getResultSet();
			ResultSetMetaData metadata = result.getMetaData();
			
			ArrayList<String> header = new ArrayList<>();
			for (int i = 0; i < metadata.getColumnCount(); i++) {
				header.add(metadata.getColumnName(i+1));
			}
			data.add(header);
			while(result.next()){
				ArrayList<String>row = new ArrayList<>();
				for (int i = 0; i < metadata.getColumnCount(); i++) {
					row.add(result.getString(i+1));
				}
				data.add(row);
			}
			
			return data;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	/**
	 * 
	 * @param obj
	 * @param fields
	 * @return
	 */
	
	public ArrayList<ArrayList<String>> searchObject(Object obj,String fields[]){
		
		ArrayList<ArrayList<String>>resultData = new ArrayList<>();
		try {
			Object data[];
			String field[];
			int index[] = new int[fields.length];
			field = this.getField(obj);
			int numfield = 0;
			for (int i = 0; i < fields.length; i++) {
				for (int j = 0; j < field.length; j++) {
					if(fields[i].equalsIgnoreCase(field[j])){
						index[numfield] = j;
						numfield++;
						break;
					}
				}
			}
			if(numfield == fields.length){
				data = this.getValue(obj);
				String tableName = this.getTableName(this.getObjectClass(obj).toString());
				
				String sql = "SELECT * FROM "+PREFIX+tableName+" WHERE ";
				for (int i = 0; i < index.length; i++) {
					sql += fields[i]+"='"+data[index[i]];
				}
				Statement stt = this.connection.createStatement();
				ResultSet result = stt.executeQuery(sql);
				ResultSetMetaData meta = result.getMetaData();
				while(result.next()){
					ArrayList<String>row = new ArrayList<>();
					for (int i = 0; i < meta.getColumnCount(); i++) {
						row.add(result.getString(i+1));
					}
					resultData.add(row);
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultData;
	}
	
	public String[] getField(Object obj){
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
		Driver dr = new Driver(1, "haha", "sdasdas", "1", new Date(), "");
		db.insertObject(dr);
		db.updateObject(dr);
		ArrayList<ArrayList<String>> data = db.getObject(dr, 1);
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).size(); j++) {
				System.out.println(data.get(i).get(j));
			}
		}
		
	}
*/	
	
	private String dateFormat(Date date){
		
		SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dfm.format(date);
	}
	
	
}
