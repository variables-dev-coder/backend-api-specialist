# 🌍 API Specialist – Day 23

## 🔹 REST vs RPC vs GraphQL

#### Trade-offs (No Bias, Architecture Level)

This is not about which is better.

It’s about:
> Which problem are you solving?

---

### 1️⃣ REST (Resource-Oriented Architecture)
#### Core Idea
- Model system as resources
- Use HTTP semantics
- Transfer state representations

#### Strengths

✔ Mature ecosystem

✔ Caching support (HTTP-native)

✔ Scalable by design

✔ Great for public APIs

✔ Clear semantics (GET, POST, etc.)

#### Weaknesses

❌ Over-fetching / under-fetching

❌ Multiple round trips

❌ Versioning challenges

❌ Harder for complex nested data

#### Best For
- Public APIs
- Microservices
- CRUD-heavy systems
- Systems requiring caching
- Web-scale applications

---

### 2️⃣ RPC (Remote Procedure Call)
#### Core Idea

Call a remote function like a local one.

Example:

approveOrder(orderId)

getUserById(id)

Transport can be:
- HTTP (JSON-RPC)
- gRPC
- Binary protocols

#### Strengths

✔ Simple mental model

✔ Clear action-based design

✔ High performance (gRPC)

✔ Strong contracts (Protobuf)

#### Weaknesses

❌ Tight coupling

❌ Harder evolvability

❌ Less cache-friendly

❌ HTTP semantics often ignored


#### Best For
- Internal microservices
- High-performance systems
- Real-time systems
- Low-latency communication

gRPC especially shines here.

---

### 3️⃣ GraphQL (Query-Based API)
#### Core Idea

Client specifies exactly what data it needs.

Single endpoint:

POST /graphql


Client sends query structure.

#### Strengths

✔ No over-fetching

✔ Single request for complex data

✔ Strong type system

✔ Great for frontend-heavy apps


#### Weaknesses

❌ Harder caching

❌ Query complexity attacks

❌ More backend logic required

❌ Less natural HTTP semantics

#### Best For
- Frontend-driven apps
- Complex UI dashboards
- Mobile apps
- Rapid iteration teams

#### 🔍 Architectural Comparison

| Feature          | REST           | RPC               | GraphQL       |
| ---------------- | -------------- | ----------------- | ------------- |
| Design Model     | Resource-based | Action-based      | Query-based   |
| Coupling         | Medium         | High              | Medium        |
| Caching          | Excellent      | Limited           | Complex       |
| Performance      | Good           | Excellent (gRPC)  | Good          |
| Evolvability     | Good           | Moderate          | Good          |
| Tooling Maturity | Very High      | High              | High          |
| Best Use         | Public APIs    | Internal services | Frontend apps |


#### 🧠 Deep Insight

##### REST optimizes for:
- Web scale
- Caching
- Long-term evolution

##### RPC optimizes for:
- Performance
- Clarity of operations
- Internal communication

##### GraphQL optimizes for:
- Frontend flexibility
- Data efficiency
- Rapid UI iteration

Different philosophies.

Different trade-offs.

#### 🎯 When NOT to Use Each

❌ Don’t use REST for real-time streaming

❌ Don’t use RPC for public APIs without careful versioning

❌ Don’t use GraphQL if caching simplicity is critical

## 🔥 Interview Killer Line
> “REST, RPC, and GraphQL solve different problems — REST optimizes for scalable web architecture, RPC for performance and clarity of operations, and GraphQL for client-driven data flexibility. The right choice depends on system constraints, not preference.”


---


# 🌍 REST vs RPC vs GraphQL – Interview Q & A (Day 23)

## 1. What is the fundamental difference between REST, RPC, and GraphQL?

**Answer:**

- REST → Resource-oriented architecture using HTTP semantics.
- RPC → Action-oriented communication calling remote procedures.
- GraphQL → Query-oriented API where client specifies required data.

Each follows a different design philosophy.

---

## 2. What problem does REST solve best?

