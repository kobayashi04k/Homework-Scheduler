package com.example.kobay.homeworkschedule;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private List<String> classes = new ArrayList<String>();
    private String timeHome;

    public Person(List<String> tempClasses, String tempTime){
        //name = tempName;
        for(String x : tempClasses){
            classes.add(x);
        }
        timeHome = tempTime;
    }


    //returns the person's name
    public String getName(){
        return name;
    }

    //returns the person's classes
    public List<String> getClasses(){
        return classes;
    }

    //returns the time the person reaches home
    public String getTimeHome(){
        return timeHome;
    }

    //returns a user's specific class
    public String getClass(int classNum){
        return classes.get(classNum);
    }
}
