FROM openjdk:11-jre-slim

WORKDIR /app

COPY . /app

RUN javac com/banking/bank_web_app/PostgresConnection.java

CMD ["java", "com.banking.bank_web_app.PostgresConnection"]