# CheckRunner
CheckRunner – решение задачи из Сlevertec по формированию чека в магазине.

# Стек технологий
Для создания программы использовался следующий стек:
1) Spring Boot 
2) Spring MVC
3) Spring Data JPA
4) PostgreSQL
5) Lombok
6) Liquibase
7) Junit / Mockito 

Также были использованы следующие инструменты:
1) Docker
2) Postman

# Запуск

<hr>

### Через консоль

Для запуска программы перейдите в директорию /build/libs/ и выполните следующую комманду:

```bash
java -jar CheckRunner.jar 1-2 3-4 5-6 card-1234
```

1-2, 3-4, 5-6 – товары, где первое число обозначает ID товара из БД, а второе число – его количество.

card-1234 – карта с номером 1234 из БД.

После запуска программы будут созданы и заполены таблицы discount_card и product.

<hr>

### При помощи Docker-compose

Для запуска программы перейдите в корневую папку проетка и выполните следующую комманду:

```bash
docker-compose up
```

После запуска будет создан образ 'check-runner' и 2 контейнера:
1) check-runner-app
2) check-runner-db

В конфигурации используется следующий Dockerfile:

```dockerfile
FROM openjdk:17-jdk-slim
MAINTAINER nikitasudaev
COPY build/libs/CheckRunner.jar CheckRunner.jar
ENTRYPOINT ["java","-jar","/CheckRunner.jar", "1-2", "3-5", "5-6"]
```

# Эндпоинты

Просмотреть все эндпоинты можно как в списке ниже, так и через Swagger-ui по адресу: <http://localhost:8080/swagger-ui/index.html#/> 

Все эндпоинты находятся по адресу <http://localhost:8080>.

<hr>

### Получить чек в формате text/markdown

#### Запрос:
```
GET  /api/v1/chek/text 
```

#### Ответ (значения могут быть другими):
```
string
```
<hr>

### Получить чек в формате json

#### Запрос:
```
GET  /api/v1/chek/json 
```

#### Ответ (значения могут быть другими):
```
{
  "dateTime": "2022-12-17T07:44:14.786Z",
  "products": {
    "additionalProp1": 0,
    "additionalProp2": 0,
    "additionalProp3": 0
  },
  "discountCard": {
    "id": 0,
    "number": 0,
    "percent": 0
  }
}
```
<hr>

### Получить продукт

#### Запрос:
```
GET  /api/v1/products/{id}  
```

#### Ответ (значения могут быть другими):
```
{
  "id": 0,
  "name": "string",
  "price": 0,
  "discount": true
}
```
<hr>

### Обновить продукт

#### Запрос:
```
PUT  /api/v1/products/{id}  
```

#### Ответ (значения могут быть другими):
```
{
  "id": 0,
  "name": "string",
  "price": 0,
  "discount": true
}
```
<hr>

### Удалить продукт

#### Запрос:
```
DELETE  /api/v1/products/{id}  
```

#### Ответ:
```
100 CONTINUE
```
<hr>

### Получить все продукты

#### Запрос:
```
GET  /api/v1/products/  
```

#### Ответ (значения могут быть другими):
```
[
  {
    "id": 0,
    "name": "string",
    "price": 0,
    "discount": true
  }
]
```
<hr>

### Добавить продукт

#### Запрос:
```
POST  /api/v1/products/  
```

#### Ответ (значения могут быть другими):
```
{
  "id": 0,
  "name": "string",
  "price": 0,
  "discount": true
}
```
<hr>

### Получить карту

#### Запрос:
```
GET  /api/v1/cards/{id}  
```

#### Ответ (значения могут быть другими):
```
{
  "id": 0,
  "number": 0,
  "percent": 0
}
```
<hr>

### Обновить карту

#### Запрос:
```
PUT  /api/v1/cards/{id}  
```

#### Ответ (значения могут быть другими):
```
{
  "id": 0,
  "number": 0,
  "percent": 0
}
```
<hr>

### Удалить карту

#### Запрос:
```
DELETE  /api/v1/cards/{id}  
```

#### Ответ:
```
100 CONTINUE
```
<hr>

### Получить все карты

#### Запрос:
```
GET  /api/v1/cards/ 
```

#### Ответ (значения могут быть другими):
```
[
  {
    "id": 0,
    "number": 0,
    "percent": 0
  }
]
```
<hr>

### Добавить карту

#### Запрос:
```
POST  /api/v1/cards/ 
```

#### Ответ (значения могут быть другими):
```
{
  "id": 0,
  "number": 0,
  "percent": 0
}
```
