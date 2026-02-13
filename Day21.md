# 🌍 API Specialist – Day 21
## 🔹 REST Anti-Patterns
### Why Most “REST APIs” Aren’t Actually REST

Today is about recognizing what NOT to do.

Bad REST design usually comes from:

- RPC thinking
- Database-first design
- Controller-first mindset
- Short-term convenience

---

### 1️⃣ Anti-Pattern: /getUser

#### ❌ What’s wrong?

GET /getUser?id=10

This is RPC disguised as REST.

You are:
- Embedding action in URL
- Ignoring HTTP semantics
- Mixing verbs into resource identifiers

REST principle:

> URI identifies resource
> 
> HTTP method defines action

### ✅ Correct Design

GET /users/10

Why better?
- Resource-based
- Predictable
- Cache-friendly
- Uniform interface compliant

---

### 2️⃣ Anti-Pattern: /updateStatus

#### ❌ What’s wrong?

POST /updateStatus


Problems:
- Action-based endpoint
- Ambiguous resource
- POST misused as universal hammer
- Not self-descriptive

This breaks uniform interface.

#### ✅ Better Design

If status belongs to order:

PATCH /orders/10

With body:

{

  "status": "SHIPPED"
  
}


Now:
- URI identifies resource
- Method expresses intent
- Body transfers state

That’s REST.

---

### 3️⃣ Anti-Pattern: POST Everywhere

This is extremely common in real-world APIs.

POST /getUsers

POST /deleteUser

POST /updateUser

POST /createUser

This means:
- HTTP semantics ignored
- No idempotency clarity
- No caching possibility
- No safe method distinction

You’ve basically turned HTTP into a tunnel.

#### Why Developers Do This
- Simplicity
- Misunderstanding HTTP
- “It works”
- Framework auto-generation habits

But architecturally, it kills:
- Visibility
- Caching
- Tooling
- Automation

#### ✅ Proper Method Usage

| Action         | Method |
| -------------- | ------ |
| Fetch          | GET    |
| Create         | POST   |
| Replace        | PUT    |
| Partial Update | PATCH  |
| Delete         | DELETE |


Using correct methods enables:
- Idempotency guarantees
- Safe retries
- Cache optimization
- Monitoring clarity

---

### 4️⃣ Anti-Pattern: Action-Based URLs

Examples:

/approveOrder

/cancelOrder

/activateUser

/deactivateAccount

/processPayment

This is RPC mindset.

REST is not:
> “Remote function call over HTTP”

REST is:
> “State transfer of resources”

#### Why Action URLs Are Dangerous
1. Hard to scale
2. Hard to version
3. Hard to cache
4. Hard to automate
5. Hard to evolve

Every new action becomes a new endpoint.

Your API surface explodes.

#### ✅ Resource-Centric Alternative

Instead of:

POST /approveOrder

Use:

PATCH /orders/{id}

With state transition:

{

  "status": "APPROVED"
  
}

Now:
- Single endpoint
- Predictable pattern
- Evolvable system

---

### 5️⃣ Deep Insight: Why These Anti-Patterns Happen

Because most developers think in:
Methods
- Service classes
- Controller functions
- Database operations

REST requires thinking in:
- Resources
- Representations
- State transitions
- HTTP semantics

It’s a mental model shift, not syntax change.

---

### 6️⃣ When Breaking REST Is Acceptable

Let’s be realistic.

Sometimes:
- Internal systems
- Rapid prototypes
- Very specific domain operations

Might justify pragmatic endpoints.

But:
> Breaking REST unknowingly is bad.
> 
> Breaking REST consciously is engineering.

---

### 7️⃣ REST vs RPC (Final Clarity)

| RPC Style           | REST Style            |
| ------------------- | --------------------- |
| Action endpoints    | Resource endpoints    |
| POST for everything | Semantic HTTP methods |
| Tight coupling      | Loose coupling        |
| Hard to evolve      | Evolvable             |


Most “REST APIs” are actually HTTP-based RPC.

And that’s fine — as long as you understand the difference.

