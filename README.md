# Book Service

A Spring Boot microservice for managing library books. Part of a library management system.

## Tech Stack
- Spring Boot
- MongoDB
- Maven
- Java 17

## Upcoming Features
- Kafka integration for event publishing
- JWT authentication

## API Endpoints

### Books
- `POST /api/books` - Add a new book
  - Required fields: title, author, description, category, location, isbn, type
  - Returns: Book details with auto-generated ID and timestamps
  - Sample request
```
{
    "title": "Spring Boot in Action",
    "author": "Craig Walls",
    "description": "A comprehensive guide to Spring Boot",
    "location": "Shelf-A1",
    "bookType": "BOOK",
    "fine": 5.00,
    "isbn" : "9004",
    "category": "FICTION"
}
```

## Getting Started

1. Ensure MongoDB is running locally
2. Configure application.properties with application.properties.example and your MongoDB connection details
3. Run `mvn spring-boot:run`
4. Service will be available at `http://localhost:8003`

## API Gateway - nginx (without docker)
#### nginx.conf
```
...

    upstream book_service {
        server 127.0.0.1:8003;
    }
...
```

#### sites-available/api-gateway-lib-system.conf
```
...
location /book-service {
        rewrite ^/book-service(/.*)$ $1 break;
        
        proxy_pass http://book_service/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;

        proxy_connect_timeout 600;
        proxy_send_timeout 600;
        proxy_read_timeout 600;
        send_timeout 600;
    }
...
```

