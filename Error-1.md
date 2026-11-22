# Java-Spring-DeferralSystem – Java Source Inspection Findings

I have completed the static inspection of **all 56 Java source files under `src/main/java/com/grouptwo/`** in the repository's `master` branch.

This gives us a much clearer picture. The overlapping component scans are not merely a theoretical concern: there are concrete classes in all three application layers that Spring can discover.

## 1. What I found in the Java classes

**Confirmed from source inspection**

| Layer | Concrete classes with Spring annotations | Annotation |
|---|---:|---|
| Controllers | 7 | `@Controller` |
| Service implementations | 7 | `@Service` |
| Repository implementations | 7 | `@Repository` |
| Mail component | 1 | `@Component` |
| **Total** | **22** | |

### Controllers

The seven controllers are:

- `DeferralController`
- `HomeController`
- `LecturerController`
- `LoginController`
- `ModuleController`
- `ProgrammeController`
- `StudentController`

### Service implementations

The seven service implementations are:

- `DeferralServiceImpl`
- `LecturerServiceImpl`
- `ModuleServiceImpl`
- `ProgrammeServiceImpl`
- `RegistrationServiceImpl`
- `SemesterServiceImpl`
- `StudentServiceImpl`

### Repository implementations

The seven repository implementations are:

- `DeferralJdbcDaoSupport`
- `LecturerJdbcDaoSupport`
- `ModuleJdbcDaoSupport`
- `ProgrammeJdbcDaoSupport`
- `RegistrationJdbcDaoSupport`
- `SemesterJdbcDaoSupport`
- `StudentJdbcDaoSupport`

There is also `Mail`, annotated with `@Component`.

## 2. An additional detail about the interfaces

I found another pattern worth noting.

Several service interfaces have `@Service`, and all seven DAO interfaces have `@Repository`.

For example:

- `StudentService.java` has `@Service`.
- `StudentServiceImpl.java` also has `@Service`.
- `StudentDAO.java` has `@Repository`.
- `StudentJdbcDaoSupport.java` also has `@Repository`.

However, Spring's standard component scanner does not normally instantiate interfaces as beans. The concrete implementations are the important candidates here.

This is not evidence of additional interface instances being created.

## 3. What this means for the two contexts

Both XML files currently scan the same package hierarchy:

- Root context: `WEB-INF/configuration.xml`
- DispatcherServlet child context: `WEB-INF/mvc-dispatcher-servlet.xml`

Consequently, the same 22 concrete annotated classes are candidates for registration in both contexts.

### Expected result from the current configuration

**Root application context**

- 7 controllers
- 7 service implementations
- 7 repository implementations
- 1 mail component

**DispatcherServlet child context**

- 7 controllers
- 7 service implementations
- 7 repository implementations
- 1 mail component

This represents the expected component-scan registrations, not yet a runtime bean-count report.

Importantly, Spring permits a child context to have a bean with the same name as a bean in its parent. The child bean shadows the parent bean when resolving that name locally. This does not automatically cause a duplicate-bean-name startup exception.

## 4. A further issue we need to investigate: transactions

I noticed something particularly important.

The root `configuration.xml` contains:

```xml
<tx:annotation-driven
    transaction-manager="transactionManager"
    proxy-target-class="true"/>
```

The repository classes contain numerous `@Transactional` annotations.

However, the child MVC configuration does not appear to declare its own transaction annotation processing.

That raises an important question:

**Are the repository beans created in the child context receiving transaction proxies?**

The parent's transaction post-processors do not automatically process beans created in the child context. Therefore, this is a significant potential consequence of the overlapping scans.

We need to verify it at runtime.

## 5. Updated investigation status

| Investigation | Status |
|---|---|
| Inspect all 56 Java source files in the scanned package | Completed |
| Identify concrete Spring-annotated classes | Completed |
| Establish overlapping component-scan configuration | Confirmed |
| Identify likely duplicate bean registrations | Strongly established |
| Establish actual instantiated beans in each context | Outstanding |
| Verify controller handler mappings | Outstanding |
| Verify service-to-repository wiring in both contexts | Outstanding |
| Verify transaction proxies in both contexts | Outstanding |
| Test application startup and functionality | Outstanding |

## 6. Recommended next step

I would now move from static source inspection to **Spring context investigation**.

Specifically, we should establish:

1. Which beans are registered in the root context.
2. Which beans are registered in the DispatcherServlet child context.
3. Which repository instance each service actually receives.
4. Whether the child-context repositories have transaction proxies.
5. Which controllers are actually registered with Spring MVC.

We have now completed the Java source inspection. The next stage is to prove what Spring actually creates and wires together.

**Important limitation:** This inspection was against GitHub's `master` branch. I have not inspected your local project or run the application on your machine.