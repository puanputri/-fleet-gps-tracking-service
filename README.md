The application uses PostgreSQL as the database. Make sure you have PostgreSQL installed and create a database named gps_tracking.
The application uses MapStruct for object mapping between entities and DTOs. You'll need to configure your IDE to support annotation processing.
All endpoints include validation using Jakarta Bean Validation annotations.
The application includes proper error handling with custom exception mapping.
The GPS coordinates validation ensures latitude is between -90 and 90, longitude is between -180 and 180, and speed is between 0 and 500 km/h.
To run the application:
bash./mvnw quarkus:dev

The API endpoints are:

POST /api/gps - Submit GPS log
GET /api/vehicles/{id}/last-location - Get latest location
GET /api/vehicles/{id}/history?from=&to= - Get location history
Standard CRUD endpoints for vehicles management


Make sure to handle the date format properly when making requests to the history endpoint. The expected format is ISO-8601 (e.g., "2023-10-15T10:30:00").