# 🔹 API Specialist – Day 08  
## HTTP Caching Basics (Expert Level)

---

## 1. What Caching Really Means

Caching is **not just storing data for speed**.

**Correct definition:**  
Caching means **reusing a previously generated HTTP response** when it is still valid, instead of recomputing it.

Key points:
- Caching is about **response reuse**
- Controlled by **HTTP headers**
- Managed by **clients, proxies, CDNs**
- Happens **outside your application code**

If a response is cacheable, infrastructure may serve it **without hitting your server**.

---

## 2. Why HTTP Caching Exists

HTTP caching solves real system problems:

- Reduces latency
- Reduces server load
- Saves bandwidth and cost
- Improves availability during traffic spikes
- Improves user experience

Most large-scale performance improvements come from **correct caching**, not faster code.

---

## 3. Browser Cache vs Proxy Cache

Caching behavior depends on **where the cache lives**.

---

### Browser Cache (Private Cache)

Characteristics:
- Stored on client device
- User-specific
- Safe for personalized data

Typical usage:
- User profile UI data
- Static frontend assets
- Client-side configuration

Controlled using:


Cache-Control: private


---

### Proxy / CDN Cache (Shared Cache)

Characteristics:
- Shared across users
- Extremely fast
- Very dangerous if misused

Typical usage:
- Public APIs
- Read-heavy endpoints
- Content APIs

Controlled using:

Cache-Control: public


---

### Key Rule

- **private** → per user  
- **public** → shared across users

---

## 4. Cache-Control Header (Most Important)

`Cache-Control` defines caching behavior.

Common directives:

Cache-Control: max-age=60

Response is fresh for 60 seconds.

Cache-Control: no-cache

Cache may store but **must revalidate before reuse**.

Cache-Control: no-store

Do not store at all (security-critical).

Cache-Control: public

Response may be cached by shared caches.



Cache-Control: private

Only client cache allowed.

`Cache-Control` is a **contract with infrastructure**, not a hint.

---

## 5. Freshness vs Validation

Caching has two models.

---

### Freshness-Based Caching

Response reused without contacting server.

Controlled by:
- `Cache-Control: max-age`
- `Expires`

---

### Validation-Based Caching

Cache checks with server before reuse.

Uses:
- `ETag` + `If-None-Match`
- `Last-Modified` + `If-Modified-Since`

Server may respond:


304 Not Modified


`304` is a **performance optimization**, not an error.

---

## 6. When NOT to Cache (Critical)

Caching the wrong response is worse than no caching.

---

### Never cache when:

1. Response is user-specific  
   Examples:
   - `/me`
   - `/account`
   - `/orders`

2. Response contains sensitive data  
   - Tokens
   - PII
   - Financial data

3. Response changes frequently  
   - Real-time dashboards
   - Live metrics

4. Request has side effects  
   - POST
   - PUT
   - PATCH
   - DELETE

Use:


Cache-Control: no-store


---

## 7. Idempotency vs Cacheability (Common Trap)

These are different concepts.

| Concept | Meaning |
|------|--------|
Idempotent | Same request → same effect |
Cacheable | Response can be reused |

Examples:
- `PUT` → idempotent but NOT cacheable
- `GET` → cacheable (sometimes)

Only **safe, read-only** requests should be cached.

---

## 8. Deciding Which APIs Should Be Cacheable

Cacheability must be decided **per endpoint**.

---

### Good Candidates for Caching

| API | Reason |
|---|---|
GET /products | Public, read-heavy |
GET /categories | Rarely changes |
GET /countries | Static data |
GET /config | Shared configuration |

Example:


Cache-Control: public, max-age=300


---

### Conditional Caching (Best Practice)

| API | Strategy |
|---|---|
GET /articles | Cache + ETag |
GET /search | Short TTL |

Example:


Cache-Control: public, max-age=60
ETag: "v123"


---

### Never Cache

| API | Reason |
|---|---|
POST /login | Security |
GET /me | User-specific |
GET /balance | Financial |
POST /orders | Side effects |

Example:


Cache-Control: no-store


---

## 9. Security Disaster Scenario

If you do this:



Cache-Control: public
Authorization: Bearer token


Result:
- One user’s response cached
- Another user may receive it
- Data leakage

This is a **critical production incident**.

---

