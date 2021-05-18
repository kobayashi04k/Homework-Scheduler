package com.example.kobay.homeworkschedule;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.HashMap;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Create extends Activity {

    String[] classes = {"Biology","History","Math","English"};
    String[] assignments = {"Evolution Worksheet","World War II Essay","Textbook Page 21-22 #3-18","Read The Odyssey: Chapter 2"};
    double[] assignmentTime = {.5,2,1,6};
    double[] actualAssignmentTime = {.25,3,1,1};
//    List<Integer> startingTimes = new ArrayList<Integer>();
//    List<Integer> endingTimes = new ArrayList<Integer>();
    int[] startingTimes = {270,0,0,0};
    int[] endingTimes = {0,0,0,0};
    List<String> scheduleSlots;
    String timeHome = "4:30";
    int startHour = 4;
    int startMinute = 30;
    int endHour;
    int endMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        createSchedule();
    }

    public void createSchedule(){
        createData();
        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,scheduleSlots);
        final ListView listView = (ListView)findViewById(R.id.listViewSchedule);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                listView.getChildAt(position).setEnabled(false);
                calculateTime(actualAssignmentTime[position]);
                createData();
            }
        });
    }

    public void createData(){
        scheduleSlots = new ArrayList<String>();
        for(int i = 0; i < classes.length; i++){
//            startingTimes[i] = (startHour * 60) + startMinute;
//            startHour = startingTimes[i]/60;
//            startMinute = startingTimes[i]%60;
            calculateTime(assignmentTime[i]);
            scheduleSlots.add(classes[i] + ": " + assignments[i] + "\n \n" + combineTime() + "\n");
            endingTimes[i] = (endHour * 60) + endMinute;
            shiftTime();
        }
    }
    public void updateData(){

    }

    public void calculateNewTime(double time, int position){

    }

    public String combineTime(){
        if(startMinute == 0 && endMinute == 0){
            return startHour + ":" + startMinute + "0 - " + endHour + ":" + endMinute + "0";
        }
        else if(startMinute == 0){
            return startHour + ":" + startMinute + "0 - " + endHour + ":" + endMinute;
        }
        else if(endMinute == 0){
            return startHour + ":" + startMinute + " - " + endHour + ":" + endMinute + "0";
        }
        return startHour + ":" + startMinute + " - " + endHour + ":" + endMinute;
    }

    public void calculateTime(double time){

        time = (int)(time*60);
        int totalMinutes = (startHour * 60) + startMinute;
        totalMinutes+=(time);
        if(totalMinutes >= 780){
            totalMinutes -= 720;
        }
        endHour = totalMinutes/60;
        endMinute = totalMinutes%60;

    }

    public void shiftTime(){
        startHour = endHour;
        startMinute = endMinute;
    }




}
