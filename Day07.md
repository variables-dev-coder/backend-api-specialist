# 🔹 API Specialist – Day 07

# HTTP Error Philosophy

## 1️⃣ Errors Are NOT Failures — Errors Are Part of the Contract

Beginner thinking ❌
> “Error means something went wrong.”

Expert thinking ✅
> “An error is a valid, expected outcome defined by the API contract.”

In HTTP:
- Success (2xx) is a contract
- Redirect (3xx) is a contract
- Error (4xx / 5xx) is ALSO a contract

📌 If your API does not clearly define errors,

your API contract is incomplete.

---

## 2️⃣ Errors Are for MACHINES First, Humans Second

Machines need:
- Predictable status codes
- Stable error structure
- Deterministic behavior

Humans need:
- Clear messages
- Debug hints (but safe)

👉 Never design errors only for humans.

---

## 3️⃣ Client Error vs Server Error (Deep Meaning)

This distinction is critical.

## 🔹 4xx — Client Error
> “The server refuses to process the request as sent.”

Meaning:
- Request is malformed
- Authentication missing or invalid
- Authorization insufficient
- Resource does not exist
- Validation failed

📌 Rule

If the client retries the SAME request → it will FAIL again.

Examples:
- 400 Bad Request
- 401 Unauthorized
- 403 Forbidden
- 404 Not Found
- 422 Unprocessable Entity

## 🔹 5xx — Server Error
> “The request was valid, but the server failed to process it.”

Meaning:
- Database down
- Timeout
- Null pointer
- Dependency failure

📌 Rule

Client retry MAY succeed later.

Examples:
- 500 Internal Server Error
- 502 Bad Gateway
- 503 Service Unavailable
- 504 Gateway Timeout

## 🔥 Interview GOLD Rule
> If client must change request → 4xx
>  
> If client can retry later → 5xx

---

## 4️⃣ Why Stack Traces Are SECURITY BUGS

This is security-critical, not optional.

❌ What bad APIs do

{

  "error": "NullPointerException at UserService.java:87",
  
  "stackTrace": "com.company.service.UserService..."
  
}

Why this is dangerous:
- Reveals internal classes
- Reveals technology stack
- Reveals file structure
- Helps attackers craft exploits

📌 OWASP considers this an information disclosure vulnerability.

✅ What good APIs do
- Log stack trace internally
- Return safe, abstract error to client


---

## 5️⃣ Errors Must Be STABLE Over TIME

Never do this ❌
- Change error structure every release
- Change error field names
- Mix different formats

Clients depend on error structure for:
- UI messages
- Retry logic
- Monitoring
- Alerting

📌 Breaking error format is a breaking API change.

---

## 6️⃣ Designing a STANDARD Error Response Format

This is the core output of Day 07.

🎯 Goals of a Good Error Format
- Machine-readable
- Predictable
- Secure
- Extensible
- Human-friendly (but safe)

✅ Recommended Standard Error Response

{

  "timestamp": "2026-01-06T10:15:30Z",
  
  "status": 400,
  
  "error": "Bad Request",
  
  "code": "INVALID_EMAIL",
  
  "message": "Email format is invalid",
  
  "path": "/users"
  
}


🔍 Field-by-Field Explanation (Expert)

| Field     | Purpose                                           |
| --------- | ------------------------------------------------- |
| timestamp | When error occurred (debugging & tracing)         |
| status    | HTTP status code (machine logic)                  |
| error     | HTTP reason phrase (standard)                     |
| code      | **Application-level error code** (VERY IMPORTANT) |
| message   | Human-readable safe explanation                   |
| path      | Request URI (context)                             |


---

## 7️⃣ Why code Is MORE Important Than message

Message ❌
- May change
- May be localized
- For display only

Code ✅
- Stable
- Language-independent
- Used by frontend & automation

📌 Frontends should depend on code, not message.

---

## 8️⃣ Example: Client Error vs Server Error (Correct Design)

🔹 Client Error (Validation)

HTTP/1.1 422 Unprocessable Entity

{

  "status": 422,
  
  "error": "Unprocessable Entity",
  
  "code": "EMAIL_REQUIRED",
  
  "message": "Email is required",
  
  "path": "/users"
  
}


🔹 Server Error (Crash)

HTTP/1.1 500 Internal Server Error

