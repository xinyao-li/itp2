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
        message_fail = "Logout failed"
        try:
            robin.authentication.logout()
        except Exception as e:
            logging.exception("Error during logout")
            return robinhood_pb2.LoginResponse(success=False, message=message_fail)
        return robinhood_pb2.LoginResponse(success=True, message=message_success)

    def quote(self, request, context):
        ticker = request.ticker
        message_success = "Quote retrieved"
        message_fail = "Quote retrieved failed"
        try:
            r = robin.get_latest_price(ticker)
        except Exception as e:
            logging.exception("No such ticker")
            return robinhood_pb2.QuoteResponse(price=None, message=message_fail)
        return robinhood_pb2.QuoteResponse(price=float(r[0]), message=message_success)

    def buy(self, request, context):
        ticker = request.ticker
        amount = request.amount
        message_success = "Shares bought"
        message_fail = "bought fail"
        try:
            r = robin.order_buy_market(ticker, amount)
        except Exception as e:
            logging.exception("No such ticker or not enough balance")
            return robinhood_pb2.BuyResponse(success=False, message=message_fail)
        return robinhood_pb2.BuyResponse(success=True, message=message_success)

    def sell(self, request, context):
        ticker = request.ticker
        amount = request.amount
        message_success = "Shares sold"
        message_fail = "Sold failed"
        try:
            r = robin.order_sell_market(ticker, amount)
        except Exception as e:
            logging.exception("No such ticker or not enough shares")
            return robinhood_pb2.SellResponse(success=False, message=message_fail)
        return robinhood_pb2.SellResponse(success=True, message=message_success)

    def getBalance(self, request, context):
        try:
            profile = robin.account.load_account_profile()
            balance = float(profile['buying_power'])
            message = str(balance)
            return robinhood_pb2.BalanceResponse(balance=balance,message=message)
        except Exception as e:
            logging.exception("Error during get your balance")
        return robinhood_pb2.BalanceResponse(balance=None,message="You have no buying power now")

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

    def getCompany(self, request, context):
        message_success = "Company Name retrieved"
        message_fail = "Not find the company name under this ticker"
        try:
            company_name = robin.stocks.get_name_by_symbol(request.ticker)
            print(company_name)
            return robinhood_pb2.CompanyResponse(company=company_name, message=message_success)
        except Exception as e:
            print(e)
        return robinhood_pb2.CompanyResponse(company=None, message=message_fail)

if __name__ == '__main__':
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    robinhood_pb2_grpc.add_RobinhoodServiceServicer_to_server(RobinhoodServicer(), server)
    server.add_insecure_port("localhost:9090")
    print ("Python server start")
    server.start()
    server.wait_for_termination()
