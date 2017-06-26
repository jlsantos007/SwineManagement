package com.csthesis.swinemanagement;

/**
 * Created by JL on 26/06/2017.
 */

public class Config {
    //Address of PHP Scripts
    public static final String URL_ADD = "http://192.168.15.8/connection/addUser.php";
    public static final String URL_SELECT = "http://192.168.15.8/connection/selectUser.php";

    //Keys that will be used to send the request to PHP Scripts
    public static final String KEY_USER_ID   = "id";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_USER_PASS = "password";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_USER = "username";
    public static final String TAG_PASS = "password";

    //employee id to pass with intent
    public static final String USER_ID = "user_id";
}
