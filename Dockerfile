FROM openjdk:21-jdk
LABEL authors="lyovik"
WORKDIR /app
COPY target/AppSpring-0.0.1-SNAPSHOT.jar /app/ComputerShop.jar
ENTRYPOINT ["java", "-jar", "ComputerShop.jar"]
#
#FROM mcr.microsoft.com/mssql/server:2019-latest
#
## Установите переменные среды для настройки SQL Server
#ENV SA_PASSWORD="YourStrongPassword123"
#ENV ACCEPT_EULA="Y"
#ENV MSSQL_PID="Developer"
#
## Порт для подключения к SQL Server
#EXPOSE 1433
#
## Скопируйте скрипты для создания базы данных и пользователей
#COPY ./init.sql /tmp/init.sql
#RUN /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P "$SA_PASSWORD" -d master -i /tmp/init.sql
#
## Добавьте команды для подключения к базе данных, если это необходимо.
## Например, если вы хотите, чтобы база данных была автоматически прикреплена при запуске контейнера:
#COPY C:/Program Files/Microsoft SQL Server/MSSQL16.SQLEXPRESS/MSSQL/DATA/ComputerShop.mdf /var/opt/mssql/data/your_database.mdf
## COPY ./your_database_log.ldf /var/opt/mssql/data/your_database_log.ldf
#
#CMD /opt/mssql/bin/sqlservr