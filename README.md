# Smart Contact Manager


Smart Contact Manager is a comprehensive contact management application that allows users to manage their contacts effortlessly. The application supports adding, editing, deleting, and generating reports of contacts. It features seamless user authentication via OAuth with Google and GitHub, ensuring secure and streamlined access. The application also includes email validation before sign-up to enhance security.

## Features

- **Contact Management**: Add, edit, delete, and generate reports of contacts.
- **OAuth Authentication**: Secure login with Google and GitHub accounts.
- **Email Validation**: Validate email addresses before sign-up for improved security.
- **User-Friendly Interface**: Built with Tailwind CSS and Flowbite for a responsive and modern UI.

## Technologies Used

- **Backend**: Java, Spring Boot, Spring MVC, Spring Security
- **Frontend**: Thymeleaf, Tailwind CSS, Flowbite
- **Database**: PostgreSQL
- **Cloud Services**: AWS
- **OAuth**: Google, GitHub

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- PostgreSQL
- An AWS account (optional for cloud deployment)
- Google and GitHub developer accounts for OAuth configuration

### Installation

1. **Clone the repository**:
   bash 
   ```
   git clone https://github.com/nikhillab/smart-contact-manager-spring-boot
   cd smart-contact-manager-spring-boot 
   ```

### Configure the database:

Create a PostgreSQL database and update the application.properties file with your database credentials.
properties
```
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
```

### Set up OAuth credentials:s

Obtain OAuth credentials from Google and GitHub, and update the application.properties with your client IDs and secrets.
```
    spring.security.oauth2.client.registration.google.client-id=your-google-client-id
    spring.security.oauth2.client.registration.google.client-secret=your-google-client-secret
    spring.security.oauth2.client.registration.github.client-id=your-github-client-ids
    spring.security.oauth2.client.registration.github.client-secret=your-github-client-secret
```

### Build the project:

```mvn clean install```
Run the application:

bash
```
mvn spring-boot:run
Access the application:

Open your browser and navigate to http://localhost:8080.
```
### Deployment
AWS Deployment
To deploy the application on AWS:

### Package the application as a JAR file:


bash 
```
mvn clean package
```
Deploy the JAR file to an AWS EC2 instance or use AWS Elastic Beanstalk.

Configure your environment variables for the database and OAuth credentials.

### Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure your code follows the project's coding standards and includes appropriate tests.