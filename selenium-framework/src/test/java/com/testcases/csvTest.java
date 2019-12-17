package com.testcases;


import com.tests.constant.Constant;
import com.tests.utils.CSVReader;

public class csvTest
{

    public static void main(String[]args)
    {
        csvTest t1 = new csvTest();
        String path = t1.getDataPath("TagDefinition", "");
        System.out.println("-------");
        System.out.println(path);
        String path2 = "E:\\MyProjects\\seleniumFramework\\selenium-framework\\src\\main\\resources\\dataseeding\\csv\\";
        System.out.println("---------");
        System.out.println(CSVReader.readPayloadTemplate(path));
        
    }
    
    public String getDataPath(String dataseedingSchemaDataFileName, String dataFolder)
    {
        /*
        String absolutePath = Constant.DATAFILE_BASE_PATH.getValue() + Constant.FORWARD_SLASH.getValue() + dataFolder
            + Constant.FORWARD_SLASH.getValue() + dataseedingSchemaDataFileName + Constant.FORWARD_SLASH.getValue()
            + dataseedingSchemaDataFileName + Constant.CSV.getValue();
        return absolutePath;
        */
        String absolutePath = Constant.DATAFILE_BASE_PATH.getValue() + Constant.FORWARD_SLASH.getValue() + dataseedingSchemaDataFileName + Constant.FORWARD_SLASH.getValue()
            + dataseedingSchemaDataFileName + Constant.CSV.getValue();
        return absolutePath;
    }
}
