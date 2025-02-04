FROM openjdk:11-jre-slim

WORKDIR /app

COPY . /app

RUN javac banking-app/com/banking/bank_web_app/PostgresConnection.java

CMD ["java", "banking-app.src.main.com.banking.bank_web_app.PostgresConnection"]