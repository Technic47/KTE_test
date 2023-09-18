# Тестовый сервис регистрации пациентов в поликлинике

Выполнен с использованием Spring Boot, JPA, Postgres, REST API.
Авторизация выполнена с помощью JWT токена.
Покрытие тастами - **более 65%**.
Дополнительно в проекте присутствует модуль SOAP сервиса.

Основные сущности сервиса:  
1. Cabinet - кабинет приёма.  
2. Customer - модель пациента.  
3. Doctor - модель врача.  
4. Ticket - талон на приём.  
5. TimeSlot - слот времени.  

**Cabinet**  
Содержит номер и коллекцию слотов времени для записи.

**Customer**  
Содержит порядковый и uuid номер для идентификации.

**Doctor**  
Содержит порядковый и uuid номер для идентификации.
Так же присутствует поле специализации врача.

**Ticket**  
В талоне хранится информация о пациенте, враче и слоте времени приёме.

**TimeSlot**  
В слоте содержится время начала, время конца, кабинет и талон на приём.
Присутствует дополнительная информация о продолжительности приёма и занятости слота.

### Регистрация
Регистрационный Post запрос отправляется на:  
/api/registration  
Запрос должен содержать 3 поля:
- username - String
- password - String
- email - String

После регистрации необходимо получить токен post запросом на:
/api/auth/login
В запросе указываются 2 поля:
- username - String
- password - String

Полная информация по REST API доступна после регистрации по ссылке:
/swagger-ui/index.html

Скачать JSON документации можно по ссылке:
/v3/api-docs

---
API позволяет сгенерировать расписание на год, месяц или день с заданным интервалом в минутах. Стандартный - 15минут.

После генерации временных слотов можно создавать талоны на конкретный слот и кабинет.

Есть функционал получения списка свободных слотов в кабинете по заданной дате.

По id/uuid пациента можно получить все талоны в которых он записан.
