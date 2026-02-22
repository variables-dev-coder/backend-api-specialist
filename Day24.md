# 🌍 API Specialist – Day 24
## 🔹 Designing Your First REST API (Paper Design)

Before touching Spring Boot, before writing a single controller:
> Good API design happens on paper.

---

### Step 1️⃣ Choose a Domain

We’ll choose: Order Management System

Why Order?
- Has state transitions (CREATED → PAID → SHIPPED → DELIVERED)
- Has relationships (User, Product)
- Has business rules
- Real-world complexity
- Great for interviews

This will stretch your thinking.

---


### 🧠 Step 2️⃣ Identify Resources (Not Tables!)

Don’t think database.

Think business concepts.

Possible resources:
- User
- Product
- Order
- Payment
- Shipment

We will focus on:
- /users
- /products
- /orders

---

### 📦 Step 3️⃣ Define Core Resource Structures

#### 1️⃣ User

{

  "id": 1,
  
  "name": "Munna",
  
  "email": "munna@email.com"
  
}

#### 2️⃣ Product

{

  "id": 100,
  
  "name": "Laptop",
  
  "price": 70000,
  
  "stock": 5
  
}

#### 3️⃣ Order

{

  "id": 500,
  
  "userId": 1,
  
  "items": [
  
    {
    
      "productId": 100,
      
      "quantity": 1
    }
    
  ],
  
  "status": "CREATED",
  
  "totalAmount": 70000
  
}

Notice:
- Order has state
- Order contains product references
- This will help with REST modeling


---


### 🧱 Step 4️⃣ Design Endpoints (Resource-Based)

Now think like REST.

#### Users

GET    /users

GET    /users/{id}

POST   /users

PUT    /users/{id}

DELETE /users/{id}

#### Products

GET    /products

GET    /products/{id}

POST   /products

PATCH  /products/{id}

DELETE /products/{id}

#### Orders

GET    /orders

GET    /orders/{id}

POST   /orders

PATCH  /orders/{id}

Notice:

We are NOT doing:

❌ /createOrder

❌ /getOrderByUser

❌ /updateStatus

We model resources properly.

### 🔁 Step 5️⃣ Model State Transitions Properly

Now the expert part.

Instead of:

POST /approveOrder

POST /cancelOrder

We do:

PATCH /orders/{id}

Body:

{

  "status": "PAID"
  
}

Why?

Because REST transfers state, not actions.

---

### 🔗 Step 6️⃣ Relationships Design

Question:

Should we do:

GET /users/1/orders

Yes — if orders are strongly tied to users.

This expresses:
> Orders belonging to a specific user

Alternative:

GET /orders?userId=1

Both are valid.

Choose based on:
- Hierarchy clarity
- Query flexibility

Senior thinking = intentional design.

---

### ⚖ Step 7️⃣ Proper HTTP Semantics

Important:

| Operation      | Method |
| -------------- | ------ |
| Fetch list     | GET    |
| Fetch single   | GET    |
| Create         | POST   |
| Replace        | PUT    |
| Partial update | PATCH  |
| Delete         | DELETE |

Never:
- POST everywhere
- Action-based URLs

---

### 🧠 Step 8️⃣ Status Codes (Professional Design)

Example:

#### POST /orders

Return:

201 Created

Location: /orders/500

GET /orders/999 (not found)

Return:

404 Not Found

Invalid input

Return:

400 Bad Request

---

### 🔐 Step 9️⃣ Authentication Strategy (Stateless)
- JWT in Authorization header
- No server-side sessions
- Each request self-contained

---

### 🚦 Step 🔟 Versioning Strategy

Choose early:

Option A:

/api/v1/orders

Option B:

Header-based versioning

Do not ignore versioning in design.

---

### 🎯 Step 11️⃣ Design Checklist (Use in Interviews)

Before coding, ask:

✔ Are URIs nouns?

✔ Are HTTP methods used correctly?

✔ Are status codes meaningful?

✔ Is it stateless?

✔ Are state transitions modeled cleanly?

✔ Is it scalable?

If yes → production-grade thinking.

---

### 🔥 Interview Killer Answer

If interviewer asks:

“How do you design a REST API?”

You say:
> “I start by identifying business resources, define their representations, model state transitions, apply proper HTTP semantics, ensure stateless communication, and design for evolvability before implementation.”

That is senior-level answer.


---


# 🌍 Designing Your First REST API (Paper Design) – Interview Q & A (Day 24)

## 1. How do you start designing a REST API?

