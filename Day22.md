# 🌍 API Specialist – Day 22

## 🔹 REST Maturity Model

#### Level 0 → Level 3

#### Where Most APIs Fail

The REST Maturity Model was introduced by Leonard Richardson.

It defines how RESTful an API actually is.

Important:
> This model is about design quality, not features.

---

### 🧱 Level 0 – The Swamp of POX

POX = Plain Old XML (or JSON)

What it looks like:

POST /api

{

  "action": "getUser",
  
  "id": 10
  
}

Everything goes through:
- One endpoint
- One HTTP method (usually POST)
- Action inside request body

Characteristics:
- HTTP is just a tunnel
- No resource modeling
- No HTTP semantics
- Pure RPC over HTTP

This is not REST.

It’s just HTTP transport for remote procedure calls.

---

### 🧱 Level 1 – Resources Introduced

Now we separate endpoints by resource.

Example:

POST /users

POST /orders

POST /products

Improvements:
- URIs represent resources
- Better structure
- Logical separation

But still:
- POST used everywhere
- No HTTP method semantics

Better than Level 0, but still weak REST.

---

### 🧱 Level 2 – HTTP Semantics (Where Most APIs Stop)

Now we properly use:
- GET
- POST
- PUT
- PATCH
- DELETE
- Status codes

Example:
- GET /users/10
- POST /users
- PUT /users/10
- DELETE /users/10

Now:
- HTTP verbs have meaning
- Idempotency matters
- Caching becomes possible
- Uniform interface respected

Most modern Spring Boot APIs live here.

And honestly?

Level 2 is good enough for most systems.

---

### 🧱 Level 3 – HATEOAS (True REST)

Now responses include hypermedia links.

Example:

{

  "id": 101,
  
  "status": "CREATED",
  
  "links": {
  
    "self": "/orders/101",
    
    "cancel": "/orders/101/cancel",
    
    "pay": "/orders/101/payment"
    
  }
  
}

Now:
- Client discovers actions dynamically
- Server controls workflow
- API evolves without breaking clients

This is true REST.

#### 📊 Visual Comparison

pics

---

### 🎯 Where Most APIs Fail

Most APIs:

✔ Reach Level 2

❌ Stop before Level 3

Why?
- HATEOAS is complex
- Frontend apps already know flows
- Dynamic discovery often unnecessary
- ROI unclear for internal systems

So most APIs are:
> REST-like, not pure REST

And that’s okay — if done intentionally.

---

### 🧠 Senior Insight

The model is not about ego.

It’s about understanding trade-offs.
- Level 0 → Avoid
- Level 1 → Structured but weak
- Level 2 → Industry standard
- Level 3 → Architecturally pure

Real-world engineering balances:
- Purity
- Practicality
- Business needs

---

### 🔥 Interview Killer Line
> “Most modern APIs reach Level 2 of the Richardson Maturity Model, correctly using resources and HTTP semantics, but stop short of Level 3 because HATEOAS adds complexity without always delivering proportional value.”

---

# 🌍 REST Maturity Model – Interview Q & A (Day 22)

## 1. What is the REST Maturity Model?

**Answer:**
The REST Maturity Model, introduced by Leonard Richardson, classifies APIs based on how closely they follow REST principles.

It defines four levels:
- Level 0 – HTTP as a tunnel (RPC-style)
- Level 1 – Resources
- Level 2 – HTTP methods + status codes
- Level 3 – HATEOAS

---

## 2. Is the REST Maturity Model an official REST requirement?

**Answer:**
No.

It is not part of Roy Fielding’s original REST definition.  
It is a practical classification model to measure REST adoption quality.

---

## 3. What is Level 0 in the REST Maturity Model?

**Answer:**
Level 0 uses HTTP as a transport tunnel.

Example:
POST /api
{
  "action": "getUser",
  "id": 10
}

