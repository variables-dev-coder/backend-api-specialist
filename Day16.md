# 🌍 BLOCK 2: REST — REAL REST
## 🔹 Day 16 – What REST Actually Is

---

### 1️⃣ REST ≠ JSON (Most Important Unlearning)

Most developers think:
> ❌ “I’m sending JSON over HTTP → I built REST API”

That is wrong.

#### ✅ Reality
REST is an architectural style, not:
- Not JSON
- Not HTTP itself
- Not Spring Boot
- Not controllers

JSON is just a representation format.

REST answers how systems should interact, not what format they use.

#### 🧠 Simple analogy
Think of language vs letter:
- REST → language grammar
- JSON / XML → alphabets

You can speak REST using:
- JSON
- XML
- HTML
- Plain text

---

### 2️⃣ Who Invented REST (And Why It Matters)

REST was defined by Roy Fielding in his 2000 PhD dissertation.

He was:
- One of the creators of HTTP
- Working on large-scale distributed systems
- Trying to explain why the web scales

👉 REST is literally how the Web itself works

---

### 3️⃣ What REST Actually Tries to Solve

Before REST:
- Tight coupling
- Chatty APIs
- Stateful servers
- Versioning hell
- Poor scalability

REST focuses on:
- Scalability
- Evolvability
- Loose coupling
- Visibility
- Reliability

---

### 4️⃣ REST = Constraints (NOT FEATURES)

REST is defined by constraints.

If you break constraints → not REST

Roy Fielding defined 6 constraints:

---

### 5️⃣ REST Constraint #1 — Client–Server
#### Meaning
- Client → UI / consumer
- Server → data + logic
- They evolve independently

#### Why it matters
- Frontend can change without backend
- Backend can scale without UI changes

#### Spring Boot mapping
- Controller ≠ UI
- API ≠ View

---

### 6️⃣ REST Constraint #2 — Stateless (🔥 Interview Favorite)
#### Meaning
Server does NOT store client session state

Each request must contain:
- Authentication info
- Context
- All required data

#### ❌ Not REST

Server remembers logged-in user

#### ✅ REST
Authorization: Bearer <token>

#### Why this is powerful
- Horizontal scaling
- Load balancers work
- Failure recovery easy

👉 Stateless ≠ No auth

It means no server-side session memory

---

### 7️⃣ REST Constraint #3 — Cacheable
#### Meaning
Responses must say:
- Can this be cached?
- For how long?

#### Why
- Reduce server load
- Faster responses
- Web-scale performance

#### HTTP headers involved
- Cache-Control
- ETag
- Last-Modified

Even GET APIs without caching headers = weak REST

---

### 8️⃣ REST Constraint #4 — Uniform Interface (CORE OF REST)

This is where 90% APIs fail.

Uniform Interface has 4 sub-rules 👇

#### 4.1 Resource Identification (URI design)

REST talks about resources, not actions.

#### ❌ Bad

/getUser

/createUser

/deleteUser

#### ✅ Good

/users

/users/{id}

👉 Nouns, not verbs

#### 4.2 Manipulation via Representations

You don’t call methods.

You send representations.

{
  "name": "Munna",
  "role": "API Specialist"
}

Server interprets state change, not command.

#### 4.3 Self-Descriptive Messages

Each request/response must explain itself.

Includes:
- HTTP method
- Headers
- Media type
- Status code

If client needs out-of-band docs → weak REST

#### 4.4 HATEOAS (Most Ignored, Most Powerful)

Hypermedia As The Engine Of Application State

Meaning:
> Server tells client what it can do next

Example:

{

  "id": 10,
  
  "name": "Munna",
  
  "links": {
  
    "self": "/users/10",
    
    "update": "/users/10",
    
    "delete": "/users/10"
    
  }
  
}

👉 Client does not hardcode workflow

👉 Server controls navigation

Most APIs skip this → REST-like, not REST

---

### 9️⃣ REST Constraint #5 — Layered System

Client should NOT know:
- Is it talking to proxy?
- Gateway?
- Cache?
- Load balancer?

Why
- Security layers
- Scalability
- API gateways
- CDN support

Microservices depend heavily on this.

---

### 🔟 REST Constraint #6 — Code on Demand (Optional)

Server can send:
- Executable code (JS)
- Scripts

Browsers do this all the time.

👉 Optional

👉 Rare in APIs

👉 Still part of REST definition

### 1️⃣1️⃣ REST Maturity Levels (Richardson Model)

| Level   | Description         |
| ------- | ------------------- |
| Level 0 | HTTP as tunnel      |
| Level 1 | Resources           |
| Level 2 | HTTP verbs + status |
| Level 3 | HATEOAS (true REST) |

Most Spring Boot APIs = Level 2

### 1️⃣2️⃣ REST vs RESTful vs REST-like
- REST → strict constraints
- RESTful → follows most rules
- REST-like → JSON over HTTP only

👉 Interviews expect you to know the difference

---

### 1️⃣3️⃣ Key Expert Takeaways (Remember This)
- REST is constraint-driven, not feature-driven
- JSON is optional
- Statelessness enables scalability
- Uniform Interface is the soul of REST
- HATEOAS separates REST experts from average devs

---


# 🌍 REST Interview Questions & Answers (Expert Level)

## 1. What is REST?

**Answer:**
REST (Representational State Transfer) is an **architectural style** for designing distributed systems.  
It defines a set of **constraints** that guide how clients and servers should communicate to achieve scalability, simplicity, and loose coupling.

REST is **not a protocol, framework, or data format**.

---

