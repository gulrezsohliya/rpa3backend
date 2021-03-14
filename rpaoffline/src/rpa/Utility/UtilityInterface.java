package rpa.Utility;

import java.util.List;
import java.util.Map;

public interface UtilityInterface {

	public Integer getMax(String schema, String table, String column);

	public <T> List<T> listGeneric(Class<T> clazz, String sql);

	public <T> List<T> listGeneric(Class<T> clazz, String sql, Object[] params);

	public List<Map<String, Object>> listGeneric(String sql);

	public List<Map<String, Object>> listGeneric(String sql, Object[] params);

	public <T> boolean update(String tablename, String sql, Object[] params);

}
