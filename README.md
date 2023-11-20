# Java Spring Boot Opportunity Management REST API

## Author

Name: Achmad Dinofaldi Firmansyah

## Description

This is a REST API for Opportunity Management. It is built using Java Spring Boot and uses MySQL as the database.

## Requirements

- Java 8
- Maven
- MySQL

## Installation

1. Clone the repository
2. Create a database in MySQL
3. Update the database credentials in `src/main/resources/application.properties`
4. Run the application using `mvn spring-boot:run`

## Usage

The application runs on port 8080. You can use Postman to test the API.

## API Endpoints

### Authentication

- POST `/api/auth/register` - Register a new user
- POST `/api/auth/login` - Login and generate token
- DELETE `/api/auth/logout` - Logout and invalidate token

### Users

- GET `/api/users` - Get user details

### Opportunities

- GET `/api/opportunities` - Get all opportunities
- GET `/api/opportunities/{id}` - Get an opportunity by ID
- POST `/api/opportunities` - Create a new opportunity
- PUT `/api/opportunities/{id}` - Update an opportunity
- DELETE `/api/opportunities/{id}` - Delete an opportunity
- PUT `/api/opportunities/{id}/status` - Update the status of an opportunity

## API Documentation

### Authentication

#### Register a new user

##### Request

- Method : `POST`
- Endpoint : `/api/auth/register`
- Body :

```json
{
  "username": "user1",
  "password": "password",
  "name": "John Doe"
}
```

##### Response

```json
{
  "data": "OK",
  "errors": null
}
```

#### Login and generate token

##### Request

- Method : `POST`
- Endpoint : `/api/auth/login`
- Body :

```json
{
  "username": "user1",
  "password": "password"
}
```

##### Response

```json
{
  "data": {
    "token": "53e008e9-cb4e-4e27-a176-579f3b91a95d",
    "expiredAt": 1700091496971
  },
  "errors": null
}
```

#### Logout and invalidate token

##### Request

- Method : `DELETE`
- Endpoint : `/api/auth/logout`
- Headers : 
  
  ```
  X-API-TOKEN: 53e008e9-cb4e-4e27-a176-579f3b91a95d
  ```

##### Response
  
  ```json
  {
    "data": "OK",
    "errors": null
  }
  ``` 

### Users

#### Get user details

##### Request

- Method : `GET`
- Endpoint : `/api/users`
- Headers : 
  
  ```
  X-API-TOKEN: 53e008e9-cb4e-4e27-a176-579f3b91a95d
  ```

##### Response

```json
{
  "data": {
    "username": "user1",
    "name": "John Doe"
  },
  "errors": null
}
```

### Opportunities

#### Get all opportunities

##### Request

- Method : `GET`
- Endpoint : `/api/opportunities`
- Headers : 
  
  ```
  X-API-TOKEN: 53e008e9-cb4e-4e27-a176-579f3b91a95d
  ```
- Query Parameters : 
  
  ```
  page: 1
  size: 3
  ```

##### Response

```json
{
  "data": {
    "content": [
      {
        "id": "1D5B2681-14FF-9AED-2995-4E3B74E271DD",
        "company": "Egestas Inc.",
        "position": "Rhiannon Palmer",
        "description": "Tyrone Rivera",
        "link": "http://nytimes.com",
        "companyUrl": "http://twitter.com",
        "status": "applied"
      },
      {
        "id": "3AE6729A-6DFB-91C3-D54D-057018D84443",
        "company": "Consequat Foundation",
        "position": "Charles Wheeler",
        "description": "Charles Frazier",
        "link": "http://netflix.com",
        "companyUrl": "http://walmart.com",
        "status": "interested"
      },
      {
        "id": "4EC820B0-CADA-119B-49FF-5A01AFBBD29D",
        "company": "Dictum Mi Ltd",
        "position": "Lynn Meadows",
        "description": "MacKenzie Mccarthy",
        "link": "https://wikipedia.org",
        "companyUrl": "https://google.com",
        "status": "applied"
      }
    ],
    "pageable": {
      "pageNumber": 1,
      "pageSize": 3,
      "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
      },
      "offset": 3,
      "unpaged": false,
      "paged": true
    },
    "last": false,
    "totalPages": 4,
    "totalElements": 11,
    "size": 3,
    "number": 1,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "numberOfElements": 3,
    "first": false,
    "empty": false
  },
  "errors": null
}
```