---

### 🔥 Interview Killer Line

> “REST anti-patterns typically stem from RPC thinking, where actions are embedded in URLs and HTTP semantics are ignored, breaking uniform interface and scalability.”


---

# 🌍 REST Anti-Patterns – Interview Q & A (Day 21)

## 1. What is a REST anti-pattern?

**Answer:**
A REST anti-pattern is a design practice that violates REST principles such as resource orientation, uniform interface, and proper HTTP semantics.

These usually arise from RPC-style thinking applied over HTTP.

---

## 2. Why is `/getUser` considered a REST anti-pattern?

**Answer:**
Because:
- It embeds an action (get) in the URL.
- It violates resource-based design.
- HTTP method (GET) already expresses the action.

Correct design:
GET /users/{id}

---

## 3. Why are action-based URLs problematic?

**Answer:**
Examples:
- /approveOrder
- /cancelPayment
- /updateStatus

Problems:
- Mix actions into resource identifiers
- Break uniform interface
- Increase endpoint surface area
- Reduce evolvability

REST is resource-centric, not action-centric.

---

## 4. Why is “POST everywhere” considered a bad practice?

**Answer:**
Using POST for all operations:
- Ignores HTTP semantics
- Breaks idempotency guarantees
- Prevents caching
- Reduces observability

It effectively turns HTTP into a transport tunnel for RPC.

---

## 5. What problems arise from ignoring HTTP methods?

**Answer:**
- No clarity about safety (GET should not modify data)
- No idempotency guarantees (PUT/DELETE should be idempotent)
- Harder retries
- Monitoring and logging lose meaning

---

## 6. What is the correct alternative to `/updateStatus`?

**Answer:**
Instead of:
POST /updateStatus

Use:
PATCH /orders/{id}

With body:
{
  "status": "SHIPPED"
}

This transfers new resource state instead of invoking an action.

---

## 7. Why does action-based API design resemble RPC?

**Answer:**
Because:
- Each endpoint maps to a method call
- URLs represent operations
- HTTP becomes a transport protocol only

REST, in contrast, models resources and state transitions.

---

## 8. Why do developers often fall into REST anti-patterns?

**Answer:**
Common reasons:
- Controller-first mindset
- Service-method mapping directly to endpoints
- Lack of HTTP knowledge
- Short-term delivery pressure

---

## 9. How do REST anti-patterns affect scalability?

**Answer:**
They:
- Break caching
- Increase tight coupling
- Complicate versioning
- Prevent safe retries
- Reduce intermediary support

---

## 10. Is breaking REST always wrong?

**Answer:**
No.

In internal systems or prototypes, pragmatic deviations may be acceptable.

However, breaking REST unknowingly is poor design.
Breaking it consciously with clear trade-offs is engineering.

---

## 11. How do REST anti-patterns impact API evolution?

**Answer:**
Action-based endpoints create:
- Endpoint explosion
- Tight client-server coupling
- Versioning complexity

Resource-based design supports long-term evolution.

---

## 12. What is the relationship between REST anti-patterns and Uniform Interface?

**Answer:**
REST anti-patterns usually violate Uniform Interface by:
- Mixing verbs in URIs
- Ignoring HTTP semantics
- Breaking consistent interaction patterns

Uniform Interface is the core protection against these mistakes.

---

## 13. How would you refactor an API that uses POST everywhere?

**Answer:**
Steps:
1. Identify resource entities.
2. Map operations to correct HTTP methods.
3. Remove verbs from URLs.
4. Ensure idempotency rules are respected.
5. Align with resource-based URIs.

---

## 14. Why is `/deleteUser` worse than `DELETE /users/{id}`?

**Answer:**
Because:
- It duplicates action semantics in URL.
- It prevents standardized handling by intermediaries.
- It reduces predictability and consistency.

---

## 15. Interview Power Line (Use This)

**Answer:**
“Most REST anti-patterns stem from RPC thinking, where actions are embedded in URLs and HTTP semantics are ignored, breaking uniform interface and long-term scalability.”



