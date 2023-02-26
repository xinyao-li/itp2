from robinhood import robinhood_pb2_grpc

import logging
import pyotp
import robin_stocks.robinhood as robin

class Robinhood(robinhood_pb2_grpc.RobinhoodServiceServicer):
    def LOGIN(self, username, password):
        print(f"Login called with username: {username}")
        totp = pyotp.TOTP("My2factorAppHere").now()
        try:
            login = robin.login(username, password, mfa_code=totp)
            print("Login successful")
            return True
        except Exception as e:
            logging.exception("Error during login")
        return False

    def QUOTE(self, ticker):
        r = robin.get_latest_price(ticker)
        print(ticker.upper()+"$:"+str(r[0]))
        return r[0]

    def BUY(self, ticker, amount):
        r = robin.order_buy_market(ticker, amount)
        print(r)
        return True

    def SELL(self, ticker, amount):
        r = robin.order_sell_market(ticker, amount)
        print(r)
        return True
    def ApiTest(self,amount):
        print(amount)
        return amount+1

if __name__ == '__main__':
    with grpc.insecure_channel('localhost:9090') as channel:
        print('Starting the server...')
        stub = robinhood_pb2_grpc.RobinhoodServiceStub(channel)
        print("thrift server exit")
