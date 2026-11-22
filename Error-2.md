I’ve moved on to the Spring context investigation and checked the current master branch, including the Maven configuration, web deployment descriptors and existing tests.

One important distinction has emerged: the application’s web deployment and its existing tests load different Spring configuration files. That means the tests may be exercising a different context arrangement from the one used when the web application starts. We need to account for that before treating passing tests as evidence that the web configuration is correct.

# Java-Spring-DeferralSystem – Spring Context Investigation Findings

## 1. Summary

The Spring context investigation has identified an important distinction between the configuration used by the deployed web application and the configuration used by the existing repository tests.

The tests load a simplified repository-only Spring context. They do not reproduce the web application's complete parent/child context hierarchy.

This means that passing repository tests cannot establish that the deployed application's Spring MVC configuration is correct.

---

## 2. Two different `configuration.xml` files

There are two separate files with the same name:

| File | Location | Purpose |
|---|---|---|
| Web application configuration | `src/main/webapp/WEB-INF/configuration.xml` | Loaded by `ContextLoaderListener` into the root context |
| Test configuration | `src/main/resources/configuration.xml` | Loaded by tests using `classpath:configuration.xml` |

These files are not equivalent.

### Web application configuration

The web application's root configuration contains:

```xml
<context:component-scan base-package="com.grouptwo.*" />
```

This scans the broad application package hierarchy.

### Test configuration

The test configuration contains:

```xml
<context:component-scan base-package="com.grouptwo.repository" />
```

This scans only the repository package.

The test configuration also contains:

```xml
<tx:annotation-driven
    transaction-manager="transactionManager"
    proxy-target-class="true"/>
```

The web application's root configuration contains transaction annotation processing too, but the DispatcherServlet child configuration does not.

---

## 3. What the existing tests actually test

The repository tests use:

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:configuration.xml" })
```

This loads:

`src/main/resources/configuration.xml`

It does not load the web application's root and child context arrangement.

| Test area | What the configuration provides |
|---|---|
| Repository implementations | Scanned and registered |
| DataSource | Defined |
| TransactionManager | Defined |
| Transaction annotation processing | Enabled |
| MVC controllers | Not scanned |
| MVC DispatcherServlet configuration | Not loaded |
| Spring Security configuration | Not loaded |
| Parent/child context interaction | Not tested |

**Conclusion:** The existing repository tests cannot establish that the live web application's Spring MVC configuration is correct.

They are useful repository-layer tests, but they are not tests of the deployed application's complete Spring context hierarchy.

---

## 4. What happens in the live web application

The `web.xml` contains:

```xml
<servlet>
    <servlet-name>mvc-dispatcher</servlet-name>
    <servlet-class>
        org.springframework.web.servlet.DispatcherServlet
    </servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
```

It also contains:

```xml
<listener>
    <listener-class>
        org.springframework.web.context.ContextLoaderListener
    </listener-class>
</listener>
```

The intended hierarchy is:

```text
Root ApplicationContext
|
|-- ContextLoaderListener
|
|-- WEB-INF/configuration.xml
|-- WEB-INF/spring-security.xml
|
|-- Currently also loads:
    WEB-INF/mvc-dispatcher-servlet.xml
        |
        v
DispatcherServlet child ApplicationContext
|
|-- mvc-dispatcher
|
|-- Conventionally loads:
    WEB-INF/mvc-dispatcher-servlet.xml
```

The third item in the root configuration list is problematic.

The `web.xml` currently contains:

```xml
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>
        /WEB-INF/mvc-dispatcher-servlet.xml,
        /WEB-INF/configuration.xml,
        /WEB-INF/spring-security.xml,
    </param-value>
