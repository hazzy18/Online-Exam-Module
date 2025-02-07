Online Examination Module
This project is an Online Examination System built using Spring Boot (backend), React.js (frontend), and MySQL (database). It provides functionality for two primary roles:
1.	Admin: Responsible for managing student profiles, questions, and exams.
2.	Student: Participates in exams by selecting and answering questions.
The system is designed to streamline the process of online examinations, providing a user-friendly interface and robust backend functionality.
Features
Admin Features:
1.	Student Profile Management:
o	Create, update, delete, and view student profiles and their information.
2.	Question Management:
o	Create and manage questions categorized into:
	Logical
	Aptitude
	Technical
3.	Exam Management:
o	View and manage all exams.
o	Create new exams by selecting questions from the available question pool.
Student Features:
1.	Exam Dashboard:
o	View available exams and choose which one to attempt.
2.	Question Navigation:
o	Navigate through the questions during an exam.
o	Answer MCQ questions by selecting options.
o	Submit programming question answers in text form.
o	Save answers before final submission.
3.	Exam Submission:
o	Submit the completed exam for evaluation.
________________________________________
Entity Structure
The system manages the following entities:
1.	Category
o	Represents categories of questions: Logical, Programming, Technical.
2.	Exam
o	Represents the details of an exam.
3.	ExamQuestion
o	Links questions to a specific exam.
4.	McqAnswerEntity
o	Stores answers to MCQ questions submitted by students.
5.	Option
o	Represents multiple-choice options for questions.
6.	ProgrammingAnswerEntity
o	Stores answers to programming questions submitted by students.
7.	QuestionEntity
o	Represents the question details and associated category.
8.	StudentExamEntity
o	Links students to exams they have taken or are currently taking and tells exam status.
9.	StudentProfileEntity
o	Represents the profiles and details of students.
________________________________________
Backend Structure
The backend is implemented in Spring Boot, with the following key components:
1.	Controllers
o	StudentProfileController:
	Manages API endpoints for student profile operations (create, read, update, delete).
o	QuestionController:
	Manages API endpoints for question operations (create, read, update, delete).
o	QuestionNavigationController:
	Provides endpoints for students to navigate questions, save answers, and submit exams.
o	ExamController:
	Manages API endpoints for exam operations.
2.	Database
o	Uses MySQL to store all entity-related data, ensuring referential integrity and optimized queries.
________________________________________
Frontend Structure
The frontend is developed using React.js, with the following functionality:
1.	Admin Interface:
o	Manage student profiles, questions, and exams using an intuitive UI.
2.	Student Interface:
o	View and select exams to attempt.
o	Navigate through exam questions, submit answers, and finalize the exam.
3.	Branching and Code Management:
o	The frontend code is available in the frontend branch.
________________________________________
GitHub Repository Structure
The repository includes two primary branches:
1.	Master Branch:
o	Contains backend code written in Spring Boot.
2.	Frontend Branch:
o	Contains frontend code developed in React.js.
________________________________________
Technologies Used
•	Spring Boot: Used for the backend to handle business logic, database interactions, and RESTful API endpoints.
•	React: Used for the frontend to create a dynamic and responsive user interface.
•	MySQL: Used as the database to store student profiles, questions, exams, and answers.
Setting Up the Project
Prerequisites
•	Java Development Kit (JDK): Ensure you have the JDK installed.
•	Maven: Ensure you have Maven installed for building the Spring Boot application.
•	Node.js and npm: Ensure you have Node.js and npm installed for managing the React frontend.
•	MySQL: Ensure you have MySQL installed and running.
Steps
1.	Backend Setup:
•	Create a new Spring Boot project using Spring Initializr or your preferred IDE.
•	Add dependencies for Spring Web, Spring Data JPA, and MySQL connector in the pom.xml file.
•	Create the necessary entities (Category, Exam, ExamQuestion, McqAnswerEntity, Option, ProgrammingAnswerEntity, QuestionEntity, StudentExamEntity, StudentProfileEntity).
•	Create repositories for each entity.
•	Create services to handle business logic (StudentProfileService, QuestionService, ExamService, QuestionNavigationService).
•	Create controllers to handle API endpoints (StudentProfileController, QuestionController, QuestionNavigationController, ExamController).
•	Configure application properties to connect to the MySQL database.
2.	Frontend Setup:
•	Create a new React application using Create React App or your preferred method.
•	Install necessary dependencies (axios, react-router-dom, bootstrap).
•	Create components for managing student profiles, questions, exams, and navigating through exam questions.
•	Create services to interact with the backend API (StudentService, QuestionService, ExamService, QuestionNavigationService).
•	Set up routing using react-router-dom to navigate between different views.
3.	Database Setup:
•	Create the necessary tables in the MySQL database to store student profiles, questions, exams, and answers.
•	Configure the application properties to connect to the MySQL database.
4.	Running the Application:
•	Start the Spring Boot application.
•	Start the React application using npm start.
•	Access the application in a browser to manage student profiles, questions, exams, and take exams.
Conclusion
The Online Exam Module provides a comprehensive solution for managing and conducting online examinations. It leverages modern technologies like Spring Boot, React, and MySQL to create a robust and user-friendly system. Admins can manage student profiles, questions, and exams, while students can take exams, navigate through questions, and submit their answers. This documentation should help you set up and understand the project's structure and functionalities.

