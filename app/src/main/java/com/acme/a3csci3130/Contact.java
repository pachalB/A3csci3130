package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public  String uid;
    public  String businessNum;
    public  String name;
    public  String primaryBusiness;
    public  String address;
    public  String province;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * creates contact
     * @param uid
     * @param businessNum
     * @param name
     * @param primaryBusiness
     * @param address
     * @param province
     *
     */
    public Contact(String uid, String name, String businessNum, String primaryBusiness, String address, String province){
        this.uid = uid;
        this.name = name;
        this.businessNum = businessNum;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.province = province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("business number", businessNum);
        result.put("name", name);
        result.put("primary business", primaryBusiness);
        result.put("address", address);
        result.put("province/territory", province);

        return result;
    }
}
