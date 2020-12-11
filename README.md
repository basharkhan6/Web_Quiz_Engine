# Web Quiz Engine
----
[![Java](https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white")](https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white") [![Spring](https://img.shields.io/badge/spring%20-%236DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white)](https://img.shields.io/badge/spring%20-%236DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white)

[![official JetBrains project](http://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub) [![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger) [![Website cv.lbesson.qc.to](https://img.shields.io/website-up-down-green-red/http/cv.lbesson.qc.to.svg)](http://cv.lbesson.qc.to/) [![MIT license](https://img.shields.io/badge/License-MIT-blue.svg)](https://lbesson.mit-license.org/)

This is a [JetBrains Academy](https://hyperskill.org) [Project](https://hyperskill.org/projects/91). Where I develop a multi-user web service for creating and solving quizzes using **REST API**, an embedded database, security, and other technologies. This is a server side ("engine") without a user interface at all.


## Usage
---
#### Register a user
To register a new user, the client needs to send a JSON with email and password via POST request to `/api/register`:
```
{
  "email": "test@gmail.com",
  "password": "secret"
}
```
*The service returns `200` (OK) status code if the registration has been completed successfully.
*If the email is already taken by another user, the service will return the `400` (Bad request) status code.



#### Accessing Service
*User need to pass **Basic Authorization header** containing email and password to access any service.


#### Create a new quiz
To create a new quiz, the client needs to send a JSON as the request's body via POST to `/api/quizzes`.
The JSON should contain the four fields:
- `title` a string, **required**;
- `text` a string, **required**;
- `options` an array of strings, **required**, should contain **at least 2** items;
 - `answer` integer index of the correct option, optional, since all options can be wrong.

Here is a new JSON quiz as an example:
```
{
  "title": "The Java Logo",
  "text": "What is depicted on the Java logo?",
  "options": ["Robot","Tea leaf","Cup of coffee","Bug"],
  "answer": 2
}
```
*The answer equals [0,2] corresponds to the first and the third item from the options array ("Americano" and "Cappuccino").
The server response is a JSON with four fields: `id`, `title`, `text` and options. Here is an example:
```
{
  "id": 1,
  "title": "The Java Logo",
  "text": "What is depicted on the Java logo?",
  "options": ["Robot","Tea leaf","Cup of coffee","Bug"]
}
```
*If the request JSON does not contain title or text, or they are empty strings (""), then the server should respond with the `400` (Bad request) status code. If the number of options in the quiz is less than 2, the server returns the same status code.

#### Get a quiz by id
To get a quiz by id, the client sends the GET request to `/api/quizzes/{id}`.
Here is a response example:
```
{
  "id": 1,
  "title": "The Java Logo",
  "text": "What is depicted on the Java logo?",
  "options": ["Robot","Tea leaf","Cup of coffee","Bug"]
}
```
*If the specified quiz does not exist, the server should return the `404` (Not found) status code.


#### Get all quizzes
To get all existing quizzes in the service, the client sends the GET request to `/api/quizzes`.
The response contains a JSON array of quizzes like the following:
```
[
  {
    "id": 1,
    "title": "The Java Logo",
    "text": "What is depicted on the Java logo?",
    "options": ["Robot","Tea leaf","Cup of coffee","Bug"]
  },
  {
    "id": 2,
    "title": "The Ultimate Question",
    "text": "What is the answer to the Ultimate Question of Life, the Universe and Everything?",
    "options": ["Everything goes right","42","2+2=4","11011100"]
  }
]
```
*If there are no quizzes, the service returns an empty JSON array: `[]`.
*In both cases, the status code is `200` (OK).

*The API should support the navigation through pages by passing the page parameter ( `/api/quizzes?page=1`).


#### Solve a quiz
To solve a quiz, the client sends the POST request to `/api/quizzes/{id}/` solve with a JSON that contains the indexes of all chosen options as the answer. This looks like a regular JSON object with key **"answer"** and value as the array:
Example: `{"answer": [0,2]}`.
*Indexes start from 0.

It is also possible to send an empty array [] since some quizzes may not have correct options.
The service returns a JSON with two fields: `success` (true or false) and `feedback` (just a string). There are three possible responses.
**1.** If the passed answer is correct:
```{"success":true,"feedback":"Congratulations, you're right!"}```
**2.** If the answer is incorrect:
```{"success":false,"feedback":"Wrong answer! Please, try again."}```
**3.** If the specified quiz does not exist, the server returns the `404` (Not found) status code.



#### Delete a quiz
A user can delete their quiz by sending the DELETE request to `/api/quizzes/{id}`.
*If the operation was successful, the service returns the `204` (No content) status code without any content.
*If the specified quiz does not exist, the server returns `404` (Not found).
*If the specified user is not the author of this quiz, the response is the `403` (Forbidden) status code.


#### Get all completions of quizzes
To getting all completions of quizzes for a specified user by sending the GET request to `/api/quizzes/completed` together with the **user auth data**.



## Technologies
----
Here I use [Spring Boot](https://spring.io/projects/spring-boot) freamework of [Java](https://www.oracle.com/java/).
* [Spring Boot Web](https://spring.io/projects/spring-boot) - Build RESTful web service.
* [Spring Boot Security](https://spring.io/guides/gs/securing-web) - Securing Web.
* [Spring Boot Data JPA](https://spring.io/guides/gs/accessing-data-jpa) - Accessing database.
* [H2](https://www.h2database.com/) - Lightweight In-Memory database written in Java.
* [IntelliJ Idea](https://www.jetbrains.com/idea/) - Awesome and Powerful IDE for Java!



## License
----
Distributed under the [MIT](https://opensource.org/licenses/MIT) License. See LICENSE for more information.



## Contact
---
[![Ask Me Anything !](https://img.shields.io/badge/Ask%20me-anything-1abc9c.svg)](https://GitHub.com/Naereen/ama)

Abul Basar - [@basharkhan6](https://facebook.com/basharkhan6) - basharkhan6@gmail.com
Project Link: [https://github.com/basharkhan6/Web_Quiz_Engine](https://github.com/basharkhan6/Web_Quiz_Engine)

[![Linkedin](https://img.shields.io/badge/linkedin%20-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white)](https://img.shields.io/badge/linkedin%20-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white) [![Stackoverflow](https://img.shields.io/badge/-Stack%20overflow-FE7A16?style=for-the-badge&logo=stack-overflow&logoColor=white)](https://img.shields.io/badge/-Stack%20overflow-FE7A16?style=for-the-badge&logo=stack-overflow&logoColor=white)
