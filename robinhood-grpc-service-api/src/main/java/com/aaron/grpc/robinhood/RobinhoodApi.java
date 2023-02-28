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
    public void logout(){
        LogoutRequest logoutRequest = LogoutRequest.newBuilder().build();
        LogoutResponse logoutResponse = stub.logout(logoutRequest);
    }
    public double quote(String ticker){
        QuoteRequest quoteRequest = QuoteRequest.newBuilder().setTicker(ticker).build();
        QuoteResponse quoteResponse = stub.quote(quoteRequest);
        return quoteResponse.getPrice();
    }
    public void buy(String ticker,double amount){
        BuyRequest buyRequest = BuyRequest.newBuilder().setTicker(ticker).setAmount(amount).build();
        BuyResponse buyResponse = stub.buy(buyRequest);
    }
    public void sell(String ticker,double amount){
        SellRequest sellRequest = SellRequest.newBuilder().setTicker(ticker).setAmount(amount).build();
        SellResponse sellResponse = stub.sell(sellRequest);
    }
    public double getBalance(){
        BalanceRequest balanceRequest = BalanceRequest.newBuilder().build();
        BalanceResponse balanceResponse = stub.getBalance(balanceRequest);
        return balanceResponse.getBalance();
    }

    public void autoBuy(String ticker,double target, double amount){
        AutoBuyRequest autoBuyRequest = AutoBuyRequest.newBuilder().setTicker(ticker).setTarget(target).setAmount(amount).build();
        AutoBuyResponse autoBuyResponse = stub.autoBuy(autoBuyRequest);
    }
    public void autoSell(String ticker,double target, double amount){
        AutoSellRequest autoSellRequest = AutoSellRequest.newBuilder().setTicker(ticker).setTarget(target).setAmount(amount).build();
        AutoSellResponse autoSellResponse = stub.autoSell(autoSellRequest);
    }
}
