package com.exchange.entity;
/**
 * @author 何承聪
 * 用户实体类
 */
public class USER {
   private int    user_id;          //编号
   private String  user_account;     //账号
   private String  user_password;    //密码
   private String  user_nickName;    //昵称
   private char    user_gender;     //性别
   private String  user_imgUrl;     //头像图片地址
   private String  user_address;    //地址
   private String  user_email;      //邮箱
   private String  user_tel;        //手机号码
   private char   user_type;       //用户类型
   
   //无参构造函数
   public USER(){
       
   }
     
   
   //有参构造函数
    public USER(int user_id, String user_account, String user_password,
            String user_nickName, char user_gender, String user_imgUrl,
            String user_address, String user_email, String user_tel,
            char user_type)
    {
        super();
        this.user_id = user_id;
        this.user_account = user_account;
        this.user_password = user_password;
        this.user_nickName = user_nickName;
        this.user_gender = user_gender;
        this.user_imgUrl = user_imgUrl;
        this.user_address = user_address;
        this.user_email = user_email;
        this.user_tel = user_tel;
        this.user_type = user_type;
    }

    public int getUser_id()
    {
        return user_id;
    }
    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }
    
    public String getUser_account()
    {
        return user_account;
    }
    
    public void setUser_account(String user_account)
    {
        this.user_account = user_account;
    }
    
    public String getUser_password()
    {
        return user_password;
    }
    
    public void setUser_password(String user_password)
    {
        this.user_password = user_password;
    }
    
    public String getUser_nickName()
    {
        return user_nickName;
    }
    
    public void setUser_nickName(String user_nickName)
    {
        this.user_nickName = user_nickName;
    }
    
    public char getUser_gender()
    {
        return user_gender;
    }
    
    public void setUser_gender(char user_gender)
    {
        this.user_gender = user_gender;
    }
    
    public String getUser_imgUrl()
    {
        return user_imgUrl;
    }
    
    public void setUser_imgUrl(String user_imgUrl)
    {
        this.user_imgUrl = user_imgUrl;
    }
    
    public String getUser_address()
    {
        return user_address;
    }
    
    public void setUser_address(String user_address)
    {
        this.user_address = user_address;
    }
    
    public String getUser_email()
    {
        return user_email;
    }
    
    public void setUser_email(String user_email)
    {
        this.user_email = user_email;
    }
    
    public String getUser_tel()
    {
        return user_tel;
    }
    
    public void setUser_tel(String user_tel)
    {
        this.user_tel = user_tel;
    }
    
    public char getUser_type()
    {
        return user_type;
    }
    
    public void setUser_type(char user_type)
    {
        this.user_type = user_type;
    }


    @Override
    public String toString()
    {
        return "USER [user_id=" + user_id + ", user_account=" + user_account
                + ", user_password=" + user_password + ", user_nickName="
                + user_nickName + ", user_gender=" + user_gender
                + ", user_imgUrl=" + user_imgUrl + ", user_address="
                + user_address + ", user_email=" + user_email + ", user_tel="
                + user_tel + ", user_type=" + user_type + "]";
    }
       
   
   
}
