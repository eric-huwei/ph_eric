<!-- TOC -->

- [1. 基本概念](#1-基本概念)
- [2. 说明](#2-说明)
- [3. 模块说明](#3-模块说明)
- [4. 开发环境](#4-开发环境)
  - [4.1. JDK](#41-jdk)
    - [4.1.1. 环境变量](#411-环境变量)
    - [4.1.2. 安装](#412-安装)
    - [4.1.3. 环境变量](#413-环境变量)
  - [4.2. Maven配置](#42-maven配置)
    - [4.2.1. 依赖包存储位置](#421-依赖包存储位置)
    - [4.2.2. 镜像仓库的配置](#422-镜像仓库的配置)
- [5. 项目开发简介](#5-项目开发简介)
  - [5.1. SCE业务实现](#51-sce业务实现)
    - [5.1.1. 入参封装](#511-入参封装)
      - [5.1.1.1. 基类](#5111-基类)
      - [5.1.1.2. 手动编写方法实例化](#5112-手动编写方法实例化)
    - [5.1.2. 出参封装](#512-出参封装)
  - [5.2. 业务层编写](#52-业务层编写)
- [6. 测试](#6-测试)
  - [6.1. postman测试](#61-postman测试)
  - [6.2. curl测试](#62-curl测试)

<!-- /TOC -->

## 1. 基本概念

| 名称  | 说明  |
| -- | --  |
| * | * |
|   |   |
|   |   |

## 2. 说明

[ph_eric工程发布部署以及环境手册](Doc/Deploy.md)

## 3. 模块说明

~~~txt

ph_eric
├── db -- 数据库脚本
├── jwtsso -- ssm工程
├    |-- main
├    ├    ├── java
├    ├    ├    ├── com.eric
├    |-- test
├── leetcode -- daily算法训练
├── rpc -- rpc工程
├── pom.xml
├── README.md

~~~

## 4. 开发环境

| 工具 | 版本 | 约束 |
| -- | -- | -- |
| JDK | 1.8+ | 必须 |
| Maven | 3.5+ | 必须 |
| IDEA | 2021.1+ | 必须 |

### 4.1. JDK

#### 4.1.1. 环境变量

- Windows 64位 [下载地址](https://download.oracle.com/otn-pub/java/jdk/8u201-b09/42970487e3af4f5aa5bca3f542482c60/jdk-8u201-windows-x64.exe)
- Linux 64位	[下载地址](https://download.oracle.com/errors/download-fail-1505220.html)

#### 4.1.2. 安装

此处仅说明 `Windows` 环境下，安装。

直接运行 `exe` 可执行程序，默认安装即可；备注：路径可以选其他盘符，不建议路径包含中文名及特殊符号。

#### 4.1.3. 环境变量

- 新建变量名：JAVA_HOME，变量值：C:\Program Files\Java\jdk1.8.0_11
- 打开PATH，添加变量值：%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin
- 新建变量名：CLASSPATH，变量值：.;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar

### 4.2. Maven配置

配置文件的路径均在 `%MAVEN_HOME%\conf\settings.xml` 下。

#### 4.2.1. 依赖包存储位置

修改 `<localRepository>` 节点中的路径为本地路径即可。

#### 4.2.2. 镜像仓库的配置

修改 `<mirrors>` 节点中子节点内容为需要的，通常在实际公司及项目开发中使用公司仓库 `http://192.168.55.11:8001` 为前缀的，日常学习建议用 `https://maven.aliyun.com`。

~~~xml

  <mirrors>
    <mirror>
         
    </mirror>
  </mirrors>

~~~

## 5. 项目开发简介

## 6. 测试