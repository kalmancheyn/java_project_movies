# Movie Database and Management System

This Java Spring project provides a comprehensive Movie Database and Management System, allowing users to manage movies, actors, genres, reviews, and more.

## Features

### Database and Persistence Layer

- Utilizes a Hibernate H2 database to store movie-related information.
- Implements the following tables:
  - Movies
  - Actors
  - Genres
  - Reviews
- Establishes relationships using foreign keys (e.g., linking actors to movies, genres to movies).
- Uses Flyway for database versioning and migration.

### API Endpoints

#### Movies
- Allows CRUD operations (GET, DELETE, POST, PUT) for movie management.
- Implements pagination for listing movies with customizable limit and sort parameters.

#### Actors
- Enables CRUD operations for managing actors.

#### Genres
- Supports CRUD operations for genre management.

#### Reviews
- Facilitates CRUD operations for managing movie reviews.

### Security and User Management

- Implements basic authentication
- Defines two roles: admin and regular user.
- Applies role-based access control to endpoints

## Installation and Usage

To install and run this project:

1. Clone the repository.
2. Download IntelliJ IDE
3. Open and run the Spring Application (On the first run you will need to enable annotation processing because of the Lombok extension. If you don't have it install it first)


For detailed API documentation I created a "test-files" folder which contains a  Postman collection JSON file, that you can easily import to Postman and test all the endpoints. (If you want to run AddMoviesFromXlsxFile you have to import to the Body -> Value to the movies.xlsx file which you can find in the "test-files" directory)

