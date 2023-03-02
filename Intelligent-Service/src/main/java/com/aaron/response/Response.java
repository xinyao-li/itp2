package com.aaron.response;

public class Response {
    public static final Response PREDICTION_FAILED = new Response("1001","prediction of the Stock failed");

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
