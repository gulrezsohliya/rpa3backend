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
function Userlogins() {
    var Userlogins = {
        "officername": "",
        "designation": new Designation(),
//        "designationid": 0,
//        "designationname": "",
        "usercode": 0,
        "username": "",
        "password": "",
        "userrole": "U",
        "enabled": "Y",
        "officecode": 0,
        "officename": "",
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
function Finyear() {
    var Finyear = {
        "finyear": "",
        "description": ""
    };
    return Finyear;
}
////////////////////////////////////////////////////////////////////////////////////
function Quarter() {
    var Quarter = {
        "accmid": 0,
        "accmbungname": "",
        "accmlocality": "",
        "accmstreet": "",
        "accmbuildingno": "",
        "accmflatno": "",
        "accmpin": "",
        "taluka": new Taluka(),
        "townVillage": new TownVillage(),
        "vacantflag": 0,
        "location" : "",
        "quartertype": new QuarterType(),
        "categorycode": null
    };
    return Quarter;
}
var quarterTypeList = ['A', 'B', 'C', 'D', 'E', 'F', 'G'];

var categoryList = [
    {
        "key": '',
        "desc": ""
    },
    {
        "key": 'D',
        "desc": "Class I Officer\'s Quarter"
    },
    {
        "key": 'B',
        "desc": "Commissioner\'s and Secretary\'s Residence"
    },
    {
        "key": 'G',
        "desc": "Grade IV Staff Quarter"
    },
    {
        "key": 'A',
        "desc": "Ministers/Leader of Opposition/Chairman etc at Government Bungalows under GAD"
    },
    {
        "key": 'F',
        "desc": "Non-Residential Bungalow"
    },
    {
        "key": 'C',
        "desc": "Senior Class -  I Officer\'s Quarter"
    },
    {
        "key": 'E',
        "desc": "Staff Quarter"
    }
];
var quarterStatus = [
    {
        "key": 1,
        "desc": "Occupied"
    },
    {
        "key": 2,
        "desc": "Vaccant"
    },
    {
        "key": 3,
        "desc": "Demolished"
    }
];
var quarterLoc = [
    {
        "key": "C",
        "desc": "Centre"
    },
    {
        "key": "E",
        "desc": "East"
    },
    {
        "key": "N",
        "desc": "North"
    },
    {
        "key": "NE",
        "desc": "North East"
    },
    {
        "key": "NW",
        "desc": "North West"
    },
    {
        "key": "S",
        "desc": "South"
    },
    
    {
        "key": "SW",
        "desc": "South West"
    },
    {
        "key": "SE",
        "desc": "South East"
    },
    {
        "key": "W",
        "desc": "West"
    }
    
];
var salutationList = ["","Dr","Shri","Smti", "Kumari"];
//function Occupant() {
//    var Occupant = {
//        "occupantid": 0,
//        "occupantname": "",
//        "designation": "",
//        "gender": "",
//        "maritalstatuscode": "",
//        "department": new Department(),
//        "jobtypecode": 0,
//        "basicpay": "",
//        "paymatrix": "",
//        "dateofjoining": "",
//        "dateofretirement": "",
//    };
//    return Occupant;
//}
function ApplicationDetails() {
    var ApplicationDetails = {
        "applicantid": 0,
        "salutation": "",
        "applicantname": "",
        "gender": "",
        "maritalstatuscode": "",
        "designation": new Designation(),
        "jobtypecode": 0,
        "basicpay": "",
        "paymatrix": "",
        "dateofjoining": "",
        "dateofretirement": "",
        "isaccomodated":2,
        "alloteddetails":"",
        "service":"",
        "iscentralgovernmentdeputation":2,
        "fromdeputationperiod":"",
        "todeputationperiod":"",
        "isdebarred":2,
        "debarreddate":"",
        "isownhouse":2,
        "housedetails":"",
        "istakenhousebuilding":2,
        "loanyear":"",
        "ishouseconstructed":2,
        "houselocation":"",
        "presentaddress":"",
        "ishasquarter":2,
        "notallotedreason":"",
        "forwardingOfficer": new ForwardingOfficer(),
//        "categorycode": null
    };
    return ApplicationDetails;
}
function Occupied_Quarters() {
    var Occupied_Quarters = {
        "occupied_quarterid": 0,
        "quarter": new Quarter(),
        "applicationDetails": new ApplicationDetails(),
        "allot_orderno": "",
        "dateofoccupying": "",
        "vacatedate": "",
        "allocationAssistant": "",
        "remarks":"",
        "allotmentdate":new Date(),
        "action":null
    };
    return Occupied_Quarters;
}

function Occupied_Quarters(occupied_quarterid, quarter) {
    var Occupied_Quarters = {
        "occupied_quarterid": occupied_quarterid,
        "quarter": quarter,
        "applicationDetails": new ApplicationDetails(),
        "allot_orderno": "",
        "dateofoccupying": "",
        "vacatedate": "",
        "allocationAssistant": "",
        "remarks":"",
        "allotmentdate":"",
//        "allotmentdate":new Date(),
        "action":null
    };
    return Occupied_Quarters;
}
function Department() {
    var Department = {
        "departmentcode": 0,
        "departmentname": "",
    };
    return Department;
}
function Departments() {
    var Departments = {
        "departmentid": -1,
        "departmentcode": "",
        "departmentname": ""
    };
    return Departments;
}
function Designation() {
    var Designation = {
        "designationid": 0,
        "designationname": "",
        "departments":new Departments(),
        "cdr_grp_id": "",
    };
    return Designation;
}
function Designationgroup() {
    var Designationgroup = {
        "cdr_grp_id": "",
        "description": ""
    };
    return Designationgroup;
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

function District() {
    var District = {
        "districtcode": 6,
        "districtname": "East Khasi Hills",
        "lgdcode": 274,
        "state": new State()
    };
    return District;
}

function TownVillage() {
    var TownVillage = {
//        "townid": 0,
//        "towncode": 0,
//        "townname": "",
//        "District": new District()
        "townid": 67,
        "towncode": 40603000,
        "townname": "Shillong",
        "district": new District()
    };
    return TownVillage;
}

function Taluka() {
    var Taluka = {
//        "talukaid": 0,
//        "talukacode": 0,
//        "talukaname": "",
//        "District": new District()
        "talukaid": 53,
        "talukacode": 2,
        "talukaname": "Mylliem",
        "district": new District()
    };
    return Taluka;
}

function QuarterType() {
    var QuarterType = {
        "codevalue": 0,
        "codetext": "",
        "codetype": ""
    };
    return QuarterType;
}

function Custodian() {
    var Custodian = {
        "custid": 0,
        "custpermddoid": 0,
        "custdept": "",
        "custdesc": "",
        "custreceiptshcemecode": ""
    };
    return Custodian;
}

function QuartersRent() {
    var QuartersRent = {
        "quarters": new Quarter(),
        "quarterType": new QuarterType(),
        "custodian": new Custodian(),
        "quarterArea": 0.0,
        "quarterRent": 0.0,
        "quarterAddRent1": 0.0,
        "quarterAddRent2": 0.0,
        "quarterGarageRent": 0.0,
        "quarterServiceRent": 0.0
    };
    return QuartersRent;
}

function ForwardingOfficer() {
    var ForwardingOfficer = {
        "applicantid": ApplicationDetails.applicantid,
        "designation": new Designation(),
        "officername": "",
        "mobileno": "",
        "endorsementno": "",
        "endorsementdate": ""
    };
    return ForwardingOfficer;
}

function JobType() {
    var JobType = {
        "jobtypecode": null,
        "description": ""
    };
    return JobType;
}