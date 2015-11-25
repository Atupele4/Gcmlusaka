package com.members.systems;

/**
 * Created by mboyaa on 11/20/2015.
 */
public class CompanyContactDetails {


    String PhoneNumber;
    String Address;

    CompanyContactDetails(){
        String PhoneNumber = "";
        String Address = "";
    }

    CompanyContactDetails(String phone,String address){
        String PhoneNumber = phone;
        String Address = address;
    }

}
