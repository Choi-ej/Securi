package com.example.securi.dataSet;

        import com.google.firebase.database.Exclude;

        import java.util.HashMap;
        import java.util.Map;

public class firebaseDateSet {
    //회원가입
    public String email;
    public String password;
    //public String dataDate;
    //public int dataInfoImg;

    //방문자 기록
    public Map<String,Object> dataInfo = new HashMap<>();

    //출입 기록
    public Map<String,Object> entryHistory = new HashMap<>();

    public firebaseDateSet(String email, String password){
        this.email = email;
        this.password = password;
    }
    @Exclude
    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("email",email);
        result.put("password",password);
        result.put("dataInfo",dataInfo);
        result.put("entryHistory",entryHistory);
        return result;
    }
}
