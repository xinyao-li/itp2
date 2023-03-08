import robin_stocks.robinhood as robin
import mysql.connector
import configparser


username = input("username:")
password = input("password:")

try:
    login = robin.login(username,password)
    print('Login Success')
except Exception as e:
    print("username or password invalid")
    print(e)

config = configparser.ConfigParser()
config.read('config.properties')

host = config.get('db', 'host')
conn = mysql.connector.connect(user='root', password='19931127li', host=host, port='3306', database='collection_db')

cursor = conn.cursor()
sql = "INSERT INTO login_table (username, password) VALUES (%s, %s)"

try:
    cursor.execute(sql,(username,password))
    conn.commit()
    print("registered in DB")
except Exception as e:
    conn.rollback()
    print("register DB failed or account already in DB")
    print(e)
conn.close()