{

  "status": 500,
  
  "error": "Internal Server Error",
  
  "code": "INTERNAL_ERROR",
  
  "message": "Something went wrong. Please try again later.",
  
  "path": "/users"
  
}

📌 No stack trace. No internals. Secure.


---

## 9️⃣ Monitoring & Observability Insight (Senior Level)
- Clients see generic error
- Logs contain:
    - stack trace
    - correlation ID
    - request details

👉 Same error, two audiences.

---

## 🔥 Final Expert Takeaways
- Errors are first-class API responses
- 4xx vs 5xx is about responsibility
- Stack traces in responses = security bugs
- Error format is part of public contract
- Stable error codes matter more than messages

---

# 🔹 API Specialist – Day 07  
## Important Interview Questions & Answers  
### Topic: HTTP Error Philosophy

---

### Q1: Are HTTP errors part of the API contract?
**Answer:**  
Yes. Errors are first-class API responses. A well-designed API explicitly defines its error behavior, status codes, and error formats as part of the public contract.

---

### Q2: What is the core difference between 4xx and 5xx errors?
**Answer:**  
4xx indicates a client-side problem where the request must change to succeed.  
5xx indicates a server-side failure where the same request may succeed later.

---

### Q3: How do you decide whether an error should be 4xx or 5xx?
**Answer:**  
If the client needs to modify the request → 4xx.  
If the client can retry later without changing the request → 5xx.

---

### Q4: Is retrying a request after a 4xx error recommended?
**Answer:**  
No. Retrying the same request will fail again because the error is caused by client input or state.

---

### Q5: Is retrying a request after a 5xx error acceptable?
**Answer:**  
Yes, depending on the method and error type. Retries should use limits and exponential backoff.

---

### Q6: Why should APIs not return stack traces in error responses?
**Answer:**  
Because stack traces expose internal implementation details, class names, and technologies, which is a security risk and considered information disclosure.

---

### Q7: Where should stack traces be handled instead?
**Answer:**  
Stack traces should be logged internally and correlated with request identifiers, not exposed to API consumers.

---

### Q8: Why is a standard error response format important?
**Answer:**  
Clients depend on consistent error structures for automation, UI handling, monitoring, and retries. Changing error formats is a breaking API change.

---

### Q9: What fields should a standard API error response contain?
**Answer:**  
At minimum:
- HTTP status
- Error type or reason
- Application-level error code
- Safe, human-readable message
- Request path or context

---

### Q10: Why is an application-level error code important?
**Answer:**  
Because error messages may change or be localized, while error codes remain stable and are used by clients for logic and handling.

---

### Q11: Should frontend applications depend on error messages?
**Answer:**  
No. Frontends should depend on error codes. Messages are for display only.

---

### Q12: Is it acceptable to return `200 OK` with an error message in the body?
**Answer:**  
No. This breaks HTTP semantics, client logic, monitoring, and caching. Proper status codes must be used.

---

### Q13: Why is `404 Not Found` preferred over `403 Forbidden` in some security-sensitive APIs?
**Answer:**  
To avoid revealing whether a resource exists, preventing information leakage to unauthorized users.

---

### Q14: Should APIs expose different error messages for authentication vs authorization?
**Answer:**  
Yes internally, but externally messages should remain generic to avoid leaking security details.

---

### Q15: Can error responses be cached?
**Answer:**  
Yes, but only if explicitly allowed via cache headers. Most error responses should not be cached by default.

---

### Q16: Is an empty error body valid in HTTP?
**Answer:**  
Yes. Status code and headers alone are sufficient to communicate errors.

---

### Q17: Why should error messages be safe and generic?
**Answer:**  
To prevent attackers from learning internal system behavior while still providing meaningful feedback to clients.

---

### Q18: How do errors relate to observability?
**Answer:**  
Clients receive sanitized errors, while logs and monitoring systems capture detailed diagnostic information.

---

### Q19: What is the impact of changing error formats in an API?
**Answer:**  
It breaks clients. Error formats are part of the public contract and must be versioned if changed.

---

### Q20: What is the biggest mistake developers make with HTTP errors?
**Answer:**  
Treating errors as exceptions instead of designing them as predictable, documented API responses.

---

## Final Interview Insight

> APIs are judged not by how they handle success,  
> but by how predictably and securely they handle failure.
































