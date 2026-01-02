# API Specialist – Day 04 (HTTP Methods: Surface vs Reality)

Most developers learn this:

| Method | Meaning        |
| ------ | -------------- |
| GET    | Fetch data     |
| POST   | Create data    |
| PUT    | Update data    |
| PATCH  | Partial update |
| DELETE | Delete data    |

❌ This is incomplete and dangerous knowledge.

HTTP methods are semantic contracts, not CRUD shortcuts.

---

## 2️⃣ THE REAL AXES OF HTTP METHODS

Every HTTP method is defined by 3 core properties:

🔹 A. Safe
> Does this method change server state?

🔹 B. Idempotent
> If I call it 10 times, is the result the same as calling it once?

🔹 C. Cacheable
> Can intermediaries cache the response?

These are protocol guarantees, not suggestions.


---

## 3️⃣ SAFE vs UNSAFE METHODS (CRITICAL CONCEPT)

✅ SAFE METHODS
> Must NOT change server state
- GET
- HEAD
- OPTIONS

📌 Even logging, counters, or analytics technically violate safety (real systems still do it, but conceptually it’s unsafe).

Why safety matters?
- Browsers prefetch GET
- Crawlers auto-hit GET
- Retry logic assumes GET is harmless

👉 If GET mutates data → data corruption risk

❌ UNSAFE METHODS
> Change server state
- POST
- PUT
- PATCH
- DELETE

---

## 4️⃣ IDEMPOTENCY (MOST MISUNDERSTOOD)
Definition:
> Multiple identical requests → same final state

Examples:

PUT (Idempotent)

PUT /users/10

{

  "name": "Munna"
  
}


Call it 1 time or 100 times → user name is still "Munna".

POST (Non-idempotent)

POST /users

{

  "name": "Munna"
  
}


Every call creates a new user.

📌 Idempotent does NOT mean same response

It means same state.

---

## 5️⃣ METHOD-BY-METHOD DEEP REALITY

🔹 GET – READ ONLY (ABSOLUTE RULE)

Correct Use
- Fetch resources
- Apply filters
- Pagination
- Sorting

Rules
- ❌ No body (practically ignored)
- ✅ Query params allowed
- ✅ Cacheable
- ✅ Safe
- ✅ Idempotent

❌ WRONG USAGE (COMMON SIN)

GET /users/delete?id=10

🔥 Disaster waiting to happen.


🔹 POST – PROCESS / CREATE / COMMAND

POST means:
> “Server decides what happens”

Correct Use
- Create new resource
- Trigger business workflows
- Submit commands
- Non-idempotent operations

Why POST is abused?

Because:
- Easy
- No strict rules
- Allows body
- Works everywhere

🚨 Abuse Example:

POST /users/10/updateName

This is RPC over HTTP, not REST.

POST is not “update”.


🔹 PUT – COMPLETE REPLACEMENT (IMPORTANT)

PUT means:
> “Replace the entire resource at this URI”

Key Rules
- Client knows resource URI
- Full object must be sent
- Idempotent
- Unsafe

Correct Example

PUT /users/10

{

  "id": 10,
  
  "name": "Munna",
  
  "email": "munna@gmail.com"
  
}

❌ WHEN NOT TO USE PUT
- Partial updates
- Auto-generated IDs
- Large objects with sparse updates
- When client doesn’t own resource identity


🔹 PATCH – PARTIAL MODIFICATION

PATCH means:
> “Change only what I mention”

Features
- Partial updates
- Non-idempotent (usually)
- Complex semantics

Example

PATCH /users/10

{

  "email": "new@gmail.com"
  
}

⚠️ PATCH IS HARD
- Validation complexity
- Merge logic
- Field null vs missing confusion
- Many teams misuse PATCH blindly.


🔹 DELETE – REMOVE RESOURCE

DELETE means:
> “Remove the resource identified by URI”

Properties
- Idempotent
- Unsafe
- Usually returns 204

Soft delete?
- Still DELETE semantically
- Implementation detail ≠ API contract

---

## 6️⃣ WHY POST IS ABUSED (REAL TRUTH)

Reasons:
- Frontend devs don’t understand HTTP
- Backend hides bad design
- Legacy RPC mindset
- Easier validation
- No idempotency thinking

🚨 Result:
- No cache
- Poor retry behavior
- Hard to debug
- Breaks REST constraints

---

## 7️⃣ WHEN NOT TO USE PUT (INTERVIEW GOLD)
- ❌ Don’t use PUT when:
- Updating only 1 field
- Server generates fields
- You don’t want replacement semantics
- Resource size is huge
- Concurrent updates exist

Use PATCH or POST instead.

---

## 8️⃣ FINAL OUTPUT – OPERATION → CORRECT HTTP METHOD

