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
        success = True
        message = "Login successful"
        totp = pyotp.TOTP("My2factorAppHere").now()
        try:
            login = robin.login(username, password, mfa_code=totp)
        except Exception as e:
            logging.exception("Error during login")
        return robinhood_pb2.LoginResponse(success=success, message=message)

    def quote(self, request, context):
        ticker = request.ticker
        r = robin.get_latest_price(ticker)
        message = "Quote retrieved"
        return robinhood_pb2.QuoteResponse(price=r[0], message=message)

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
        return robinhood_pb2.SellResponse(amount=amount+1, message=message)

if __name__ == '__main__':
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    robinhood_pb2_grpc.add_RobinhoodServiceServicer_to_server(RobinhoodServicer(), server)
    server.add_insecure_port("localhost:9090")
    print ("Python server start")
    server.start()
    server.wait_for_termination()
