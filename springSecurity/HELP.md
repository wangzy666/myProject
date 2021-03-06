1、Spring security 简介
​ Spring Security 为 Java EE-based 企业软件应用程序提供全面的安全服务（也就是用户登录页面和相关权限的控制），应用的安全性包括用户认证（ Authentication ）和用户权限（ Authorization ）两部分。 用户认证是确定某个用户是否有进入系统的权限，使用用户名密码去认证，也就是所谓的登录；用户权限是确定哪些用户有哪些功能权限，一般都是按角色。

2、主要过滤器
​ 众所周知 想要对对Web资源进行保护，最好的办法莫过于Filter，要想对方法调用进行保护，最好的办法莫过于AOP。所以springSecurity在我们进行用户认证以及授予权限的时候，通过各种各样的拦截器来控制权限的访问，从而实现安全。

有篇关于过滤器的文章，有需要可以查看下

https://blog.csdn.net/andy_zhang2007/article/details/84726992

WebAsyncManagerIntegrationFilter

为请求处理过程中可能发生的异步调用准备安全上下文获取途径

SecurityContextPersistenceFilter

整个请求处理过程所需的安全上下文对象SecurityContext的准备和清理不管请求是否针对需要登录才能访问的页面，这里都会确保SecurityContextHolder中出现一个SecurityContext对象:
1.未登录状态访问登录保护页面:空SecurityContext对象，所含Authentication为null
2.登录状态访问某个页面:从SecurityContextRepository获取的SecurityContext对象

HeaderWriterFilter

将指定的头部信息写入响应对象

CorsFilter

对请求进行csrf保护

LogoutFilter

检测用户退出登录请求并做相应退出登录处理

RequestCacheAwareFilter

提取请求缓存中缓存的请求
1.请求缓存在安全机制启动时指定
2.请求写入缓存在其他地方完成
3.典型应用场景:
用户请求保护的页面，
系统引导用户完成登录认证,
然后自动跳转到到用户最初请求页面

SecurityContextHolderAwareRequestFilter

包装请求对象使之可以访问SecurityContextHolder,从而使请求真正意义上拥有接口HttpServletRequest中定义的getUserPrincipal这种访问安全信息的能力

AnonymousAuthenticationFilter

如果当前SecurityContext属性Authentication为null，将其替换为一个AnonymousAuthenticationToken`

SessionManagementFilter

检测从请求处理开始到目前是否有用户登录认证，如果有做相应的session管理，比如针对为新登录用户创建新的session(session fixation防护)和设置新的csrf token等。

ExceptionTranslationFilter

处理AccessDeniedException和 AuthenticationException异常，将它们转换成相应的HTTP响应

FilterSecurityInterceptor

一个请求处理的安全处理过滤器链的最后一个，检查用户是否已经认证,如果未认证执行必要的认证，对目标资源的权限检查，如果认证或者权限不足，抛出相应的异常:AccessDeniedException或者AuthenticationException

UsernamePasswordAuthenticationFilter

检测用户名/密码表单登录认证请求并作相应认证处理:
1.session管理，比如为新登录用户创建新session(session fixation防护)和设置新的csrf token等
2.经过完全认证的Authentication对象设置到SecurityContextHolder中的SecurityContext上;
3.发布登录认证成功事件InteractiveAuthenticationSuccessEvent
4.登录认证成功时的Remember Me处理
5.登录认证成功时的页面跳转

BasicAuthenticationFilter 检测和处理http basic认证

DefaultLoginPageGeneratingFilter 生成缺省的登录页面

DefaultLogoutPageGeneratingFilter 生成缺省的退出登录页面

RememberMeAuthenticationFilter 针对Remember Me登录认证机制的处理逻辑 (免登陆)

3、security核心组件
SecurityContextHolder：提供对SecurityContext的访问
SecurityContext,：持有Authentication对象和其他可能需要的信息
UsernamePasswordAuthenticationFilter 检测用户民密码并做处理
AuthenticationManager 其中可以包含多个AuthenticationProvider
ProviderManager对象为AuthenticationManager接口的实现类
AuthenticationProvider 主要用来进行认证操作的类 调用其中的authenticate()方法去进行认证操作
Authentication：Spring Security方式的认证主体
GrantedAuthority：对认证主题的应用层面的授权，含当前用户的权限信息，通常使用角色表示
UserDetails：构建Authentication对象必须的信息，可以自定义，可能需要访问DB得到
UserDetailsService：通过username构建UserDetails对象，通过loadUserByUsername根据userName获取UserDetail对象 （可以在这里基于自身业务进行自定义的实现 如通过数据库，xml,缓存获取等）
passwordEncoder 密码加密器