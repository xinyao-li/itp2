FROM python:3.7-slim-buster

MAINTAINER li651854292@gmail.com

WORKDIR /app

COPY requirements.txt .

RUN pip install --no-cache-dir -r requirements.txt

COPY . .

COPY intelligent /app/intelligent

COPY template /app/template

ENV PYTHONPATH="${PYTHONPATH}:/app"

RUN pip install protobuf==3.20.*

EXPOSE 8084

CMD ["python", "/app/intelligent/intelligentController.py"]