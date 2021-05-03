package rpa.Services.Examination;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpa.Models.Examination.Advertisement;
import rpa.Models.Examination.AdvertisementAgeRelax;
import rpa.Models.Examination.AdvertisementAges;
import rpa.Models.Examination.AdvertisementFees;
import rpa.Models.Examination.AdvertisementFeesRelax;
import rpa.Models.Examination.AdvertisementOptionals;
import rpa.Models.Examination.ExamSubjects;
import rpa.Models.Examination.OfficeCenter;
import rpa.Models.Examination.OptionalSubjects;
import rpa.Utility.UtilityInterface;

@Service("ExaminationService")
public class ExaminationService implements ExaminationServiceInterface {

	private static final Logger LOG = Logger.getLogger(ExaminationService.class.getName());

	@Autowired
	private UtilityInterface UI;

	/* List */
	@Override
	public List<Advertisement> listAdvertisements(Integer officecode, Integer usercode) {
		List<Advertisement> oth = null;
		List<Advertisement> advs = new ArrayList<Advertisement>();
		List<AdvertisementAges> ages = null;
		List<AdvertisementAgeRelax> ageRelax = null;
		List<AdvertisementFees> fees = null;
		List<AdvertisementFeesRelax> feeRelax = null;
		List<AdvertisementOptionals> optionals = null, tempopt = new ArrayList<AdvertisementOptionals>();
		String agesSQL = "SELECT * FROM backend.advertisementsages where adcode=? ";
		String ageRelaxSQL = "SELECT * FROM backend.advertisementsagesrelax where adcode=? ";
		String feesSQL = "SELECT * FROM backend.advertisementsfees where adcode=? ";
		String feeRelaxSQL = "SELECT * FROM backend.advertisementsfeesrelax where adcode=? ";
		String optionalsSQL = "SELECT * FROM backend.advertisementsoptionals where adcode=? order by optionalcode ";
		String sql = "SELECT * FROM BACKEND.advertisements WHERE CASE WHEN ? =1 then 1=1 else officecode=? end "
				+ "ORDER BY issuedate DESC";
		oth = UI.listGeneric(Advertisement.class, sql, new Object[] { usercode, officecode });
		if (oth.size() > 0) {
			try {
				for (Advertisement adv : oth) {
					// Ages
					ages = UI.listGeneric(AdvertisementAges.class, agesSQL, new Object[] { adv.getAdcode() });
					if (ages.size() == 0) {
						ages.add(new AdvertisementAges());
					}
					adv.setAdvertisementAge(ages);

					// Fees
					fees = UI.listGeneric(AdvertisementFees.class, feesSQL, new Object[] { adv.getAdcode() });
					if (fees.size() == 0) {
						fees.add(new AdvertisementFees());
					}
					adv.setAdvertisementFee(fees);

					// AgeRelax
					ageRelax = UI.listGeneric(AdvertisementAgeRelax.class, ageRelaxSQL,
							new Object[] { adv.getAdcode() });
					if (ageRelax.size() == 0) {
						ageRelax.add(new AdvertisementAgeRelax());
					}
					adv.setAdvertisementAgeRelax(ageRelax.get(0));

					// FeeRelax
					feeRelax = UI.listGeneric(AdvertisementFeesRelax.class, feeRelaxSQL,
							new Object[] { adv.getAdcode() });
					if (feeRelax.size() == 0) {
						feeRelax.add(new AdvertisementFeesRelax());
					}
					adv.setAdvertisementFeeRelax(feeRelax.get(0));

					// OptionalSubjects
					optionals = UI.listGeneric(AdvertisementOptionals.class, optionalsSQL,
							new Object[] { adv.getAdcode() });
					if (optionals.size() == 0) {
						optionals.add(new AdvertisementOptionals(1));
						optionals.add(new AdvertisementOptionals(2));
						optionals.add(new AdvertisementOptionals(3));
					} else {
						HashMap<String, Object> os = null;
						int i = 1;
						AdvertisementOptionals temp = new AdvertisementOptionals(i, adv.getAdcode());
						for (AdvertisementOptionals o : optionals) {
							if (i == o.getOptionalcode()) {
								temp.setAdcode(o.getAdcode());
							} else {
								i++;
								tempopt.add(temp);
								temp = new AdvertisementOptionals(i, adv.getAdcode());
								temp.setAdcode(o.getAdcode());
							}
							os = new HashMap<String, Object>();
							os.put("optionalsubjectcode", o.getOptionalsubjectcode());
							temp.getOptionalsubject().add(os);
						}
						tempopt.add(temp);
						optionals = tempopt;
					}
					adv.setAdvertisementOptionals(optionals);

					advs.add(adv);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return advs;
	}

	@Override
	public List<OptionalSubjects> listOptionalSubjects() {
		List<OptionalSubjects> oth = null;

		String sql = "SELECT * FROM MASTERS.optionalsubjects ORDER BY optionalsubjectname";
		oth = UI.listGeneric(OptionalSubjects.class, sql);
		return oth;
	}

	@Override
	public List<ExamSubjects> listExamSubjects() {
		List<ExamSubjects> oth = null;

		String sql = "SELECT * FROM MASTERS.examinationsubjects ORDER BY examinationsubjectname";
		oth = UI.listGeneric(ExamSubjects.class, sql);
		return oth;
	}

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

	// Exam Subject
	@Override
	public String createExamSubject(ExamSubjects subject) {

		String sql = "SELECT * FROM masters.examinationsubjects WHERE examinationsubjectname =? ";
		if ((UI.listGeneric(ExamSubjects.class, sql, new Object[] { subject.getExaminationsubjectname() }))
				.size() > 0) {
			return "EXISTS";
		}
		sql = "INSERT INTO masters.examinationsubjects(examinationsubjectcode,examinationsubjectname,description)VALUES (?, ?, ?)";
		Object[] params = new Object[] { UI.getMax("masters", "examinationsubjects", "examinationsubjectcode") + 1,
				subject.getExaminationsubjectname(), subject.getDescription() };
		return (UI.update("masters.examinationsubjects", sql, params)) ? "CREATED" : "FAILED";
	}

	@Override
	public boolean updateExamSubject(ExamSubjects subject) {
		String sql = "UPDATE masters.examinationsubjects SET examinationsubjectname=?, description=? WHERE  examinationsubjectcode=? ";
		Object[] params = new Object[] { subject.getExaminationsubjectname(), subject.getDescription(),
				subject.getExaminationsubjectcode() };
		return UI.update("masters.examinationsubjects", sql, params);
	}

	@Override
	public boolean deleteExamSubject(Integer examinationsubjectcode) {

		String sql = "Delete from masters.examinationsubjects where examinationsubjectcode=? ";
		return UI.update("masters.examinationsubjects", sql, new Object[] { examinationsubjectcode });
	}

	// Optional Subject
	@Override
	public String createOptionalSubject(OptionalSubjects subject) {

		String sql = "SELECT * FROM masters.Optionalsubjects WHERE subjectname =? ";
		if ((UI.listGeneric(OptionalSubjects.class, sql, new Object[] { subject.getOptionalsubjectname() }))
				.size() > 0) {
			return "EXISTS";
		}
		sql = "INSERT INTO masters.Optionalsubjects (optionalsubjectcode,optionalsubjectname)VALUES (?, ?)";
		Object[] params = new Object[] { UI.getMax("masters", "Optionalsubjects", "optionalsubjectcode") + 1,
				subject.getOptionalsubjectname() };
		return (UI.update("masters.Optionalsubjects", sql, params)) ? "CREATED" : "FAILED";
	}

	@Override
	public boolean updateOptionalSubject(OptionalSubjects subject) {
		String sql = "UPDATE masters.Optionalsubjects SET optionalsubjectname=? WHERE  optionalsubjectcode=? ";
		Object[] params = new Object[] { subject.getOptionalsubjectname(), subject.getOptionalsubjectcode() };
		return UI.update("masters.Optionalsubjects", sql, params);
	}

	@Override
	public boolean deleteOptionalSubject(Integer subjectcode) {

		String sql = "Delete from masters.Optionalsubjects where optionalsubjectcode=? ";
		return UI.update("masters.Optionalsubjects", sql, new Object[] { subjectcode });
	}

	// Advertisement
	@Override
	public String createAdvertisement(Advertisement obj) {

		String sql = "SELECT * FROM backend.Advertisements WHERE advertisementno =? ";
		if ((UI.listGeneric(Advertisement.class, sql, new Object[] { obj.getAdvertisementno() })).size() > 0) {
			return "EXISTS";
		}
		sql = "INSERT INTO backend.Advertisements ("
				+ "            slno, adcode, officecode, nameofpost, postshortname, issuedate, "
				+ "            lastdate, agedate, description, counterentry, open, "
				+ "            noofoptionals, usercode, entrydate, finalized,  advertisementno,  "
				+ "            examinationmodecode)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Integer slno = UI.getMax("backend", "Advertisements", "slno","officecode",obj.getOfficecode()) + 1;
		String adcode = ((obj.getOfficecode().toString().length() == 1) ? "00"
				: (obj.getOfficecode().toString().length() == 2) ? "0" : "") + obj.getOfficecode()
			+ ((slno.toString().length() == 1) ? "00" 
				: (slno.toString().length() == 2) ? "0" : "") + slno;//6 Characters adcode 3 of officecode +3 of slno
		Object[] params = new Object[] { slno, adcode, obj.getOfficecode(), obj.getNameofpost(), obj.getPostshortname(),
				obj.getIssuedate(), obj.getLastdate(), obj.getAgedate(), obj.getDescription(), obj.getCounterentry(),
				obj.getOpen(), obj.getNoofoptionals(), obj.getUsercode(), new Date(), obj.getFinalized(),
				obj.getAdvertisementno(), obj.getExaminationmodecode() };
		return (UI.update("backend.Advertisements", sql, params)) ? (adcode + 1) + "" : "FAILED";
	}

	@Override
	public boolean updateAdvertisement(Advertisement obj) {
		try {
			String sqlDelete = "";
			String sql = "UPDATE backend.Advertisements SET nameofpost=?, postshortname=?, issuedate=?, "
					+ "		lastdate=?, agedate=?, description=?, counterentry=?, open=?, "
					+ "		noofoptionals=?, usercode=?, entrydate=?, finalized=?,  advertisementno=?, examinationmodecode=? "
					+ "	WHERE  adcode=? ";
			Object[] params = new Object[] { obj.getNameofpost(), obj.getPostshortname(), obj.getIssuedate(),
					obj.getLastdate(), obj.getAgedate(), obj.getDescription(), obj.getCounterentry(), obj.getOpen(),
					obj.getNoofoptionals(), obj.getUsercode(), obj.getEntrydate(), obj.getFinalized(),
					obj.getAdvertisementno(), obj.getExaminationmodecode(), obj.getAdcode() };
			if (UI.update("backend.Advertisements", sql, params)) {

////////////// Save AdvertisementsAgesRelax
//			if()
				sqlDelete = "DELETE FROM backend.advertisementsagesrelax WHERE adcode=?";
				sql = "INSERT INTO backend.advertisementsagesrelax(slno, adcode, pwdadditionalage,  "
						+ "		womanadditionalage, exservicemenadditionalage,entrydate) "
						+ "    VALUES (?, ?, ?, ?, ?, ?)";
				UI.update("backend.advertisementsagesrelax", sqlDelete, new Object[] { obj.getAdcode() });
				if (!UI.update("backend.advertisementsagesrelax", sql,
						new Object[] { UI.getMax("backend", "advertisementsagesrelax", "slno") + 1, obj.getAdcode(),
								obj.getAdvertisementAgeRelax().getPwdadditionalage(),
								obj.getAdvertisementAgeRelax().getWomanadditionalage(),
								obj.getAdvertisementAgeRelax().getExservicemenadditionalage(), new Date() })) {
					return false;
				}

////////////// Save AdvertisementsFeesRelax
				sqlDelete = "DELETE FROM backend.advertisementsfeesrelax WHERE adcode=?";
				sql = "INSERT INTO backend.advertisementsfeesrelax("
						+ "            slno, adcode, pwdfees, womanfees, exservicemenfees, entrydate)"
						+ "    VALUES (?, ?, ?, ?, ?, ?)";
				UI.update("backend.advertisementsfeesrelax", sqlDelete, new Object[] { obj.getAdcode() });
				if (!UI.update("backend.advertisementsfeesrelax", sql,
						new Object[] { UI.getMax("backend", "advertisementsfeesrelax", "slno") + 1, obj.getAdcode(),
								obj.getAdvertisementFeeRelax().getPwdfees(),
								obj.getAdvertisementFeeRelax().getWomanfees(),
								obj.getAdvertisementFeeRelax().getExservicemenfees(), new Date() })) {
					return false;
				}

//////////////Save AdvertisementsAges
				sqlDelete = "DELETE FROM backend.advertisementsages WHERE adcode=?";
				sql = "INSERT INTO backend.advertisementsages( "
						+ "            slno, adcode, categorycode, minage, maxage, entrydate) "
						+ "    VALUES (?, ?, ?, ?, ?, ?); ";
				UI.update("backend.advertisementsages", sqlDelete, new Object[] { obj.getAdcode() });
				Integer max = UI.getMax("backend", "advertisementsages", "slno");
				for (AdvertisementAges age : obj.getAdvertisementAge()) {
					if (!UI.update("backend.advertisementsages", sql, new Object[] { ++max, obj.getAdcode(),
							age.getCategorycode(), age.getMinage(), age.getMaxage(), new Date() })) {
						return false;
					}
				}

//////////////Save AdvertisementsFees
				sqlDelete = "DELETE FROM backend.advertisementsfees WHERE adcode=?";
				sql = "INSERT INTO backend.advertisementsfees( slno, adcode, categorycode, feeamount, entrydate) "
						+ "VALUES (?, ?, ?, ?, ?);";
				UI.update("backend.advertisementsfees", sqlDelete, new Object[] { obj.getAdcode() });
				max = UI.getMax("backend", "advertisementsfees", "slno");
				for (AdvertisementFees fees : obj.getAdvertisementFee()) {
					if (!UI.update("backend.advertisementsfees", sql, new Object[] { ++max, obj.getAdcode(),
							fees.getCategorycode(), fees.getFeeamount(), new Date() })) {
						return false;
					}
				}

//////////////Save AdvertisementsOptionals
				sqlDelete = "DELETE FROM backend.advertisementsoptionals WHERE adcode=?";
				sql = "INSERT INTO backend.advertisementsoptionals( adcode, optionalcode,optionalsubjectcode ) "
						+ "VALUES (?, ?, ?);";
				UI.update("backend.advertisementsoptionals", sqlDelete, new Object[] { obj.getAdcode() });
				for (AdvertisementOptionals opt : obj.getAdvertisementOptionals()) {
					for (Map<String, Object> subject : opt.getOptionalsubject()) {
						if (!UI.update("backend.advertisementsoptionals", sql, new Object[] { obj.getAdcode(),
								opt.getOptionalcode(), subject.get("optionalsubjectcode") })) {
							return false;
						}
					}
				}

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error in updateAdvertisement(Advertisement obj) ::" + e);
			return false;
		}
	}

	@Override
	public boolean deleteAdvertisement(Integer code) {

		String sql = "Delete from backend.Advertisements where adcode=? ";
		return UI.update("backend.Advertisements", sql, new Object[] { code });
	}

}
