# pet-clinic

## О приложении

Это приожение является системой администриования для ветеринарной клиники и позволяет работать со всем рутинными операциями, которые выполняются в клинике каждый день:
- Запись и прием пациентов
- Учет дходов и расходов
- Учет материальной базы (поставки оборудования, медикаментов и т.д.)
- Ведение базы клиентов
- Ведение базы поставщиков товаров и услуг
- Ведение базы лабораторных результатов пациентов
- Статистика по разным аспектам работы клиники

Основой приложения послужило простейшее приложение Spring Petclinic - пример использования Spring Boot для веб-приложения, использующего базу данных. 

## Используемые технологии

- Java 11
- Apache Maven
- Spring Boot
- MySQL
- jQuery

Для упрощения работы используется автоконфигурация Spring Boot, которая позволяет использовать его готовые возможности через настройки свойств в файле [application.properties](src/main/resources/application.properties)

## Структура проекта

- [src/main/java/](src/main/java/) - исходные Java-файлы
- [ClinicApplication.java](src/main/java/com/home/project/pet/clinic/ClinicApplication.java) - главный класс приложения с main методом, которым запускается приложение
- [WebSecurityConfig.java](src/main/java/com/home/project/pet/clinic/web/security/WebSecurityConfig.java) - Spring Boot класс с конфигурацией безопасности преложения
- [application.properties](src/main/resources/application.properties) - Файл со свойствами Spring Boot, позволяющий управлять конфигурацией и разворачиванием приложения
- [log4j.properties](src/main/resources/log4j.properties) - Файл со свойствами Log4j, позволяющий управлять настройкой журнала событий приложения (в частности, записью событий в файл myapp.log)
- [src/main/resources/static](src/main/resources/static) - статические ресурсы веб-приложения (скрипты, стили, статические картинки)
- [src/main/resources/templates](src/main/resources/templates) - Thymeleaf шаблоны для динамического отображения Java-обьектов при использовании веб-приложения пользователями
- [pom.xml](pom.xml) - Maven дескриптор приложения
- [database.sql](database.sql) - SQL скрипт для инициализации базы данных приложения со всеми необходимыми таблицами и заполнение их минимальным набором тестовых данных

## Интеграция с базой данных

Интеграция с базой данных происходит через свойства в файле [application.properties](src/main/resources/application.properties), которые автоматически настраивают Spring Boot для подключения к заданной базе данных и автоматической ассоциации таблиц базы данных с Java-сущностями:
- `spring.datasource.url` - JDBC URL базы данных приложения
- `spring.datasource.username` и `spring.datasource.password` - имя пользователя и пароль для подключения к базе данных приложения

## Запуск проекта

Для запуска проекта тебуется проверить, что свойства в файле [application.properties](src/main/resources/application.properties) соответствуют используемой базе данных, и изменить их при необходимости.

После этого требуется зайти в корневую папку приложения и выполнить команду:
```mvn spring-boot:run```
