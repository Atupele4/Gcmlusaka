package com.mboyaa.gcmlusaka;

public class Messages {

    //private variables
    int _id;
    String _com_id;
    String _messagee;
    String _dateTime;

    // Empty constructor
    public Messages(){

    }
    // constructor
    public Messages(int id, String com_id, String message,String dateTime){
        this._id = id;
        this._com_id = com_id;
        this._messagee = message;
        this._dateTime = dateTime;
    }

    // constructor
    public Messages(String com_id, String message,String Datetime){
        this._com_id = com_id;
        this._messagee = message;
        this._dateTime = Datetime;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting Com_ID
    public String get_com_id(){
        return this._com_id;
    }

    // setting Com_ID
    public void set_com_id(String com_id){
        this._com_id = com_id;
    }

    // getting Message
    public String get_message(){
        return this._messagee;
    }

    // setting Message
    public void set_message(String message){
        this._messagee = message;
    }


    //getting datetime
    public String get_dateTime(){
        return this._dateTime;
    }


    // setting Message
    public void set_datetime(String datetime){
        this._dateTime = datetime;
    }
}
