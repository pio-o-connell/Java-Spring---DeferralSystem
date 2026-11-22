# Bugs Uncovered So Far

## 1. MVC configuration loaded into the wrong container — Configuration bug

In `web.xml`, `mvc-dispatcher-servlet.xml` is included in the root context's `contextConfigLocation`.

However, because the servlet is named `mvc-dispatcher`, Spring also loads that file automatically into the `DispatcherServlet`'s child context.

**Result:** The MVC configuration is loaded into both containers.

## 2. Both containers scan the same packages — Configuration bug

Both `configuration.xml` and `mvc-dispatcher-servlet.xml` contain:

```xml
<context:component-scan base-package="com.grouptwo.*" />
```

This means both containers scan the application packages.

**Potential consequences:**

- Services and repositories may be registered in both containers.
- Controllers may be registered in both containers.
- Separate instances of the same singleton bean may be created in each container.
- Beans may end up in the wrong context.

## 3. Duplicate MVC annotation configuration — Redundant configuration

In `mvc-dispatcher-servlet.xml`, there are two declarations:

```xml
<mvc:annotation-driven/>

<mvc:annotation-driven validator="validator"/>
```

There should normally be one declaration, configured with the validator.

## 4. Credentials exposed in configuration files — Security issue

The repository contains database and SMTP credentials in configuration files.

**Required actions:**

- Remove credentials from source control.
- Move them to environment variables or a secure configuration mechanism.
- Rotate the exposed credentials.

---

# The Intended Architecture

| Container | Responsibility | Component scan |
|---|---|---|
| Root | Business logic and data access | Services, repositories |
| Child | Web presentation | Controllers |

## Root container should load

- `configuration.xml`
- `spring-security.xml`

## Child container should load

- `mvc-dispatcher-servlet.xml`

---

## Qualification

These are configuration defects or risks identified from the XML.

We haven't yet inspected every Java class to establish exactly which duplicate beans are actually being instantiated.

**That's our current findings list.**