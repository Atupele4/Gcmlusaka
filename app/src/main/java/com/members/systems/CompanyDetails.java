package com.members.systems;

/**
 * Created by mboyaa on 11/20/2015.
 */
public class CompanyDetails {

    String CompanyName_;
    String CompanyID_;
    String CompanyDescription_;
    String CompanyImageID_;

//Sets
    public void SetCompanyName(String companyName){
        this.CompanyName_ = companyName;
    }

    public void SetCompanyID(String companyID){
        this.CompanyID_ = companyID;
    }

    public void SetCompanyDescription(String companyDescription){
        this.CompanyDescription_ = companyDescription;
    }

    public void SetCompanyDetails(CompanyDetails companyDetails){
//        CompanyDetails_ = companyDetails;
    }

    public void SetCompanyImageID(String companyImageID){
        this.CompanyImageID_ = companyImageID;
    }

//    Gets....
    public String GetCompanyName(){
        return this.CompanyID_;
    }

    public String GetCompanyID(){
        return this.CompanyDescription_;
    }

    public String GetCompanyDescription(){
        return this.CompanyID_;
    }

//    public CompanyDetails GetCompanyDetails(){
//        return this.CompanyDetails_;
//    }

    public String GetCompanyImageID() {return this.CompanyImageID_; }
}
