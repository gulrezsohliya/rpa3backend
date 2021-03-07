package rpa.utility;

import java.util.List;

public interface UtilityInterface {
	
	public Integer getMax(String schema, String table,String column);
	
	public <T> List<T> listGeneric(Class<T> clazz, String sql);
	
	public <T> List<T> listGeneric(Class<T> clazz, String sql, Object[] params);
	
	public <T> boolean update(String tablename,String sql, Object[] params);
	
}
