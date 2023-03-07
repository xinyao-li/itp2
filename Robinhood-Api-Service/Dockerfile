FROM python:3.7-slim-buster

MAINTAINER li651854292@gmail.com

WORKDIR /app

COPY requirements.txt .

RUN pip install --no-cache-dir -r requirements.txt

EXPOSE 9090

COPY . .

COPY robinhood /app/robinhood

ENV PYTHONPATH="${PYTHONPATH}:/app"

RUN pip install protobuf==3.20.*

CMD ["python", "/app/robinhood/robinhood-service.py"]