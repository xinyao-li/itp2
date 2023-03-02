import logging
import pyotp
import robin_stocks.robinhood as robin

def login(username,password):
    try:
        #robin.authentication.logout()
        login = robin.login(username, password)
    except Exception as e:
        logging.exception("Error during login")

def quote(ticker):
    r = robin.get_latest_price(ticker)
    print(type(r[0]))
    return float(r[0])

def getBalance():
    try:
        profile = robin.account.load_account_profile()
        balance = float(profile['buying_power'])
        return balance
    except Exception as e:
        logging.exception("Error during get your balance")
    return None
def getCompany(ticker):
    try:
        company_name = robin.stocks.get_name_by_symbol(ticker)
        print(type(company_name))
        return company_name
    except Exception as e:
        print(e)
    return None

login("li651854292@gmail.com","19931127Shi@")
print(quote("AAPL"))
print(getBalance())
print(getCompany("AAPL"))