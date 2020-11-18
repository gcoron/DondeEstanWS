INSERT INTO USERS (name, lastName, numberId, email, username, password) VALUES ("user1", "test1", 12345678, "user1@user.com", "user1test1", "user1test1");

INSERT INTO USERS (name, lastName, numberId, email, username, password) VALUES ("user2", "test2", 87654321, "user2@user.com", "user2test2", "user2test2");

INSERT INTO USERS_OBSERVEES (privacyKey, companyName, licensePlate, carRegistration, userId) VALUES ("user1test1.12345678", "company", "ABC123", "111", 1);

INSERT INTO OBSERVERS_USERS (childsName, userId, userObserveeIdFK) VALUES ("Child", 2, 1);

INSERT INTO ADDRESSES (street, number, floor, apartament, zipCode, city, state, country, latitude, longitude, observerUserIdFK) VALUES ("street1", 1234, "10", "A", 3000, "city", "state", "country", "100", "120", 2);

INSERT INTO ADDRESSES (street, number, floor, apartament, zipCode, city, state, country, latitude, longitude, observerUserIdFK) VALUES ("street2", 4321, "5", "A", 3000, "city", "state", "country", "110", "110", 2);

INSERT INTO LOCATIONS (dayHour, latitude, longitude, userObserveeIdFK) VALUES ("2020/10/10 12:00:00", "100", "120", 1);

INSERT INTO MESSAGES (description, observerUserIdFK, userObserveeIdFK) VALUES ("Message1", 2, 1);

INSERT INTO MESSAGES (description, observerUserIdFK, userObserveeIdFK) VALUES ("Message2", 2, 1);

INSERT INTO NOTIFICATIONS (title, description, userObserveeIdFK) VALUES ("title Notification1", "Notification1", 1);

INSERT INTO NOTIFICATIONS (title, description, userObserveeIdFK) VALUES ("title Notification2", "Notification2", 1);

INSERT INTO ARRIVALS (dayHour, userObserveeIdFK, observerUserIdFK, addressIdFK, locationIdFK) VALUES ("2020/10/10 12:01:00", 1, 2, 1, 1);
