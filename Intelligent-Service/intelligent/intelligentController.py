import threading
import time

from flask import Flask, jsonify, request, render_template, redirect,session
from intelligent.predictionAlgroithms import IntelligentService

app = Flask(__name__, template_folder='../template', static_folder='../plotfig')
app.secret_key = 'prediction_secret_key'
intelligent_service = IntelligentService()

@app.route('/intelligent/prediction', methods=['POST','GET'])
def predict():
    if request.method == 'GET':
        return render_template('prediction.html')
    elif request.method == 'POST':
        ticker = request.form.get('ticker')
        start = request.form.get('start')
        end = request.form.get('end')
        session['ticker'] = ticker
        session['start'] = start
        session['end'] = end
        return redirect('/intelligent/loading')

@app.route('/intelligent/loading',methods=['GET'])
def loading():
    return render_template('loading.html')

@app.route('/intelligent/result', methods=['GET'])
def result():
    ticker = session.get('ticker')
    start = session.get('start')
    end = session.get('end')
    price = intelligent_service.prediction(ticker, start, end)
    return render_template('result.html',price=price)

def run_prediction(ticker,start,end):
    price = intelligent_service.prediction(ticker, start, end)
    session['price'] = price

if __name__ == '__main__':
    app.run(debug=False, host='0.0.0.0',port=8084)