**Answer:**
I start with paper design by:
1. Identifying business domains.
2. Defining core resources (nouns).
3. Designing resource representations.
4. Modeling state transitions.
5. Mapping operations to proper HTTP methods.
6. Defining status codes and error handling.
7. Considering versioning and scalability.

Design comes before implementation.

---

## 2. Why is paper design important before coding?

**Answer:**
Because:
- It prevents controller-first thinking.
- It ensures resource-based modeling.
- It avoids REST anti-patterns.
- It allows architectural review before implementation.

API design is architecture, not code.

---

## 3. How do you identify resources in a domain?

**Answer:**
By identifying business concepts, not database tables.

Examples:
- User
- Product
- Order
- Payment

Resources represent meaningful entities in the system.

---

## 4. Should REST resources map directly to database tables?

**Answer:**
Not necessarily.

Resources represent business concepts, while database tables represent storage structure.

A resource may:
- Combine multiple tables
- Represent computed data
- Represent aggregated views

---

## 5. How do you design endpoints for a resource?

**Answer:**
Using noun-based URIs and HTTP methods:

Example for Order:
GET    /orders
GET    /orders/{id}
POST   /orders
PATCH  /orders/{id}
DELETE /orders/{id}

---

## 6. How do you model state transitions in REST?

**Answer:**
Instead of action-based endpoints like:
POST /approveOrder

Use:
PATCH /orders/{id}

With body:
{
  "status": "APPROVED"
}

REST transfers state representations, not actions.

---

## 7. When should you use nested resources?

**Answer:**
When a strong hierarchical relationship exists.

Example:
GET /users/{id}/orders

Alternative:
GET /orders?userId=1

The choice depends on clarity and query flexibility.

---

## 8. How do you choose between PUT and PATCH?

**Answer:**
PUT → Full resource replacement  
PATCH → Partial update

Use PATCH when only certain fields change (e.g., status update).

---

## 9. How do you handle HTTP status codes properly?

**Answer:**
Examples:
- 200 OK → Successful GET
- 201 Created → Resource created
- 204 No Content → Successful delete
- 400 Bad Request → Invalid input
- 404 Not Found → Resource missing
- 500 Internal Server Error → Unexpected failure

Status codes must communicate meaning clearly.

---

## 10. How do you ensure statelessness in your design?

**Answer:**
- Use token-based authentication (e.g., JWT).
- Do not store client session on server.
- Ensure each request contains all required context.

---

## 11. How do you approach versioning in REST API design?

**Answer:**
Common approaches:
- URI versioning: /api/v1/orders
- Header-based versioning

Versioning should be planned early to support long-term evolution.

---

## 12. What common mistakes should be avoided during API design?

**Answer:**
- Action-based URLs (/getUser)
- POST everywhere
- Ignoring HTTP semantics
- Controller-driven endpoint design
- Skipping status codes

---

## 13. How do you design for scalability?

**Answer:**
- Keep APIs stateless
- Use proper HTTP methods
- Enable caching where possible
- Avoid tight coupling
- Model resources clearly

---

## 14. How do you design error responses?

**Answer:**
Provide structured error responses:

{
  "timestamp": "...",
  "status": 400,
  "error": "Bad Request",
  "message": "Email is required",
  "path": "/users"
}

Errors must be predictable and machine-readable.

---

## 15. How do you handle relationships between resources?

**Answer:**
Options:
- Nested resources
- Query parameters
- Hypermedia links (HATEOAS)

The choice depends on system complexity and coupling requirements.

---

## 16. What is the difference between resource identity and representation?

**Answer:**
Resource identity → URI (e.g., /orders/10)  
Representation → JSON/XML describing its state

Identity remains constant; state changes over time.

---

## 17. How do you design an order management REST API?

**Answer:**
Identify resources:
- /users
- /products
- /orders

Define representations.
Model state transitions.
Use proper HTTP semantics.
Return correct status codes.
Ensure stateless communication.

---

## 18. How would you explain your REST design approach in one sentence?

**Answer:**
“I design REST APIs by modeling business resources first, defining their state representations, mapping operations to proper HTTP semantics, and ensuring stateless, scalable communication.”

---

## 19. What separates a junior API design from a senior one?

**Answer:**
Junior:
- Controller-first
- Action-based URLs
- POST everywhere

Senior:
- Resource-first
- State-driven modeling
- Proper HTTP semantics
- Designed before coding

---

## 20. Interview Power Line (Use This)

**Answer:**
“A well-designed REST API is modeled around business resources and state transitions, not service methods, with intentional use of HTTP semantics and scalability principles.”