Characteristics:
- Single endpoint
- Single HTTP method (usually POST)
- RPC over HTTP
- No resource modeling

---

## 4. Why is Level 0 considered poor REST design?

**Answer:**
Because:
- HTTP semantics are ignored
- No resource-based structure
- No caching or idempotency
- Tight coupling between client and server

---

## 5. What does Level 1 introduce?

**Answer:**
Level 1 introduces resource-based URIs.

Example:
POST /users
POST /orders

Improvements:
- Logical separation of resources
- Clear URI structure

But still misuses HTTP methods.

---

## 6. What is Level 2 in the REST Maturity Model?

**Answer:**
Level 2 correctly uses HTTP methods and status codes.

Example:
GET /users/10
POST /users
PUT /users/10
DELETE /users/10

Features:
- Proper HTTP semantics
- Idempotency awareness
- Caching support
- Standardized responses

---

## 7. Why is Level 2 considered the industry standard?

**Answer:**
Because it:
- Balances REST principles with practicality
- Enables scalability and caching
- Is supported by modern frameworks
- Is sufficient for most real-world systems

Most production APIs operate at Level 2.

---

## 8. What is Level 3 in the REST Maturity Model?

**Answer:**
Level 3 introduces HATEOAS (Hypermedia as the Engine of Application State).

Responses include hypermedia links that guide clients on possible next actions.

---

## 9. Why is Level 3 considered “true REST”?

**Answer:**
Because:
- Clients dynamically discover workflows
- Server controls navigation
- APIs evolve without breaking clients

This aligns closely with REST’s uniform interface constraint.

---

## 10. Why do most APIs stop at Level 2?

**Answer:**
Because:
- HATEOAS adds complexity
- Frontend apps already know workflows
- Tooling support is limited
- Cost-benefit ratio is often low

---

## 11. Does skipping Level 3 mean an API is bad?

**Answer:**
No.

An API can be well-designed, scalable, and maintainable at Level 2.

Skipping Level 3 is often a conscious engineering decision.

---

## 12. Compare all levels briefly.

**Answer:**

Level 0:
- RPC over HTTP
- No REST principles

Level 1:
- Resources introduced
- Poor HTTP usage

Level 2:
- Proper HTTP semantics
- Industry standard

Level 3:
- Hypermedia-driven
- Fully REST-compliant

---

## 13. Which level provides caching support?

**Answer:**
Level 2 and Level 3.

Because they properly use HTTP methods like GET and relevant cache headers.

---

## 14. Which level supports true evolvability?

**Answer:**
Level 3.

HATEOAS allows APIs to evolve without clients hardcoding workflows.

---

## 15. Can an API move gradually across levels?

**Answer:**
Yes.

An API can:
- Start at Level 1
- Introduce proper HTTP methods (Level 2)
- Add hypermedia later (Level 3)

The model supports incremental improvement.

---

## 16. Is Level 3 always necessary?

**Answer:**
No.

Level 3 is most valuable for:
- Public APIs
- Long-lived systems
- Unknown clients

Internal systems often remain at Level 2.

---

## 17. What is the biggest misconception about the REST Maturity Model?

**Answer:**
That Level 3 is mandatory for a “good API.”

In reality, Level 2 is sufficient for most production systems.

---

## 18. How does the REST Maturity Model relate to scalability?

**Answer:**
Higher levels:
- Use HTTP semantics correctly
- Enable caching
- Reduce coupling
- Improve evolvability

Lower levels limit scalability and interoperability.

---

## 19. How would you evaluate an API’s maturity in an interview?

**Answer:**
Check:
- Are URIs resource-based?
- Are HTTP methods used semantically?
- Are status codes meaningful?
- Are hypermedia links provided?

---

## 20. Interview Power Line (Use This)

**Answer:**
“Most modern APIs operate at Level 2 of the REST Maturity Model, correctly using resources and HTTP semantics, while Level 3 adds hypermedia for long-term evolvability.”









