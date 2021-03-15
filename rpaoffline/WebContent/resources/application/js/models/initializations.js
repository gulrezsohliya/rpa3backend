var OtherCategories= function(){
	return {
		othercategorycode:0,
		othercategorydescription:"",
		enabled:"Y"
	};
}

var ExamCenter = function() {
	
	var ExamCenter = {
		centercode: 0,
		centername: "",
	};
	
	return ExamCenter;
}
var Venue = function() {
	
	return {
			centercode: 0,
			venuecode: 0,
			venuename: "",
	};
}
function District() {
    var District = {
        "districtcode": 6,
        "districtname": "East Khasi Hills",
        "lgdcode": 274,
        "state": new State()
    };
    return District;
}
var Office = function() {
	
	var Office = {
		officecode: 0, 
		officename1: '', 
		officename2: '', 
		officename3: '', 
		officeshortname: '', 
		signatoryname: '', 
		signatorydesignation: '', 
		emailid: '', 
		emailidpassword: '', 
		smsusername: '', 
		smspassword: '', 
		smssenderid: '', 
		enabled: 'Y' 
	};
	
	return Office;
	
}
var Cell = function() {
	
	return  {
		cellcode:0,
		officecode: '',
		celldescription:''			 
	};	
}
function Pageurls() {
    var Pageurls = {
        "urlcode": 0,
        "pageurl": "",
        "subsubmenu": "",
        "subsubmenuicon": "",
        "submenu": "",
        "submenuicon": "",
        "parent": "",
        "parenticon": "",
        "checked": "",
        "ordering": ""
    };
    return Pageurls;
}

function State() {
    var State = {
        "statecode": 17,
        "statecodename": "MG",
        "stateut": 1,
        "statename": "MEGHALAYA"
    };
    return State;
}

function TownVillage() {
    var TownVillage = {
        "townid": 67,
        "towncode": 40603000,
        "townname": "Shillong",
        "district": new District()
    };
    return TownVillage;
}
function Userlogins() {
    var Userlogins = {
        "cellcode": 0,
        "designation": "",
        "usercode": 0,
        "username": "",
        "passwords": "",
        "entrydate": new Date(),
        "enabled": "Y",
        "mobileno": 0,
        "fullname": "",
    };
    return Userlogins;
}
function Userpages() {
    var Userpages = {
        "userpagecode": 0,
        "usercode": 0,
        "url": new Pageurls()
    };
    return Userpages;
}
