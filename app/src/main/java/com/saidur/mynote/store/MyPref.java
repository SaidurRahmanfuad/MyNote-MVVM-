package com.saidur.mynote.store;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class MyPref {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int mood=0;
    public static final String Prefname="Prefname";
    public static String Style="style";
    public MyPref(Context context) {
        sharedPreferences = context.getSharedPreferences(Prefname,mood);
        this.context = context;
        editor=sharedPreferences.edit();
    }
    public void saveAction(String style){
        editor.putString(Style,style);
        editor.commit();
    }
    public HashMap<String,String> getStyle()
    {
        HashMap<String,String> Stylename=new HashMap<>();
        Stylename.put(Style,sharedPreferences.getString(Style,null));
        return Stylename;
    }
    public void cleare(){
        editor.clear();
        editor.commit();
    }
}
