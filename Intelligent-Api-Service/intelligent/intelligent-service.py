from intelligent import intelligent_pb2_grpc,intelligent_pb2
from concurrent import futures

import grpc
import math
import yfinance as yf
import numpy as np
import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from keras.models import Sequential
from keras.layers import Dense, LSTM
import matplotlib.pyplot as plt
import datetime as dt
import os

class IntelligentServicer(intelligent_pb2_grpc.IntelligentServiceServicer):

    def plotPrediction(self, request, context):
        ticker = request.ticker
        start = request.start
        end = request.end

        start_array = start.split("-")
        end_array = end.split("-")
        start = dt.datetime(int(start_array[0]),int(start_array[1]),int(start_array[2]))
        end = dt.datetime(int(end_array[0]),int(end_array[1]),int(end_array[2]))

        df = yf.download(ticker, start ,end)
        #Create a new dataframe with only the Close column
        data = df.filter(['Close'])
        dataset = data.values
        training_data_len = math.ceil(len(dataset)*.8)

        #Scale the data
        scaler = MinMaxScaler(feature_range=(0,1))
        scaled_data = scaler.fit_transform(dataset)

        #Create the training data set
        #Create the scaled traning data set
        train_data = scaled_data[0:training_data_len, :]
        x_train = []
        y_train = []

        for i in range(60,len(train_data)):
            x_train.append(train_data[i-60:i,0])
            y_train.append(train_data[i,0])

        #Convert x_train and y_train to numpy array
        x_train,y_train = np.array(x_train),np.array(y_train)
        x_train = np.reshape(x_train,(x_train.shape[0],x_train.shape[1],1))

        #Build the LSTM model
        model = Sequential()
        model.add(LSTM(50, return_sequences=True, input_shape=(x_train.shape[1], 1)))
        model.add(LSTM(50, return_sequences=False))
        model.add(Dense(25))
        model.add(Dense(1))

        #Compile the model
        model.compile(optimizer='adam', loss='mean_squared_error')

        #Train the model
        model.fit(x_train, y_train, batch_size=1, epochs=1)

        #Create the testing dataset
        #Create a new array containing scaled values from index 1543 to 2003
        test_data = scaled_data[training_data_len-60:, :]
        x_test = []
        y_test = dataset[training_data_len:, :]
        for i in range(60, len(test_data)):
            x_test.append(test_data[i-60:i, 0])

        #Convert the data to numpy array
        x_test = np.array(x_test)

        #Reshape the data
        x_test = np.reshape(x_test, (x_test.shape[0], x_test.shape[1], 1))

        #Get the models predicted price value
        predictions = model.predict(x_test)
        predictions = scaler.inverse_transform(predictions)

        #Get the root mean squared error(RMSE)
        rmse = np.sqrt(np.mean(predictions - y_test) **2)

        #Create a future dates array for predicting prices for April 2023
        future_dates = pd.date_range(start=end, periods=30, freq='D')

        #Create the predictions for April 2023
        x_future = x_test[-30:] # Use the last 30 days of data to predict future prices
        x_future = np.reshape(x_future, (x_future.shape[0], x_future.shape[1], 1))
        future_predictions = model.predict(x_future)
        future_predictions = scaler.inverse_transform(future_predictions)

        #Plot the data
        train = data[:training_data_len]
        valid = data[training_data_len:]
        valid['Predictions'] = predictions

        # Create a dataframe with future dates and predicted prices
        future_df = pd.DataFrame({'Date': future_dates, 'Predictions': future_predictions[:,0]})
        future_df.set_index('Date', inplace=True)

        # Concatenate the valid data and future predictions dataframes
        valid = pd.concat([valid, future_df], axis=0)

        #Visualize the data
        plt.figure(figsize=(16,8))
        plt.title('Model')
        plt.xlabel('Date', fontsize=18)
        plt.ylabel('Close Price USD ($)', fontsize=18)
        plt.plot(train['Close'])
        plt.plot(valid[['Close', 'Predictions']])
        plt.legend(['Train', 'Val', 'Predictions'], loc='lower right')

        # Save the plot in the output directory
        output_dir = "../../assets"
        plt.savefig(os.path.join(output_dir, "prediction.png"), dpi=300, bbox_inches='tight')
        plt.savefig('prediction.png')
        return intelligent_pb2.PlotResponse(success=True, message="prediction calculate completed")

    def pricePredict(self, request, context):

        ticker = request.ticker
        start = request.start
        end = request.end

        start_array = start.split("-")
        end_array = end.split("-")
        start = dt.datetime(int(start_array[0]),int(start_array[1]),int(start_array[2]))
        end = dt.datetime(int(end_array[0]),int(end_array[1]),int(end_array[2]))

        df = yf.download(ticker, start ,end)
        data = df.filter(['Close'])
        dataset = data.values
        training_data_len = math.ceil(len(dataset)*.8)

        #Scale the data
        scaler = MinMaxScaler(feature_range=(0,1))
        scaled_data = scaler.fit_transform(dataset)

        #Create the training data set
        #Create the scaled traning data set
        train_data = scaled_data[0:training_data_len, :]
        x_train = []
        y_train = []

        for i in range(60,len(train_data)):
            x_train.append(train_data[i-60:i,0])
            y_train.append(train_data[i,0])

        #Convert x_train and y_train to numpy array
        x_train,y_train = np.array(x_train),np.array(y_train)
        x_train = np.reshape(x_train,(x_train.shape[0],x_train.shape[1],1))

        #Build the LSTM model
        model = Sequential()
        model.add(LSTM(50, return_sequences=True, input_shape=(x_train.shape[1], 1)))
        model.add(LSTM(50, return_sequences=False))
        model.add(Dense(25))
        model.add(Dense(1))

        #Compile the model
        model.compile(optimizer='adam', loss='mean_squared_error')

        #Train the model
        model.fit(x_train, y_train, batch_size=1, epochs=1)
        #Get the last 60 days closing price value and convert the dataframe to array
        last_60_days = data[-60:].values
        #Scale the data to be the value between 0 and 1
        last_60_days_scaled = scaler.transform(last_60_days)
        X_test = []
        X_test.append(last_60_days_scaled)
        #Convert the X_test data set to numpy array
        X_test = np.array(X_test)
        #Reshape the data
        X_test = np.reshape(X_test, (X_test.shape[0], X_test.shape[1], 1))
        Y_test = dataset[training_data_len:, :]
        #Get the predicted scaled price
        pred_price = model.predict(X_test)
        pred_price = scaler.inverse_transform(pred_price)
        rmse = np.sqrt(np.mean(pred_price - Y_test) **2)
        #Create a future dates array for predicting prices for April 2023
        future_dates = pd.date_range(start=end, periods=30, freq='D')

        #Create the predictions for end date(future)
        x_future = X_test[-30:] # Use the last 30 days of data to predict future prices
        x_future = np.reshape(x_future, (x_future.shape[0], x_future.shape[1], 1))
        future_predictions = model.predict(x_future)
        future_predictions = scaler.inverse_transform(future_predictions)
        return intelligent_pb2.PriceResponse(price=float(future_predictions[0][0]),message="Price prediction completed")

if __name__ == '__main__':
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=5))
    intelligent_pb2_grpc.add_IntelligentServiceServicer_to_server(IntelligentServicer(), server)
    server.add_insecure_port("[::]:7911")
    #address = os.environ.get('ADDRESS', 'localhost')
    #server.add_insecure_port(f"{address}:7911")
    print("Intelligent Python server start")
    server.start()
    server.wait_for_termination()