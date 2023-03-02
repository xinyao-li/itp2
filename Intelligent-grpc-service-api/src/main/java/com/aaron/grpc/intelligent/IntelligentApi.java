package com.aaron.grpc.intelligent;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class IntelligentApi {

    private ManagedChannel channel;

    private IntelligentServiceGrpc.IntelligentServiceBlockingStub stub;

    public void setup(String name, int port){
        this.channel = ManagedChannelBuilder.forAddress(name,port).usePlaintext().build();
        this.stub = IntelligentServiceGrpc.newBlockingStub(this.channel);
    }

    public void plotPrediction(String ticker, String startDate, String endDate){
        PlotRequest plotRequest = PlotRequest.newBuilder().setTicker(ticker).setStartDate(startDate).setEndDate(endDate).build();
        PlotResponse plotResponse = stub.plotPrediction(plotRequest);
    }

    public double pricePredict(String ticker, String startDate, String endDate){
        PriceRequest priceRequest = PriceRequest.newBuilder().setTicker(ticker).setStartDate(startDate).setEndDate(endDate).build();
        PriceResponse priceResponse = stub.pricePredict(priceRequest);
        return priceResponse.getPrice();
    }
}
