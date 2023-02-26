package com.aaron.grpc.robinhood;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class RobinhoodApi {
    private ManagedChannel channel;
    private RobinhoodServiceGrpc.RobinhoodServiceBlockingStub stub;

    public void setup(String name, int port){
        this.channel = ManagedChannelBuilder.forAddress(name,port).usePlaintext().build();
        this.stub = RobinhoodServiceGrpc.newBlockingStub(this.channel);
    }
    public void login(String username, String password){
        LoginRequest loginRequest = LoginRequest.newBuilder().setUsername(username).setPassword(password).build();
        LoginResponse loginResponse = stub.login(loginRequest);
    }
    public void quote(String ticker){
        QuoteRequest quoteRequest = QuoteRequest.newBuilder().setTicker(ticker).build();
        QuoteResponse quoteResponse = stub.quote(quoteRequest);
    }
    public void buy(String ticker,int amount){
        BuyRequest buyRequest = BuyRequest.newBuilder().setTicker(ticker).setAmount(amount).build();
        BuyResponse buyResponse = stub.buy(buyRequest);
    }
    public void sell(String ticker,int amount){
        SellRequest sellRequest = SellRequest.newBuilder().setTicker(ticker).setAmount(amount).build();
        SellResponse sellResponse = stub.sell(sellRequest);
    }
    public void apiTest(double amount){
        ApiTestRequest apiTestRequest = ApiTestRequest.newBuilder().setAmount(amount).build();
        ApiTestResponse apiTestResponse = stub.apiTest(apiTestRequest);
    }
}
