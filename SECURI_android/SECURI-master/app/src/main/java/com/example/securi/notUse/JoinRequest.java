//package com.example.securi;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Response;
//import com.android.volley.toolbox.StringRequest;
//
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JoinRequest extends StringRequest {
//    //서버 url 설정(php파일 연동)
//    final static  private String URL="http://securi.dothome.co.kr/securijoin.php";
//    private Map<String,String> map;
//
//    public JoinRequest(String userid, String userpw, String username, String useremail, Response.Listener<String>listener){
//        super(Method.POST,URL,listener,null);//위 url에 post방식으로 값을 전송
//
//        map=new HashMap<>();
//        map.put("userid",userid);
//        map.put("userpw",userpw);
//        map.put("username",username);
//        map.put("useremail",useremail);
//    }
//
//    @Override
//    protected Map<String, String> getParams() throws AuthFailureError {
//        return map;
//    }
//}