#### Get an opportunity by ID

##### Request

- Method : `GET`
- Endpoint : `/api/opportunities/{id}`
- Headers : 
  
  ```
  X-API-TOKEN: 53e008e9-cb4e-4e27-a176-579f3b91a95d
  ```
- Path Variables : 
  
  ```
  id: 1D5B2681-14FF-9AED-2995-4E3B74E271DD
  ```

##### Response

```json
{
  "data": {
    "id": "1D5B2681-14FF-9AED-2995-4E3B74E271DD",
    "company": "Egestas Inc.",
    "position": "Rhiannon Palmer",
    "description": "Tyrone Rivera",
    "link": "http://nytimes.com",
    "companyUrl": "http://twitter.com",
    "status": "applied"
  },
  "errors": null
}
```

#### Create a new opportunity

##### Request

- Method : `POST`
- Endpoint : `/api/opportunities`
- Headers : 
  
  ```
  X-API-TOKEN: 53e008e9-cb4e-4e27-a176-579f3b91a95d
  ```

- Body :
  
  ```json
  {
    "company": "Egestas Inc.",
    "position": "Rhiannon Palmer",
    "description": "Tyrone Rivera",
    "link": "http://nytimes.com",
    "companyUrl": "http://twitter.com",
    "status": "applied"
  }
  ```

##### Response

```json
{
  "data": {
    "id": "1D5B2681-14FF-9AED-2995-4E3B74E271DD",
    "company": "Egestas Inc.",
    "position": "Rhiannon Palmer",
    "description": "Tyrone Rivera",
    "link": "http://nytimes.com",
    "companyUrl": "http://twitter.com",
    "status": "applied"
  },
  "errors": null
}
```

#### Update an opportunity

##### Request

- Method : `PUT`
- Endpoint : `/api/opportunities/{id}`
- Headers : 
  
  ```
  X-API-TOKEN: 53e008e9-cb4e-4e27-a176-579f3b91a95d
  ```
- Path Variables : 
  
  ```
  id: 1D5B2681-14FF-9AED-2995-4E3B74E271DD
  ```

- Body :
  
  ```json
  {
    "company": "Egestas Inc.",
    "position": "Rhiannon Palmer",
    "description": "Tyrone Rivera",
    "link": "http://nytimes.com",
    "companyUrl": "http://twitter.com",
    "status": "applied"
  }
  ```

##### Response

```json
{
  "data": {
    "id": "1D5B2681-14FF-9AED-2995-4E3B74E271DD",
    "company": "Egestas Inc.",
    "position": "Rhiannon Palmer",
    "description": "Tyrone Rivera",
    "link": "http://nytimes.com",
    "companyUrl": "http://twitter.com",
    "status": "applied"
  },
  "errors": null
}
```

#### Delete an opportunity

##### Request

- Method : `DELETE`
- Endpoint : `/api/opportunities/{id}`
- Headers : 
  
  ```
  X-API-TOKEN: 53e008e9-cb4e-4e27-a176-579f3b91a95d
  ```
- Path Variables : 
  
  ```
  id: 1D5B2681-14FF-9AED-2995-4E3B74E271DD
  ```

##### Response
  
  ```json
  {
    "data": "OK",
    "errors": null
  }
  ```

#### Update the status of an opportunity

##### Request

- Method : `PUT`
- Endpoint : `/api/opportunities/{id}/status`
- Headers : 
  
  ```
  X-API-TOKEN: 53e008e9-cb4e-4e27-a176-579f3b91a95d
  ```
- Path Variables : 
  
  ```
  id: 1D5B2681-14FF-9AED-2995-4E3B74E271DD
  ```
- Body :
  
  ```json
  {
    "status": "applied"
  }
  ```

##### Response

```json
{
  "data": {
    "id": "1D5B2681-14FF-9AED-2995-4E3B74E271DD",
    "company": "Egestas Inc.",
    "position": "Rhiannon Palmer",
    "description": "Tyrone Rivera",
    "link": "http://nytimes.com",
    "companyUrl": "http://twitter.com",
    "status": "applied"
  },
  "errors": null
}
```
