var Advertisement = function() {
	return {
		officecode : 0,
		slno : 0,
		adcode : '',
		nameofpost : '',
		postshortname : "",
		issuedate : 0,
		lastdate : 0,
		agedate : 0,
		description : "",
		counterentry : "Y",
		open : "Y",
		noofoptionals : 0,
		usercode : 0,
		entrydate : 0,
		finalized : "Y",
		feetype : 0,
		advertisementno : "",
		examinationmodecode : 0,
		advertisementAge : [ new AdvertisementAge() ],
		advertisementAgeRelax : {
			slno : 0,
			adcode : '',
			pwdadditionalage : 0,
			womanadditionalage : 0,
			exservicemenadditionalage : 0,
			entrydate : 0
		},
		advertisementFee : [ new AdvertisementFee() ],
		advertisementFeeRelax : {
			slno : 0,
			adcode : '',
			pwdfees : 0,
			womanfees : 0,
			exservicemenfees : 0,
			entrydate : 0
		},
		advertisementOptionals : [ new AdvertisementOptionals(1),
				new AdvertisementOptionals(2), new AdvertisementOptionals(3), ]
	};
}

var AdvertisementOptionals = function(optional_code) {
	return {
		adcode : '',
		optionalcode : optional_code,
		optionalsubject : [{"optionalsubjectcode":0,"optionalsubjectname":"Not Applicable"}]
	};
}
var AdvertisementAge = function() {
	return {
		slno : 0,
		adcode : '',
		categorycode : '',
		minage : 0,
		maxage : 0,
		entrydate : 0
	};
}

var AdvertisementFee = function() {
	return {
		slno : 0,
		adcode : '',
		categorycode : '',
		feeamount : 0,
		entrydate : 0
	};
}

var OptionalSubjects = function() {
	return {
		optionalsubjectcode : 0,
		optionalsubjectname : ""
	};
}

var ExamSubjects = function() {
	return {
		examinationsubjectcode : 0,
		examinationsubjectname : "",
		description : ""
	};
}

var OtherCategories = function() {
	return {
		othercategorycode : 0,
		othercategorydescription : "",
		enabled : "Y"
	};
}

var ExamCenter = function() {

	var ExamCenter = {
		centercode : 0,
		centername : "",
	};

	return ExamCenter;
}
var Venue = function() {

	return {
		centercode : '',
		venuecode : 0,
		venuename : "",
	};
}
function District() {
	var District = {
		"districtcode" : 6,
		"districtname" : "East Khasi Hills",
		"lgdcode" : 274,
		"state" : new State()
	};
	return District;
}
var Office = function() {

	var Office = {
		officecode : 0,
		officename1 : '',
		officename2 : '',
		officename3 : '',
		officeshortname : '',
		signatoryname : '',
		signatorydesignation : '',
		emailid : '',
		emailidpassword : '',
		smsusername : '',
		smspassword : '',
		smssenderid : '',
		enabled : 'Y'
	};

	return Office;

}
var Cell = function() {

	return {
		cellcode : 0,
		officecode : '',
		celldescription : ''
	};
}
function Pageurls() {
	var Pageurls = {
		"urlcode" : 0,
		"pageurl" : "",
		"subsubmenu" : "",
		"subsubmenuicon" : "",
		"submenu" : "",
		"submenuicon" : "",
		"parent" : "",
		"parenticon" : "",
		"checked" : "",
		"ordering" : ""
	};
	return Pageurls;
}

function State() {
	var State = {
		"statecode" : 17,
		"statecodename" : "MG",
		"stateut" : 1,
		"statename" : "MEGHALAYA"
	};
	return State;
}

function TownVillage() {
	var TownVillage = {
		"townid" : 67,
		"towncode" : 40603000,
		"townname" : "Shillong",
		"district" : new District()
	};
	return TownVillage;
}
function Userlogins() {
	var Userlogins = {
		"cellcode" : 0,
		"designation" : "",
		"usercode" : -1,
		"username" : "",
		"passwords" : "",
		"entrydate" : new Date(),
		"enabled" : "Y",
		"mobileno" : 0,
		"fullname" : "",
	};
	return Userlogins;
}
function Userpages() {
	var Userpages = {
		"userpagecode" : 0,
		"usercode" : 0,
		"url" : new Pageurls()
	};
	return Userpages;
}
