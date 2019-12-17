package com.tests.constant;

public enum Constant {
    USERNAME("username"), PASSWORD("password"),

    PERSONNUM("personnum"), FULLNAME("fullname"),

    FORMAT_HYPEN_YYYY_MM_DD("yyyy-MM-dd"),

    DATAFILE_BASE_PATH("dataseeding/csv"), SCHEMA_BASE_PATH("dataseeding/schema"), UPGRADE_ALL("UPGRADE_ALL"), TENANT(
        "Tenant"), CLASS_NAME("CLASS_NAME"),
RETAIL("retail"),

    UPDATE_DB("UpdateDB"), INCREMENT_PATH("IncrementPath"),

    FORWARD_SLASH("/"), EMPTY(""),

    CSV(".csv"), JSON(".json"),

     PARENT("Parent"), NAME("name"), EVENT_NAME("eventName"), PERSON_NUM("person_number"), TIMEFRAME_ID(
        "timeframe_id"), ID("id"), PROPVALUE("propertyValue"), FORCASTING_DEPT_NAME("genericDepartment-qualifier"),LABORCATEGORYID("laborCategory-id"),
/////////////////////
    
    KEY("Key"), 
    FIRSTNAME("customerfirstname"), LASTNAME("customerlastname"), EMAIL("email"), 
    COMPANY("companyDetails"), ADDRESS1("addresspoint1"), ADDRESS2("addresspoint2"),CITY("citydetails"),
    STATE("statedetails"),ADDITIONAL_INFO("additionalinfo"),PHONE("phone"),MOBILE("mobile"),ALIAS("alias"),
    POSTCODE("postcode"),LOGINHEADER("headerverification"),DRESS_NAME("dress"),COLOR("color"),SIZE("size"),
    
    
    
    
    
    
    
    
    
//////////////////    
    // Org Map Constants

    PARENTNODEREFQUALIFIER("parentNodeRef-qualifier"), CONTEXTREFQUALIFIER("contextRef-qualifier"),

    ATTHERATE2("@@"),

    LEGACY_API("LEGACY_API"), NEW_API("NEW_API"),
    //Login Credentials for every Person
    PERSON_CSV_PATH("data/common/masterlist/person.csv"),

    DATEFORMAT_HYPEN("DATEFORMAT_HYPEN"), DATEFORMAT_FORWARD_SLASH("DATEFORMAT_FORWARD_SLASH"),

    CURRENTYEAR("currentYear"), CURRENTMONTH("currentMonth"), CURRENTDATE("currentDate"),

    // DB Constants
    DBSERVER("DBServer"), DBPORT("DBPort"), DBNAME("DBName"), DBUSERNAME("DBUsername"), DBPASSWORD(
        "DBPassword"), TMSDBSERVER("TMS_DB_SERVER"), TMSDBSID("TMS_DB_SID"),

    
    DAY("day"), MONTH("month"), YEAR("year"),

    // Persona Constant
    CLOUDADMINSITRATOR("CloudAdminstrator"), CXSYSADMIN("CXSysAdmin"), SYSTEMADMINISTRATOR(
        "SystemAdministrator"), ADMINMANAGER("AdminManager"), XMLUser("XMLUser"), OLIVIAMARTIN("OliviaMartin"),;

    private final String value;

    private Constant(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}