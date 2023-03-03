package com.aaron.response;

public class Response {
    public static final Response USERNAME_PASSWORD_INVALID = new Response("1001","username or password invalid");
    public static final Response N0_SUCH_TIKCER = new Response("1002","Not found the ticker");

    public static final Response BUY_FAILED = new Response("1003","Not found the ticker or not enough balance");

    public static final Response SELL_FAILED = new Response("1004","Not found the ticker or not enough amount");

    public static final Response GET_BALANCE_FAILED = new Response("1005","Failed to get your buy power");

    public static final Response ADD_COLLECTION_FAILED = new Response("1006","Failed to add the stock in collection");

    public static final Response NO_SUCH_COMPANY = new Response("1007","Not found such a company or ticker in collection");

    public static final Response LOGIN_FAILED = new Response("1008","Login Failed with backend reason");

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
