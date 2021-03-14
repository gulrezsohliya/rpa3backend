package rpa.Services.Examination;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpa.Utility.UtilityInterface;

@Service("ExaminationService")
public class ExaminationService implements ExaminationServiceInterface {

	private static final Logger LOG = Logger.getLogger(ExaminationService.class.getName());

	@Autowired
	private UtilityInterface UI;

	/* List */
	@Override
	public List<Map<String, Object>> listQualificationsCategories() {
		List<Map<String, Object>> list = null;
		try {
			String sql = "SELECT * FROM MASTERS.qualificationscategories ORDER BY qualificationcategoryname";
			list = UI.listGeneric(sql);
		} catch (Exception e) {
			LOG.info("Error in listQualificationsCategories() :: " + e);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> listQualifications() {
		List<Map<String, Object>> list = null;
		try {
			String sql = "SELECT * FROM MASTERS.qualifications ORDER BY qualificationname";
			list = UI.listGeneric(sql);
			for (Map<String, Object> c : list) {
				sql = "SELECT * FROM MASTERS.qualificationscategories where qualificationcategorycode=?";
				c.put("qualificationscategory",
						(UI.listGeneric(sql, new Object[] { c.get("qualificationcategorycode") })).get(0));
			}
		} catch (Exception e) {
			LOG.info("Error in listQualifications() :: " + e);
		}
		return list;
	}

}
