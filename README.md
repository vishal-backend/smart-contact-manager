# smart-contact-manager
A secure contact management REST API built with spring boot allowing user to manage their personal contact.Each user can register, and then add, update, search, and delete contacts linked to their account. The API supports pagination and sorting on all list endpoints.
<br>

---
##  Features

- User management (CRUD)
- Contact management (CRUD)
- Search contacts by name
- Pagination & Sorting
- RESTful API design
- Clean layered architecture (Controller → Service → Repository)

  ---

# Tech Stack
<br>
•Java 17+ with Spring Boot
<br>
•Spring Data JPA + Hibernate
<br> 
•MySQL
<br> 
•Bean Validation 
<br>
•Maven for dependency management
<br>
•Git & Github for version control
<br>

---

## Live API
-Base URL: https://smart-contact-manager-production-84ff.up.railway.app
---

##  API Endpoints

### User APIs
- GET /users
- GET /users/{id}
- POST /users
- PUT /users/{id}
- DELETE /users/{id}

### Contact APIs
- GET /contacts/{id}
- GET /contacts/user/{userId}
- GET /contacts/search?name=abc
- POST /contacts/user/{userId}
- PUT /contacts/{id}
- DELETE /contacts/{id}

  ---

