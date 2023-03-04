import robin_stocks.robinhood as robin

username = input("username:")
password = input("password:")
login = robin.login(username,password)
print('Login Success')