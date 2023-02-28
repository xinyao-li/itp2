from robinhood import robinhood_pb2_grpc,robinhood_pb2
from concurrent import futures

import grpc
import logging
import pyotp
import threading
import time
import robin_stocks.robinhood as robin

class RobinhoodServicer(robinhood_pb2_grpc.RobinhoodServiceServicer):
    def login(self, request, context):
        username = request.username
        password = request.password
        message_success = "Login successful"
        #totp = pyotp.TOTP("My2factorAppHere").now()
        try:
            login = robin.login(username, password)
        except Exception as e:
            logging.exception("Error during login")
        return robinhood_pb2.LoginResponse(success=True, message=message_success)

    def logout(self, request, context):
        message_success = "Logout successful"
        try:
            robin.authentication.logout()
        except Exception as e:
            logging.exception("Error during logout")
        return robinhood_pb2.LoginResponse(success=True, message=message_success)
    def quote(self, request, context):
        ticker = request.ticker
        try:
            r = robin.get_latest_price(ticker)
        except Exception as e:
            logging.exception("No such ticker")
        message = "Quote retrieved"
        return robinhood_pb2.QuoteResponse(price=float(r[0]), message=message)

    def buy(self, request, context):
        ticker = request.ticker
        amount = request.amount
        try:
            r = robin.order_buy_market(ticker, amount)
        except Exception as e:
            logging.exception("No such ticker or not enough balance")
        message = "Stock bought"
        return robinhood_pb2.BuyResponse(success=True, message=message)

    def sell(self, request, context):
        ticker = request.ticker
        amount = request.amount
        try:
            r = robin.order_sell_market(ticker, amount)
        except Exception as e:
            logging.exception("No such ticker or not enough shares")
        message = "Stock sold"
        return robinhood_pb2.SellResponse(success=True, message=message)
    def getBalance(self, request, context):
        try:
            account = robin.accounts.get_accounts()[0]
            balance = float(account['buying_power'])
            message = str(balance)
            return robinhood_pb2.BalanceResponse(balance=balance,message=message)
        except Exception as e:
            logging.exception("Error during get your balance")
    def autoBuy(self, request, context):
        should_stop = threading.Event()
        thread = threading.Thread(target=self.checkBuyPrice(should_stop,request,context))
        thread.start()
        print("autoBuy is triggered")
        thread.join()
    def autoSell(self, request, context):
        should_stop = threading.Event()
        thread = threading.Thread(target=self.checkSellPrice(should_stop,request,context))
        thread.start()
        print("autoSell is triggered")
        thread.join()
    def checkBuyPrice(self,should_stop,request,context):
        while not should_stop.is_set():
            cur_price = None;
            try:
                cur_price = float(robin.get_latest_price(request.ticker)[0])
                print("AutoBuy function current price:"+str(cur_price))
            except Exception as e:
                logging.exception("No such ticker")
            if cur_price is not None and cur_price <= request.target:
                try:
                    self.buy(request, context)
                    print("autoBuy success")
                except Exception as e:
                    print(e)
                should_stop.set()
            # Wait for some time before checking the price again
            time.sleep(1)
    def checkSellPrice(self,should_stop,request,context):
        while not should_stop.is_set():
            cur_price = None;
            try:
                cur_price = float(robin.get_latest_price(request.ticker)[0])
                print("AutoSell function current price:"+str(cur_price))
            except Exception as e:
                logging.exception("No such ticker")
            if cur_price is not None and cur_price >= request.target:
                try:
                    self.sell(request, context)
                    print("autoSell success")
                except Exception as e:
                    print(e)
                should_stop.set()
            # Wait for some time before checking the price again
            time.sleep(1)


if __name__ == '__main__':
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    robinhood_pb2_grpc.add_RobinhoodServiceServicer_to_server(RobinhoodServicer(), server)
    server.add_insecure_port("localhost:9090")
    print ("Python server start")
    server.start()
    server.wait_for_termination()
