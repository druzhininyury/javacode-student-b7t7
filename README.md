## Замечания по реализации.

Для скорости опущены некоторые важные детали.

* Аннотация JsonView использована только для модели User.
* Не реализована операция delete для сущностей, т.к. сущности удалять плохо. Вместо это предполагается, что должны быть использованы статусы, например, boolean isActive.
* Не использованы мапперы (например, mapstruct).
* Не учтена работа приложения под нагрузкой, т.е предполагается, что транзакции не конкурируют друг с другом.
* Отсутствует логгирование.

### Задание
Разработайте RESTful API для управления информацией о пользователях и их заказах в интернет-магазине. Используйте аннотацию @JsonView для определения различных представлений JSON в зависимости от контекста.

Сущности:
User: содержит информацию о пользователе, такую как имя, адрес электронной почты, идентификатор и т.д.

Order: представляет заказ пользователя и содержит информацию о товарах, сумме заказа и статусе.

Реализация CRUD операций:
Создайте CRUD операции для пользователей и их заказов с использованием Spring Data JPA.

RESTful API:

Реализуйте RESTful эндпоинты для:

Получения списка всех пользователей (без деталей заказов).

Получения информации о конкретном пользователе (включая детали заказов).

Создания нового пользователя.

Обновления информации о пользователе.

Удаления пользователя.

Используйте @JsonView для определения различных представлений JSON в зависимости от контекста:
Создайте интерфейсы представлений, например, Views.UserSummary и Views.UserDetails.

Настройте их в соответствии с вашими потребностями (например, в представлении UserSummary отображать только базовую информацию о пользователе, а в UserDetails включать также детали заказов).

Обработка ошибок и валидация:
Обеспечьте корректную обработку ошибок и возвращение соответствующих HTTP-статусов.

Добавьте валидацию входных данных, такую как проверка корректности электронной почты.

Тестирование:
Напишите модульные тесты для вашего кода, уделяя внимание различным представлениям JSON при использовании @JsonView.