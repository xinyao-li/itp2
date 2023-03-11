FROM python:3.7-slim-buster

MAINTAINER li651854292@gmail.com

WORKDIR /app

COPY requirements.txt .

RUN pip install --no-cache-dir -r requirements.txt


RUN apt-get update && \
    apt-get install -y redis-server && \
    service redis-server start

COPY . .

COPY intelligent /app/intelligent

COPY template /app/template

COPY resources /app/resources

ENV PYTHONPATH="${PYTHONPATH}:/app"

RUN pip install protobuf==3.20.*

EXPOSE 8084

CMD ["python", "/app/intelligent/intelligentController.py"]