## 2. Who introduced REST and why?

**Answer:**
REST was introduced by Roy Fielding in his 2000 PhD dissertation.  
He was one of the creators of HTTP and designed REST to explain **why the web scales so well**.

---

## 3. Is REST the same as JSON?

**Answer:**
No. REST ≠ JSON.

- REST → architectural style
- JSON → representation format

REST can use JSON, XML, HTML, or plain text as representations.

---

## 4. What are REST constraints?

**Answer:**
REST is defined by six constraints:
1. Client–Server
2. Stateless
3. Cacheable
4. Uniform Interface
5. Layered System
6. Code on Demand (optional)

Breaking constraints means the system is **not REST**.

---

## 5. Explain the Client–Server constraint.

**Answer:**
Client and server responsibilities are separated:
- Client → UI & user interaction
- Server → business logic & data

This allows both to evolve independently and improves scalability.

---

## 6. What does Statelessness mean in REST?

**Answer:**
Each request from client to server must contain **all information needed** to process it.

The server must not store any client session state.

---

## 7. Why is REST stateless?

**Answer:**
Statelessness enables:
- Horizontal scaling
- Load balancing
- Fault tolerance
- Easier recovery

Servers can handle any request from any client at any time.

---

## 8. Does stateless mean authentication is not allowed?

**Answer:**
No. Authentication is allowed.

Credentials (JWT, tokens) are sent with **every request**, not stored on the server as session data.

---

## 9. What does Cacheable mean in REST?

**Answer:**
REST responses must explicitly state whether they can be cached or not.

Caching improves:
- Performance
- Scalability
- Reduced server load

HTTP headers like `Cache-Control`, `ETag` are used.

---

## 10. What is the Uniform Interface constraint?

**Answer:**
Uniform Interface ensures consistency between client and server.

It has four sub-constraints:
1. Resource Identification
2. Manipulation via Representations
3. Self-descriptive Messages
4. HATEOAS

---

## 11. Why should URLs use nouns instead of verbs?

**Answer:**
URLs represent **resources**, not actions.

- ❌ `/getUser`
- ❌ `/deleteOrder`
- ✅ `/users`
- ✅ `/orders/10`

Actions are expressed using HTTP methods, not URLs.

---

## 12. What is a resource in REST?

**Answer:**
A resource is any meaningful object or concept exposed by the API:
- User
- Order
- Product

Each resource is uniquely identified using a URI.

---

## 13. What does "Manipulation via Representation" mean?

**Answer:**
Clients modify server-side resources by sending **representations** (JSON/XML), not method calls.

The server interprets the representation and updates resource state.

---

## 14. What are self-descriptive messages?

**Answer:**
Every REST request and response must contain enough information to describe:
- What action is performed
- How to process it

This includes HTTP method, headers, media type, and status code.

---

## 15. What is HATEOAS?

**Answer:**
HATEOAS (Hypermedia As The Engine Of Application State) means:
The server provides links that tell the client what actions are possible next.

Clients should not hardcode workflows.

---

## 16. Is HATEOAS mandatory in REST?

**Answer:**
Yes, for **true REST (Level 3)** compliance.

Most real-world APIs skip it and are technically REST-like, not pure REST.

---

## 17. What is the Layered System constraint?

**Answer:**
The client should not know whether it is communicating with:
- A proxy
- Gateway
- Cache
- Load balancer

This supports scalability, security, and abstraction.

---

## 18. What is Code on Demand?

**Answer:**
An optional REST constraint where the server can send executable code (e.g., JavaScript) to the client.

Mostly used in browsers, rarely in APIs.

---

## 19. What are REST maturity levels?

**Answer:**
Richardson Maturity Model:
- Level 0 → HTTP as transport only
- Level 1 → Resources
- Level 2 → HTTP verbs + status codes
- Level 3 → HATEOAS (true REST)

Most Spring Boot APIs are Level 2.

---

## 20. Difference between REST and RESTful?

**Answer:**
- REST → strict architectural definition
- RESTful → APIs that mostly follow REST principles
- REST-like → JSON over HTTP without real constraints

---

## 21. Why REST scales better than RPC?

**Answer:**
REST:
- Is stateless
- Supports caching
- Uses uniform interfaces
- Decouples client and server

RPC systems are tightly coupled and harder to scale.

---

## 22. Is REST limited to HTTP?

**Answer:**
No. REST is protocol-agnostic.

HTTP is the most popular implementation because it naturally supports REST constraints.

---

## 23. Can REST work without JSON?

**Answer:**
Yes. REST can use:
- XML
- HTML
- YAML
- Plain text

JSON is a convenience, not a requirement.

---

## 24. Why REST forbids server-side sessions?

**Answer:**
Server-side sessions:
- Break statelessness
- Reduce scalability
- Create tight coupling

REST prefers client-managed state.

---

## 25. What is the biggest mistake developers make with REST?

**Answer:**
Confusing REST with:
- JSON
- CRUD
- Spring Boot controllers

REST is about **architecture**, not implementation.

---

## 26. When should REST not be used?

**Answer:**
REST may not be ideal for:
- Real-time systems
- High-frequency chatty communication
- Streaming data

Alternatives include gRPC or WebSockets.

---

## 27. What defines a truly RESTful API?

**Answer:**
An API that:
- Follows all REST constraints
- Uses proper HTTP semantics
- Is stateless
- Supports HATEOAS
- Is cache-aware

---

## 28. Interview Summary Line (Use This)

**Answer:**
“REST is a constraint-based architectural style focused on scalability and loose coupling, not just JSON over HTTP.”
























