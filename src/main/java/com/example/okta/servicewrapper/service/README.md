# 🔐 Okta Integration with Spring Boot + Feign Client

This project demonstrates how to integrate **Okta authentication and authorization** into a **Spring Boot microservice** that communicates with other services via **OpenFeign**.

---

## 🚀 Overview
- Secure REST APIs using Okta OAuth 2.0 and JWT
- Propagate user tokens between microservices using Feign interceptors
- Works with Spring Cloud OpenFeign and Spring Security
- Easy configuration with Okta Developer Account

---

## 🧰 Prerequisites
Before you start, make sure you have:
- Java 17 or later  
- Maven or Gradle  
- An [Okta Developer Account](https://developer.okta.com/signup/)  
- Spring Boot 3.x project

---

## ⚙️ Configuration Steps

### 1. Create an Application in Okta
1. Log in to your Okta Developer Console.  
2. Navigate to **Applications → Create App Integration**.  
3. Choose **OIDC - OpenID Connect** and **Web Application**.  
4. Add your callback URL (for example `http://localhost:8080/login/oauth2/code/okta`).  
5. Save the app and note the following details:
   - Client ID  
   - Client Secret  
   - Okta Domain (for example `https://dev-xxxxxxx.okta.com`)

---

### 2. Add Dependencies
Add the following dependencies to your project build file:
- spring-boot-starter-oauth2-client  
- spring-boot-starter-security  
- spring-cloud-starter-openfeign

---

### 3. Configure Application Properties
In your application configuration file, specify:
- Okta issuer URI  
- Client ID and Secret  
- Token propagation configuration  
- Feign client URLs for downstream services

---

### 4. Implement Feign Client
Define Feign interfaces for calling other microservices (for example, an Inventory or Order service).  
Ensure they use Feign’s interceptor to attach the OAuth2 access token in the Authorization header of each outgoing request.

---

### 5. Create a Feign Request Interceptor
Implement a custom Feign interceptor that retrieves the current authentication token from the security context and sets it on outgoing requests.  
This ensures that downstream microservices can also validate the user’s token through Okta.

---

### 6. Secure REST Endpoints
Annotate your controllers or methods with security annotations such as:
- @PreAuthorize("hasAuthority('SCOPE_orders.read')")
- @RestController for API endpoints

Spring Security automatically verifies the access token from Okta for each request.

---

### 7. Test the Integration
Run your application and visit your secured endpoints in the browser or through Postman.  
When you hit a protected endpoint, Spring Security will redirect you to the Okta login page.  
After login, the application will exchange an access token and propagate it through Feign when communicating with other services.

---

## 🧩 Token Flow Summary
1. User logs in via Okta.  
2. Spring Boot obtains an OAuth 2.0 access token.  
3. Feign interceptor attaches the token to outbound calls.  
4. Downstream services verify the token against Okta’s public keys.  
5. Authorized responses are returned to the calling service.

---

## 📊 Example Architecture
User → Gateway / UI → Service A (Okta Authenticated) → Service B (via Feign, token propagated)  

Each service validates the same JWT token using Okta’s issuer URL.

---

## 🧠 Troubleshooting
- If you see 401 Unauthorized, check that the Okta issuer URI and audience match your access token configuration.  
- Ensure your Feign interceptor correctly retrieves and attaches the bearer token.  
- Use Okta’s JWT verifier library if you need custom token verification logic.