| Operation               | Correct Method |
| ----------------------- | -------------- |
| Fetch user details      | GET            |
| Search users            | GET            |
| Create new user         | POST           |
| Replace entire user     | PUT            |
| Update user email only  | PATCH          |
| Delete user             | DELETE         |
| Trigger password reset  | POST           |
| Upload file             | POST           |
| Bulk create             | POST           |
| Idempotent update by ID | PUT            |
| Soft delete             | DELETE         |
| Partial status change   | PATCH          |

---

## 9️⃣ DAY 04 MINDSET CHECK
If someone asks:
> “Why not use POST everywhere?”

answer:
> “Because HTTP is a contract, not a tunnel.”

---

## 🔥MOST IMPORTANT INTERVIEW QUESTIONS & ANSWERS

These are actual backend interview filters.

---

Q1️⃣ Difference between PUT and PATCH?

❌ Weak answer
> PUT is full update, PATCH is partial update.

✅ Expert answer

PUT replaces the entire resource and is idempotent.

PATCH modifies part of the resource and may not be idempotent.

If a field is missing in PUT → it is removed.

If a field is missing in PATCH → it is unchanged.


---


Q2️⃣ Why is GET called a “safe” method?

Answer

GET is safe because it must not change server state.

This allows browsers, proxies, and crawlers to freely retry, cache, and prefetch GET requests without causing side effects.

If GET mutates data → it violates HTTP semantics and risks data corruption.

---

Q3️⃣ Can a POST request be idempotent?

Answer

Technically yes, but by default it is not.

POST becomes idempotent only if the server explicitly enforces idempotency, e.g.:

- Idempotency keys

- De-duplication logic

Protocol-wise, POST is non-idempotent.

---


Q4️⃣ Why is DELETE idempotent?

Answer

Deleting the same resource multiple times results in the same final state:

resource does not exist.

Even if the first DELETE succeeds and later ones return 404, state remains unchanged.

---

Q5️⃣ Why should GET not have a request body?

Answer

HTTP does not forbid it, but:
- Browsers ignore it
- Proxies drop it
- Caches don’t consider it

This makes behavior undefined and unreliable.

Correct place for GET input → query parameters.

---

Q6️⃣ When should POST be used instead of PUT?

Answer

Use POST when:
- Server generates resource ID
- Operation is non-idempotent
- Action is a command (not pure state replacement)
- Business workflow is triggered

PUT assumes client controls resource identity.

---

Q7️⃣ Why is POST abused in real projects?

Answer

Because POST:
- Accepts any payload
- Avoids HTTP thinking
- Mimics RPC style
- Works around frontend limitations

This leads to REST violation and poor API design.

---

Q8️⃣ Is PATCH idempotent?

Answer

PATCH can be idempotent, but usually is not.

Example:

{ "email": "a@gmail.com" }


This is idempotent.

Example:

{ "loginCount": "+1" }


This is NOT idempotent.

---

Q9️⃣ Can DELETE have a request body?

Answer

It’s allowed by spec but strongly discouraged.

Many servers and proxies ignore it.

Use query parameters or resource URI instead.

---

Q1️⃣0️⃣ Is soft delete still DELETE?

Answer

Yes.

Soft delete is an implementation detail.

API contract still represents resource removal.

---

Q1️⃣1️⃣ Why not use PUT for partial updates?

Answer

PUT means full replacement.

Partial updates using PUT can accidentally:
- Remove fields
- Break backward compatibility
- Cause race conditions

PATCH exists specifically to solve this.

---

Q1️⃣2️⃣ What breaks if HTTP methods are misused?

Answer
- Caching
- Retry safety
- Idempotency guarantees
- Proxy behavior
- Scalability
- Debuggability

HTTP infra assumes semantic correctness.


🧠 INTERVIEW ONE-LINER (VERY POWERFUL)
> “HTTP methods define behavioral guarantees, not CRUD shortcuts.”

---

FIX WRONG API DESIGN

❌ Wrong APIs
- POST /getUsers
- POST /deleteUser?id=10
- POST /updateUserName

✅ Correct Design
- GET    /users
- DELETE /users/10
- PATCH  /users/10

---

DESIGN API METHODS (WRITE THIS)

Design APIs for:

1. Create user
- POST /users

2. Get user by ID
- GET /users/{id}

3. Update full profile
- PUT /users/{id}

4. Update only email
- PATCH /users/{id}

5. Delete user
- DELETE /users/{id}

---


## IDENTIFY METHOD TYPE

| Scenario         | Safe | Idempotent | Method |
| ---------------- | ---- | ---------- | ------ |
| Fetch data       | Yes  | Yes        | GET    |
| Create record    | No   | No         | POST   |
| Replace resource | No   | Yes        | PUT    |
| Partial update   | No   | Maybe      | PATCH  |
| Remove resource  | No   | Yes        | DELETE |

---

POSTMAN EXPERIMENT (IMPORTANT)

Do this:
1. Send same PUT request 5 times
2. Observe state → unchanged
3. Send same POST request 5 times
4. Observe state → duplicates created

👉 This proves idempotency in reality.

---















