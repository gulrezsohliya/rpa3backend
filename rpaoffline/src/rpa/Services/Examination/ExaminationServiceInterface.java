package rpa.Services.Examination;

import java.util.List;
import java.util.Map;

public interface ExaminationServiceInterface {

	public List<Map<String, Object>> listQualificationsCategories();

	public List<Map<String,Object>> listQualifications();

}
