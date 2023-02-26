package com.arron.response;

public class Response {
    public static final Response USERNAME_PASSWORD_INVALID = new Response("1001","username or password invalid");
    public static final Response N0_SUCH_TIKCER = new Response("1002","Not found the ticker");
    public static final Response SUCCESS = new Response();

    private String code;
    private String message;
    public Response(){
        this.code = "0";
        this.message = "success";
    }
    public Response(String code, String message){
        this.code = code;
        this.message = message;
    }
    public static Response exception(Exception e){return new Response("9999",e.getMessage());}
}
