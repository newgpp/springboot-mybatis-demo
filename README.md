## 功能

- 乐观锁账户清分
- 无页码分页
- 雪花id生成器




## Folder Structure（目录结构）

This boilerplate uses a folder structure and logical architecture focused on separation of concerns based in [Domain-driven design](http://dddcommunity.org/) and [Clean architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html). Instead of the classical `controllers`/`models`/`services` folders, we now have [layers](http://wiki.c2.com/?FourLayerArchitecture) inside the `src` folder. Each of the folder layers is scoped by a namespace regarding the concern it is about (like `user`, `errors`, `logging` and so on):

该样板使用一个文件夹结构和逻辑体系结构，重点是基于域驱动的设计和清洁体系结构的关注点。现在，我们现在在SRC文件夹中有层，而不是经典的控制器/模型/服务文件夹。每个文件夹层都由一个命名空间范围来范围，内容涉及其关注点（例如用户，错误，日志记录等）：
### Application layer (`app` folder)

The application layer is responsible to mediate between your input interfaces and your business domain. In this layer we'll have the use cases of your application and your application services (like a `MailService` that communicates with a `MailchimpService` from the infrastructure layer).

应用程序层负责在您的输入接口和业务领域之间进行协调。在这一层中，我们将拥有应用程序和应用程序服务的用例（例如从基础设施层与 MailchimpService 通信的 MailService）。
### Domain layer (`domain` folder)

Here you'll define your business domain classes, functions and services that compose your [domain model](https://martinfowler.com/eaaCatalog/domainModel.html). All your business rules should be declared in this layer so the application layer can use it to compose your use cases.

在这里，您将定义构成域模型的业务域类、函数和服务。您的所有业务规则都应该在这一层中声明，以便应用程序层可以使用它来组成您的用例。
### Infrastructure layer (`infra` folder)

This is the lowest of the layers. In the infra layer you'll have the communication with what is outside your application, like the database (check the repository pattern section on [[Patterns recommendations and operations]]), mail services and direct communication with frameworks.

这是最低层。在基础层中，您将与应用程序外部的内容进行通信，例如数据库（检查[[模式建议和操作]]上的存储库模式部分）、邮件服务以及与框架的直接通信。
### Input interfaces layer (`interfaces` folder)

This folder contains all the entry points for your application. From the beginning here's where your Express controllers will be (inside the `interfaces/http` folder).

该文件夹包含您的应用程序的所有入口点。从一开始，这里就是您的 Express 控制器所在的位置（interfaces/http 文件夹内）。