</context-param>
```

Because the servlet is named `mvc-dispatcher`, Spring also loads `mvc-dispatcher-servlet.xml` into its child context by convention.

Therefore, the MVC configuration is loaded into both contexts.

---

## 5. The duplicate component scanning problem

Both web configuration files contain:

```xml
<context:component-scan base-package="com.grouptwo.*" />
```

The previous Java source inspection identified 22 concrete Spring-stereotyped classes.

| Type | Number |
|---|---:|
| Controllers | 7 |
| Service implementations | 7 |
| Repository implementations | 7 |
| Other components | 1 |
| **Total** | **22** |

These are candidates for registration in both contexts.

| Component type | Root context | Child context |
|---|---|---|
| Controllers | Scanned | Scanned |
| Services | Scanned | Scanned |
| Repositories | Scanned | Scanned |
| Mail component | Scanned | Scanned |

These are separate bean definitions in separate containers.

A child bean with the same name as a parent bean can shadow the parent bean when the child performs a lookup. It does not automatically cause a duplicate-name startup exception.

**Qualification:** We have established the overlapping scanning configuration and identified the candidate classes. We have not yet obtained a runtime bean inventory proving exactly which beans were instantiated.

---

6. Transaction proxy problem

This is potentially the most important technical consequence discovered so far.

The root configuration contains:

<tx:annotation-driven
    transaction-manager="transactionManager"
    proxy-target-class="true"/>

The MVC child configuration does not.

Consider DeferralJdbcDaoSupport.java:

@Repository
public class DeferralJdbcDaoSupport
        extends JdbcDaoSupport implements DeferralDAO {

    @Transactional(
        readOnly = false,
        propagation = Propagation.REQUIRES_NEW
    )
    public void createModuleDeferral(
            String studentId,
            String moduleId,
            int crnNumber) {
        // Database operation
    }
}

There are many methods in this repository implementation annotated with @Transactional.

The problem is the separation between the two contexts:

The root context has transaction annotation processing.

The child context does not.

A parent context's bean post-processors do not automatically process beans created in its child context.

Therefore, if the child creates its own repository instances through its component scan, those child-created instances will not automatically receive transaction proxies from the parent's transaction configuration.

The child can see the parent's DataSource and transactionManager, but that does not mean its own beans are automatically transaction-proxied.

Status: Strongly indicated configuration defect; runtime verification required.

This is particularly important because the existing repository tests load the resource configuration, which does enable transaction annotation processing. They therefore do not reproduce this specific web child-context arrangement.

7. Controller and handler mapping qualification

We should be precise about the controllers.

It would be incorrect to conclude simply that controllers in the root context are necessarily invisible to the DispatcherServlet.

Spring MVC's DispatcherServlet can detect certain infrastructure beans, including handler mappings, in ancestor contexts.

Since the root configuration currently loads MVC annotation configuration too, the root may have its own handler mapping infrastructure.

We therefore need to investigate:

Which handler mappings are registered in the root.

Which handler mappings are registered in the child.

Which controllers each mapping discovers.

Whether the same URL mappings are exposed through both contexts.

We should not claim the exact request-routing result until we inspect the actual mappings at runtime.

8. Updated findings register

No.

Finding

Status

1

MVC configuration explicitly loaded into root and conventionally into child

Confirmed

2

Both web contexts scan the same package hierarchy

Confirmed

3

Duplicate MVC annotation-driven declarations

Confirmed

4

22 concrete Spring components are candidates for overlapping registration

Source inspected

5

Test configuration differs from web application configuration

Confirmed

6

Existing tests do not exercise the web parent/child context hierarchy

Confirmed

7

Child-created transactional repositories may lack transaction proxies

Strongly indicated

8

Exact bean counts in root and child

Not yet proven

9

Exact MVC handler mappings and duplicate URL exposure

Not yet proven

10

Actual application startup and request behaviour

Not yet tested

9. Recommended configuration architecture

I would not start by changing individual Java classes. The first correction should be the configuration architecture.

The intended separation should be:

Root context

DataSource

TransactionManager

Transaction annotation processing

Repositories

Services

Mail infrastructure

Spring Security

DispatcherServlet child context

Controllers

MVC annotation processing

Validator

Tiles

View resolvers

Multipart resolver

Static resources

The root should not load the MVC servlet configuration explicitly. The DispatcherServlet will load its own configuration by convention.

Component scanning should also be separated:

Controllers in the child.

Services and repositories in the root.

This avoids creating two copies of the same application components.

10. What we need to prove next

The next stage should be a controlled Spring context diagnostic rather than immediately rewriting the XML.

We need to establish four things:

The actual bean names and types in the root context.

The actual bean names and types in the DispatcherServlet child context.

Whether the repository instances in each context are Spring AOP proxies.

Which handler mappings and controller methods the DispatcherServlet actually sees.

We can then compare the results against the proposed configuration and verify that the correction resolves the problems.

11. Overall conclusion

We have identified a more fundamental problem than just duplicate component scanning.

The existing tests use a simplified, repository-only context, while the deployed application has a root/child configuration arrangement with overlapping scans and potentially missing transaction proxies in the child.

That is where the next investigation should concentrate.

Inspection qualification: The findings above are based on static inspection of the GitHub master branch. The application has not been run and its tests have not been executed as part of this investigation. Runtime bean counts, handler mappings, proxy behaviour and application startup remain to be verified.