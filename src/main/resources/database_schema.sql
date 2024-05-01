CREATE TABLE driver (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(255) UNIQUE NOT NULL,
    created_at DATE NOT NULL,
    current_latitude DOUBLE,
    current_longitude DOUBLE,
    segment_id VARCHAR(255),
    shard_id INT
);

CREATE TABLE cab (
    id SERIAL PRIMARY KEY,
    car_type VARCHAR(255),
    registration_number VARCHAR(255),
    driver_id VARCHAR(255) REFERENCES driver(id)
);

CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(255) UNIQUE NOT NULL,
    created_at DATE NOT NULL
);

CREATE TABLE trip (
    id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customer(id),
    driver_id VARCHAR(255) REFERENCES driver(id),
    source_latitude DOUBLE,
    source_longitude DOUBLE,
    destination_latitude DOUBLE,
    destination_longitude DOUBLE,
    created_at DATE NOT NULL,
    status VARCHAR(255),
    payment_id INT UNIQUE
);

CREATE TABLE payment (
    id SERIAL PRIMARY KEY,
    trip_id INT UNIQUE REFERENCES trip(id),
    method VARCHAR(255),
    amount DOUBLE,
    created_at DATE NOT NULL
);

CREATE TABLE rating (
    id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customer(id),
    driver_id VARCHAR(255) REFERENCES driver(id),
    trip_id INT UNIQUE REFERENCES trip(id),
    rating INT,
    feedback TEXT
);
