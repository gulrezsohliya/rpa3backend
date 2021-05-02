package rpa.Services.Examination;

import java.util.List;
import java.util.Map;

import rpa.Models.Examination.Advertisement;
import rpa.Models.Examination.ExamSubjects;
import rpa.Models.Examination.OptionalSubjects;

public interface ExaminationServiceInterface {

	public List<Advertisement> listAdvertisements(Integer officecode, Integer usercode);

	public List<OptionalSubjects> listOptionalSubjects();

	public List<ExamSubjects> listExamSubjects();

	public List<Map<String, Object>> listQualificationsCategories();

	public List<Map<String, Object>> listQualifications();

	public String createExamSubject(ExamSubjects subject);

	public boolean updateExamSubject(ExamSubjects subject);

	public boolean deleteExamSubject(Integer code);

	public String createOptionalSubject(OptionalSubjects subject);

	public boolean updateOptionalSubject(OptionalSubjects subject);

	public boolean deleteOptionalSubject(Integer code);

	public String createAdvertisement(Advertisement subject);

	public boolean updateAdvertisement(Advertisement subject);

	public boolean deleteAdvertisement(Integer code);

}