**Answer:**
REST is optimized for:
- Scalable web architecture
- Resource-based modeling
- HTTP-native caching
- Public API design
- Long-term evolvability

---

## 3. What problem does RPC solve best?

**Answer:**
RPC is optimized for:
- Clear action-based communication
- High-performance internal services
- Strongly typed contracts (e.g., gRPC with Protobuf)
- Low-latency microservice communication

---

## 4. What problem does GraphQL solve best?

**Answer:**
GraphQL is optimized for:
- Client-driven data requirements
- Avoiding over-fetching and under-fetching
- Complex UI-driven applications
- Single-request nested data retrieval

---

## 5. Why is REST considered more cache-friendly than GraphQL?

**Answer:**
Because REST:
- Uses HTTP semantics correctly
- Has clear GET endpoints
- Supports standard HTTP caching mechanisms

GraphQL typically uses POST to a single endpoint, making caching more complex.

---

## 6. Why is RPC considered tightly coupled?

**Answer:**
Because:
- Clients depend on specific procedure names
- Method signatures must remain stable
- Changes often require coordinated deployment

It mirrors local method calls across services.

---

## 7. Why does REST avoid action-based URLs?

**Answer:**
Because REST models resources, not operations.

Actions are expressed using HTTP methods, not embedded in URIs.

---

## 8. What is the main criticism of REST in frontend-heavy applications?

**Answer:**
REST can cause:
- Over-fetching (extra data returned)
- Under-fetching (multiple requests required)
- Increased round trips

GraphQL addresses this issue.

---

## 9. Why can GraphQL introduce performance risks?

**Answer:**
Because:
- Complex nested queries may overload the backend
- Query depth must be controlled
- Query cost analysis is required
- Caching is harder to implement

---

## 10. When should you choose RPC over REST?

**Answer:**
Choose RPC when:
- Services are internal
- Performance is critical
- Low-latency communication is required
- Strong contracts are important

gRPC is common in microservices.

---

## 11. When should you choose REST over RPC?

**Answer:**
Choose REST when:
- Building public APIs
- Leveraging HTTP caching
- Designing resource-based systems
- Prioritizing loose coupling

---

## 12. When should you choose GraphQL over REST?

**Answer:**
Choose GraphQL when:
- UI requirements change frequently
- Clients need flexible data structures
- Reducing network round trips is important
- Multiple frontend teams consume the same API

---

## 13. Is GraphQL a replacement for REST?

**Answer:**
No.

GraphQL is an alternative architectural style designed for different use cases. Many systems combine REST and GraphQL.

---

## 14. Can REST and RPC coexist in the same system?

**Answer:**
Yes.

Example:
- Public API → REST
- Internal microservices → gRPC (RPC)
- Frontend gateway → GraphQL

Architecture often mixes approaches.

---

## 15. Which approach scales better?

**Answer:**
All can scale if designed properly.

- REST scales well via HTTP caching and statelessness.
- RPC scales well in high-performance internal systems.
- GraphQL scales well for frontend-driven environments.

Scalability depends on implementation quality.

---

## 16. Which approach is best for microservices?

**Answer:**
Common pattern:
- External communication → REST
- Internal communication → gRPC (RPC)
- UI layer → GraphQL (optional)

Microservices often use hybrid architectures.

---

## 17. What is the biggest misconception about these architectures?

**Answer:**
That one is universally superior.

In reality, architecture is about trade-offs and context-driven decisions.

---

## 18. How does coupling differ across them?

**Answer:**
- REST → Medium coupling (resource-based)
- RPC → Higher coupling (method-based)
- GraphQL → Medium coupling (schema-based)

---

## 19. How does versioning differ?

**Answer:**
REST:
- URI versioning or header-based versioning

RPC:
- Method versioning required

GraphQL:
- Schema evolution with deprecation

---

## 20. Interview Power Line (Use This)

**Answer:**
“REST, RPC, and GraphQL address different architectural needs — REST prioritizes scalable web principles, RPC focuses on performance and explicit operations, and GraphQL enables client-driven data flexibility. The correct choice depends on system constraints, not preference.”





