from flask import Flask, request, render_template, redirect,session
from intelligent.predictionAlgroithms import IntelligentService
import redis
import configparser
import os

app = Flask(__name__, template_folder='../template', static_folder='../plotfig')
app.secret_key = 'prediction_secret_key'

config = configparser.ConfigParser()
config_path = os.path.join(os.path.dirname(__file__), '../resources/config.properties')
config.read(config_path)
host = config.get('cache','host')

redis_instance = redis.Redis(host=host, port=6379, db=0)
intelligent_service = IntelligentService()


@app.route('/intelligent', methods=['POST','GET'])
def predict():
    if request.method == 'GET':
        return render_template('prediction.html')
    elif request.method == 'POST':
        ticker = request.form.get('ticker')
        start = request.form.get('start')
        end = request.form.get('end')
        redis_instance.set('ticker', ticker)
        redis_instance.set('start', start)
        redis_instance.set('end', end)
        return redirect('/intelligent/loading')

@app.route('/intelligent/loading',methods=['GET'])
def loading():
    return render_template('loading.html')

@app.route('/intelligent/result', methods=['GET'])
def result():
    ticker = redis_instance.get('ticker').decode()
    start = redis_instance.get('start').decode()
    end = redis_instance.get('end').decode()
    price = intelligent_service.prediction(ticker, start, end)
    return render_template('result.html',price=price)

def run_prediction(ticker,start,end):
    price = intelligent_service.prediction(ticker, start, end)
    session['price'] = price
    redis_instance.set('price',price)

if __name__ == '__main__':
    app.run(debug=False, host='0.0.0.0',port=8084)

