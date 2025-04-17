# Rate Limiter Microservice 🚦

A simple and configurable rate limiter microservice built with Java and Spring Boot. It limits how many requests a specific user can make to an API within a given time window. Useful for protecting APIs from abuse or overuse.

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Maven
- REST API
- In-memory storage (Java `ConcurrentHashMap`)

> Redis integration and JWT support planned as future enhancements.

---

## 📦 Features

- Enforces request limits per user (based on a time window)
- Easy to configure max requests and window duration
- Ready for microservice architecture (plug and play into other APIs)
- Stateless REST endpoint (`/api/check`)
- Clean, testable, and extensible code structure

---

## 🧪 How It Works

- Each incoming request sends a `userId` in the JSON body.
- The backend tracks the number of requests made by each user.
- If the user exceeds the configured request limit within the set time window, access is denied.

---

## 📥 Example Request

```bash
curl -X POST http://localhost:8080/api/check \
  -H "Content-Type: application/json" \
  -d '{"userId": "adil123"}'

🧱 Coming Soon
Redis-backed request tracking (for horizontal scaling)

JWT-based user authentication

Configurable via application.properties or environment variables

Cloud deployment (Docker + Fly.io or Render)

📄 License
MIT License

✍️ Author
Adil G.
www.linkedin.com/in/adilgeilani
adilgeilani@gmail.com
