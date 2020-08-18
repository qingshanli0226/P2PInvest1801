package com.bw.net;

public class UserManager {

    private UserManager() {
    }

    private static UserManager userManager ;
    public static UserManager getInstance(){

        if (userManager==null){
            synchronized (String.class){
                if (userManager==null){
                    userManager=new UserManager();
                }
            }
        }
        return userManager;
    }
}
