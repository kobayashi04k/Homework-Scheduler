package com.example.kobay.homeworkschedule;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.ListActivity;
import android.content.Context;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    String File_Name = "Save.txt";
    String File_Class_Name = "Classes.txt";
//    FileOutputStream fOut = openFileOutput("Save.txt", Context.MODE_PRIVATE);

    //private val file = File(context.filesDir, filename);

    List<String> classes = new ArrayList<String>();
    Person student;
    private EditText inputClass;
    private EditText inputTime;
    String timeHome = "0";

    //EditText editText = (EditText)findViewById(R.id.editText6);
    //EditText timeText = (EditText)findViewById(R.id.editText7);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayListView();
        inputClass = findViewById(R.id.classInput);
        inputTime = findViewById(R.id.timeInput);

        loadClasses();
        loadTimeHome();
        displayListView();

        //Intent create =  new Intent(this, Create.class);
        //startActivity(create);

    }

    public void saveTimeHome(){
        //String message = student.getTimeHome();

        FileOutputStream fOut = null;


        try {
            fOut = openFileOutput(File_Name, Context.MODE_PRIVATE);
            fOut.write(timeHome.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fOut != null){
                try {
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveClasses(){

        FileOutputStream fOut = null;

        try {

            fOut = openFileOutput(File_Class_Name, Context.MODE_PRIVATE);

            for(int i = 0; i < classes.size(); i++){
                fOut.write(classes.get(i).getBytes());
                if(i < classes.size())
                fOut.write("\n".getBytes());

            }
            //Toast.makeText(this,counter+"",Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fOut != null){
                try {
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadTimeHome(){
        FileInputStream fIn = null;

        try {
            fIn = openFileInput(File_Name);
            InputStreamReader fReader = new InputStreamReader(fIn);
            BufferedReader br = new BufferedReader(fReader);
            //StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine())!=null){
                //classes.add(text);
                //sb.append(text).append("\n");
                timeHome = text;

            }

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fIn != null){
                try {
                    fIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadClasses(){
        FileInputStream fIn = null;
        //int counter = 0;

        try {
            fIn = openFileInput(File_Class_Name);
            InputStreamReader fReader = new InputStreamReader(fIn);
            BufferedReader br = new BufferedReader(fReader);
            //StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine())!= null){
                //classes.add(text + "\n");
                classes.add(text);
                //counter++;
                //System.out.println(sb.toString());
            }

            //Toast.makeText(this,sb.toString(),Toast.LENGTH_LONG).show();
            //Toast.makeText(this,counter+"",Toast.LENGTH_LONG).show();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fIn != null){
                try {
                    fIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //displays the list view
    public void displayListView(){
        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,classes);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    //makes a person object after clicking done
    public void finishSchedule(View view){
        student = new Person(classes,timeHome);
        saveTimeHome();
        saveClasses();
    }


    //sets the time
    public void setTime(View view){
         timeHome = inputTime.getText().toString();
         inputTime.setText("");
    }

    //adds the class to the list view
    public void addClass(View view){
        classes.add(inputClass.getText().toString());
        inputClass.setText("");
        displayListView();
    }

}
