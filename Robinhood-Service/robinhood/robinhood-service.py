from robinhood import robinhood_pb2_grpc,robinhood_pb2
from concurrent import futures

import grpc
import logging
import pyotp
import robin_stocks.robinhood as robin

class RobinhoodServicer(robinhood_pb2_grpc.RobinhoodServiceServicer):
    def login(self, request, context):
        username = request.username
        password = request.password
        message_success = "Login successful"
        message_fail = "Login Failed"
        #totp = pyotp.TOTP("My2factorAppHere").now()
        try:
            login = robin.login(username, password)
            return robinhood_pb2.LoginResponse(success=True, message=message_success)
        except Exception as e:
            logging.exception("Error during login")
        return robinhood_pb2.LoginResponse(success=False, message=message_fail)

    def quote(self, request, context):
        ticker = request.ticker
        r = robin.get_latest_price(ticker)
        message = "Quote retrieved"
        return robinhood_pb2.QuoteResponse(price=float(r[0]), message=message)

    def buy(self, request, context):
        ticker = request.ticker
        amount = request.amount
        r = robin.order_buy_market(ticker, amount)
        success = True
        message = "Stock bought"
        return robinhood_pb2.BuyResponse(success=success, message=message)

    def sell(self, request, context):
        ticker = request.ticker
        amount = request.amount
        r = robin.order_sell_market(ticker, amount)
        success = True
        message = "Stock sold"
        return robinhood_pb2.SellResponse(success=success, message=message)
    def apiTest(self, request, context):
        amount = request.amount
        message = "Test pass"
        return robinhood_pb2.ApiTestResponse(amount=amount+1, message=message)

if __name__ == '__main__':
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    robinhood_pb2_grpc.add_RobinhoodServiceServicer_to_server(RobinhoodServicer(), server)
    server.add_insecure_port("localhost:9090")
    print ("Python server start")
    server.start()
    server.wait_for_termination()
