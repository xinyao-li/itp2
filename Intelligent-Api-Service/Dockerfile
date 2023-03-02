FROM python:3.7-slim-buster
WORKDIR /app
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
COPY . .
COPY intelligent /app/intelligent
ENV PYTHONPATH="${PYTHONPATH}:/app"
RUN pip install protobuf==3.20.*
CMD ["python", "/app/intelligent/intelligent-service.py"]