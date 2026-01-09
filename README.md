# Sports-Venue-Availability-Booking-Service

1.Assumptions

Booking is per slot
→ 1 booking = 1 slot.

Slot time is immutable once booked
→ Start/end time cannot be changed after booking.

Cancelled bookings free the slot immediately
→ Slot becomes available again.

Sports are NOT hardcoded
→ Sports are loaded dynamically from the public API:

https://stapubox.com/sportslist/

Sport selection is validated using DB foreign keys
→ Only valid sports from API can be used.

Single MySQL instance
→ No external cache or distributed DB.

Concurrency safety is handled at DB + transaction level
→ No double booking is possible.


2. APIs

1️ Create Venue

POST /venues

Request

{
  "name": "Elite Badminton Arena",
  "city": "Bangalore",
  "sportId": 7020104
}


Sample curl

curl -X POST http://localhost:8080/venues \
-H "Content-Type: application/json" \
-d '{
  "name": "Elite Badminton Arena",
  "city": "Bangalore",
  "sportId": 7020104
}'

2️ List Venues

GET /venues

Sample curl

curl http://localhost:8080/venues

3️ Create Slot for Venue

POST /venues/{venueId}/slots

Request

{
  "startTime": "2026-01-10T06:00:00",
  "endTime": "2026-01-10T07:00:00"
}


Sample curl

curl -X POST http://localhost:8080/venues/1/slots \
-H "Content-Type: application/json" \
-d '{
  "startTime": "2026-01-10T06:00:00",
  "endTime": "2026-01-10T07:00:00"
}'


Overlapping slots for the same venue are rejected.

4️ Check Available Venues

GET /venues/available

Query Parameters

startTime

endTime

sportId

Sample curl

curl "http://localhost:8080/venues/available?startTime=2026-01-10T06:00:00&endTime=2026-01-10T07:00:00&sportId=7020104"

5️ Book Slot

POST /bookings

Request

{
  "slotId": 1,
  "userName": "Akshay"
}


Sample curl

curl -X POST http://localhost:8080/bookings \
-H "Content-Type: application/json" \
-d '{
  "slotId": 1,
  "userName": "Akshay"
}'


Double booking of the same slot is prevented.

6️ Cancel Booking

PUT /bookings/{id}/cancel

Sample curl

curl -X PUT http://localhost:8080/bookings/1/cancel


3. Running the Application

   docker-compose up --build


Schema is inside the code in file path src/main/resources/schema.sql
App → http://localhost:8080

MySQL → localhost:3306
