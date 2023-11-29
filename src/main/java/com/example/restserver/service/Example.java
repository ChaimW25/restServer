package com.example.restserver.service;

import java.util.Map;

public class Example {

    Map result;
    String help;

    public Map getResult(){
        return result;
    }
    public String getHelp(){
        return help;
    }
    public void setResult(Map res){
        this.result=res;
    }
    public void setHelp(String help){
        this.help= help;
    }
}