## 10. Interview Decision Framework

To decide cacheability, ask:

- Is the endpoint read-only?
- Is the response user-specific?
- Is the data sensitive?
- How often does it change?
- Can stale data be tolerated?

Only then choose:
- public / private
- max-age
- validation strategy

---

## Final Expert Takeaways

- Caching is an API design decision
- Headers control caching, not application code
- Browser and proxy caches behave differently
- Wrong caching causes security bugs
- Every API must explicitly decide cache strategy


# 🔹 API Specialist – Day 08  
## Interview Questions & Answers  
### Topic: HTTP Caching Basics

---

### Q1: What does HTTP caching really mean?
**Answer:**  
HTTP caching means reusing a previously generated HTTP response when it is still valid, instead of recomputing it. It is controlled by HTTP headers and handled by clients, proxies, and CDNs.

---

### Q2: Is caching an optimization or an API design decision?
**Answer:**  
Caching is an API design decision. Incorrect caching can cause security issues, stale data bugs, and data leaks, making it more critical than performance optimization.

---

### Q3: What is the difference between browser cache and proxy cache?
**Answer:**  
Browser cache is private and user-specific, stored on the client device.  
Proxy/CDN cache is shared across users and must only store public, non-sensitive responses.

---

### Q4: What does `Cache-Control: public` mean?
**Answer:**  
It allows the response to be cached by shared caches such as proxies or CDNs and reused across multiple users.

---

### Q5: What does `Cache-Control: private` mean?
**Answer:**  
It allows caching only on the client side and prevents shared caches from storing the response.

---

### Q6: What is the purpose of `Cache-Control: no-store`?
**Answer:**  
It instructs all caches not to store the response at all. It is used for sensitive data like authentication tokens or financial information.

---

### Q7: What is the difference between `no-cache` and `no-store`?
**Answer:**  
`no-cache` allows storing the response but requires revalidation before reuse.  
`no-store` forbids storing the response entirely.

---

### Q8: What is freshness in HTTP caching?
**Answer:**  
Freshness determines how long a cached response can be reused without contacting the server, controlled by `max-age` or `Expires`.

---

### Q9: What is validation-based caching?
**Answer:**  
Validation-based caching requires the cache to check with the server before reuse, using headers like `ETag` or `Last-Modified`.

---

### Q10: What does `304 Not Modified` mean?
**Answer:**  
It indicates that the cached response is still valid and can be reused. It is a performance optimization, not an error.

---

### Q11: Are all GET requests cacheable?
**Answer:**  
No. Only GET requests that are safe, non-user-specific, and non-sensitive should be cached.

---

### Q12: Can POST responses be cached?
**Answer:**  
By default, no. POST requests usually have side effects and should not be cached.

---

### Q13: What is the difference between idempotency and cacheability?
**Answer:**  
Idempotency means repeating a request produces the same effect.  
Cacheability means the response can be reused.  
An operation can be idempotent but not cacheable.

---

### Q14: Why should user-specific APIs not be cached publicly?
**Answer:**  
Because shared caches may serve one user’s data to another user, causing data leakage and security breaches.

---

### Q15: What is a common caching security disaster scenario?
**Answer:**  
Marking authenticated responses as `public`, causing shared caches to store and serve private user data.

---

### Q16: How do you decide whether an API endpoint should be cached?
**Answer:**  
By evaluating whether the endpoint is read-only, user-specific, sensitive, frequently changing, and whether stale data is acceptable.

---

### Q17: Why is caching considered an infrastructure concern?
**Answer:**  
Because caching is enforced by browsers, proxies, and CDNs based on HTTP headers, often without application involvement.

---

### Q18: Can error responses be cached?
**Answer:**  
Yes, but only if explicitly allowed via cache headers. Most error responses should not be cached by default.

---

### Q19: Why is `Cache-Control` more important than application logic for caching?
**Answer:**  
Because infrastructure components rely on HTTP headers, not application code, to decide caching behavior.

---

### Q20: What is the biggest mistake developers make with HTTP caching?
**Answer:**  
Caching without explicitly deciding cacheability per endpoint, leading to stale data bugs or security vulnerabilities.

---

## Final Interview Insight

> Caching improves performance, but incorrect caching breaks correctness and security.
> Strong APIs treat caching as a first-class design concern.


